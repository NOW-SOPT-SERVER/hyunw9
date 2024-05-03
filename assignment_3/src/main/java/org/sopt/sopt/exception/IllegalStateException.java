package org.sopt.sopt.exception;

public class IllegalStateException extends BusinessException{

  public IllegalStateException(ErrorMessage errorMessage) {
    super(errorMessage);
  }
}
