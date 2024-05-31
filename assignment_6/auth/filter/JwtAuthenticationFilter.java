package org.sopt.sopt.auth.filter;

import static org.sopt.sopt.auth.type.JwtValidationType.VALID_JWT;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.sopt.sopt.auth.JwtTokenProvider;
import org.sopt.sopt.auth.exception.UnauthorizedException;
import org.sopt.sopt.auth.token.UserAuthentication;
import org.sopt.sopt.common.dto.response.responseEnum.ErrorStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {
    try {
      final String token = getJwtFromRequest(request);
      if (jwtTokenProvider.validateToken(token) == VALID_JWT) {
        Long memberId = jwtTokenProvider.getUserFromJwt(token);
        UserAuthentication authentication = UserAuthentication.createUserAuthentication(memberId);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception exception) {
      Object ErrorMessage;
      throw new UnauthorizedException(ErrorStatus.JWT_UNAUTHORIZED_EXCEPTION);
    }
    filterChain.doFilter(request, response);
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring("Bearer ".length());
    }
    return null;
  }
}
