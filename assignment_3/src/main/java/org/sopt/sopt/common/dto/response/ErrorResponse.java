package org.sopt.sopt.common.dto.response;


import org.sopt.sopt.exception.ErrorMessage;

public record ErrorResponse(

    int status,
    String message

) {

  public static ErrorResponse of(int status, String message) {
    return new ErrorResponse(status, message);
  }

  public static ErrorResponse of(ErrorMessage errormessage) {
    return new ErrorResponse(errormessage.getStatus(), errormessage.getMessage());
  }
}
