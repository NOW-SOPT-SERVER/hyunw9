package org.sopt.sopt.common.exception;

import lombok.Getter;
import org.sopt.sopt.common.dto.response.responseEnum.ErrorStatus;

@Getter
public class BusinessException extends RuntimeException{

  private final ErrorStatus errorStatus;

  public BusinessException(ErrorStatus errorStatus) {
    super(errorStatus.getMessage());
    this.errorStatus = errorStatus;
  }
}
