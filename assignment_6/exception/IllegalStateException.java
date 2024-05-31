package org.sopt.sopt.exception;

import org.sopt.sopt.common.dto.response.responseEnum.ErrorStatus;

public class IllegalStateException extends BusinessException{

  public IllegalStateException(ErrorStatus errorStatus) {
    super(errorStatus);
  }
}
