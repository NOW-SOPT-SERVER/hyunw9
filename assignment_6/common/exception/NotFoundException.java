package org.sopt.sopt.common.exception;

import org.sopt.sopt.common.dto.response.responseEnum.ErrorStatus;

public class NotFoundException extends BusinessException{

  public NotFoundException(ErrorStatus errorStatus) {
    super(errorStatus);
  }
}
