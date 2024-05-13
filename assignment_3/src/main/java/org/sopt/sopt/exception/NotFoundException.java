package org.sopt.sopt.exception;

public class NotFoundException extends BusinessException{

  public NotFoundException(ErrorMessage errorMessage) {
    super(errorMessage);
  }
}
