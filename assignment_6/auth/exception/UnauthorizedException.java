package org.sopt.sopt.auth.exception;

import org.sopt.sopt.exception.BusinessException;
import org.sopt.sopt.common.dto.response.responseEnum.ErrorStatus;

public class UnauthorizedException extends BusinessException {

  public UnauthorizedException(ErrorStatus errorStatus) {
    super(errorStatus);
  }
}
