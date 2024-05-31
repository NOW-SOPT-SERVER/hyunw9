package org.sopt.sopt.auth.handler;

import org.sopt.sopt.auth.exception.UnauthorizedException;
import org.sopt.sopt.common.dto.response.responseEnum.ErrorStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PrincipalHandler {

  private static final String ANONYMOUS_USER = "anonymousUser";

  public Long getUserIdFromPrincipal() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    isPrincipalNull(principal);
    return Long.valueOf(principal.toString());
  }

  public void isPrincipalNull(
      final Object principal
  ) {
    if (principal.toString().equals(ANONYMOUS_USER)) {
      throw new UnauthorizedException(ErrorStatus.JWT_UNAUTHORIZED_EXCEPTION);
    }
  }
}
