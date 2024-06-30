package org.sopt.sopt.auth.application.exception;

import org.sopt.sopt.common.exception.BusinessException;
import org.sopt.sopt.common.dto.response.responseEnum.ErrorStatus;

public class UnauthorizedException extends BusinessException {

  public UnauthorizedException(ErrorStatus errorStatus) {
    super(errorStatus);
  }
}
