package org.sopt.sopt.service;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.sopt.sopt.auth.JwtTokenProvider;
import org.sopt.sopt.auth.service.CustomUserDetailsService;
import org.sopt.sopt.common.dto.request.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final CustomUserDetailsService userDetailsService;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider tokenProvider;

  public Map<String, String> authenticate(LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
    String accessToken = tokenProvider.issueAccessToken(authentication);
    String refreshToken = tokenProvider.issueRefreshToken(authentication);

    Map<String, String> tokens = new HashMap<>();
    tokens.put("accessToken", accessToken);
    tokens.put("refreshToken", refreshToken);
    return tokens;
  }

  public Map<String, String> refreshAccessToken(String refreshToken) {
    String newAccessToken = tokenProvider.refreshAccessToken(refreshToken);

    Map<String, String> tokens = new HashMap<>();
    tokens.put("accessToken", newAccessToken);
    tokens.put("refreshToken", refreshToken); // Refresh Token은 그대로 재사용
    return tokens;
  }

  public void invalidateRefreshToken(String refreshToken) {
    tokenProvider.validateToken(refreshToken);
  }



}
