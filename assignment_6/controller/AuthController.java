package org.sopt.sopt.controller;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.sopt.sopt.common.dto.request.LoginRequest;
import org.sopt.sopt.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {


  private final AuthService authService;

  @PostMapping("/auth/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(authService.authenticate(loginRequest));
  }

  @PostMapping("/auth/refresh")
  public ResponseEntity<?> refresh(@RequestHeader String refreshToken) {
      Map<String, String> tokens = authService.refreshAccessToken(refreshToken);
      return ResponseEntity.ok(tokens);
  }
}
