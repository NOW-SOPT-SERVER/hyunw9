package org.sopt.sopt.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.sopt.sopt.auth.redis.domain.RefreshToken;
import org.sopt.sopt.auth.redis.repository.RefreshTokenRepository;
import org.sopt.sopt.auth.type.JwtValidationType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  @Value("${jwt.secret}")
  private String JWT_SECRET;

  private static final String USER_ID = "userId";

  private static final Long ACCESS_TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000L * 14;
  private static final Long REFRESH_TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000L * 14;

  private final RefreshTokenRepository refreshTokenRepository;
  private final UserDetailsService userDetailsService;

  public String issueAccessToken(final Authentication authentication) {
    return generateAccessToken(authentication, ACCESS_TOKEN_EXPIRATION_TIME);
  }

  public String issueRefreshToken(final Authentication authentication) {
    String accessToken = generateAccessToken(authentication, ACCESS_TOKEN_EXPIRATION_TIME);
    return generateRefreshToken(accessToken, authentication);
  }

  public String generateAccessToken(Authentication authentication, Long tokenExpirationTime) {
    final Date now = new Date();
    final Claims claims = Jwts.claims()
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + tokenExpirationTime));      // 만료 시간

    claims.put(USER_ID, authentication.getPrincipal());

    return Jwts.builder()
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // Header
        .setClaims(claims) // Claim
        .signWith(getSigningKey()) // Signature
        .compact();
  }

  public String generateRefreshToken(String accessToken, Authentication authentication) {
    final Date now = new Date();
    String refreshToken = UUID.randomUUID().toString();
    RefreshToken rToken = RefreshToken.builder()
        .id(UUID.randomUUID())
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .expiryDate(now.getTime() + REFRESH_TOKEN_EXPIRATION_TIME)
        .build();

    refreshTokenRepository.save(rToken);
    return refreshToken;
  }

  private SecretKey getSigningKey() {
    String encodedKey = Base64.getEncoder().encodeToString(JWT_SECRET.getBytes()); //SecretKey 통해 서명 생성
    return Keys.hmacShaKeyFor(encodedKey.getBytes());   //일반적으로 HMAC (Hash-based Message Authentication Code) 알고리즘 사용
  }

  public JwtValidationType validateToken(String token) {
    try {
      final Claims claims = getBody(token);
      return JwtValidationType.VALID_JWT;
    } catch (MalformedJwtException ex) {
      return JwtValidationType.INVALID_JWT_TOKEN;
    } catch (ExpiredJwtException ex) {
      return JwtValidationType.EXPIRED_JWT_TOKEN;
    } catch (UnsupportedJwtException ex) {
      return JwtValidationType.UNSUPPORTED_JWT_TOKEN;
    } catch (IllegalArgumentException ex) {
      return JwtValidationType.EMPTY_JWT;
    }
  }

  private Claims getBody(final String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public Long getUserFromJwt(String token) {
    Claims claims = getBody(token);
    return Long.valueOf(claims.get(USER_ID).toString());
  }

  public String refreshAccessToken(String refreshToken) {
    Optional<RefreshToken> refreshTokenOpt = refreshTokenRepository.findByRefreshToken(refreshToken);
    if (refreshTokenOpt.isPresent()) {
      RefreshToken token = refreshTokenOpt.get();
      UserDetails userDetails = userDetailsService.loadUserByUsername(token.getUserName());
      Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
      String newAccessToken = generateAccessToken(authentication, ACCESS_TOKEN_EXPIRATION_TIME);
      token.setAccessToken(newAccessToken);
      refreshTokenRepository.save(token);
      return newAccessToken;
    }
    throw new IllegalArgumentException("Invalid refresh token");
  }

}
