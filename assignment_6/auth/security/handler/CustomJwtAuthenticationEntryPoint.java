package org.sopt.sopt.auth.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.sopt.sopt.common.dto.response.responseEnum.ErrorResponse;
import org.sopt.sopt.common.dto.response.responseEnum.ErrorStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomJwtAuthenticationEntryPoint implements
    AuthenticationEntryPoint {

  private final ObjectMapper objectMapper;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    setResponse(response);
  }

  private void setResponse(HttpServletResponse response) throws IOException {
    String bearerToken = response.getHeader("Authorization");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");
    response.getWriter()
        .write(objectMapper.writeValueAsString(ErrorResponse.of(
            ErrorStatus.JWT_UNAUTHORIZED_EXCEPTION.getStatus(),
            ErrorStatus.JWT_UNAUTHORIZED_EXCEPTION.getMessage())));
  }
}
