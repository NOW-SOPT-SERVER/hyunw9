package org.sopt.sopt.global.exception;


import java.util.Date;
import lombok.Builder;

public record ErrorResponse (

  String errorCode,
  String errorMessage,
  Date date
){

  @Builder
  public ErrorResponse(String errorCode, String errorMessage, Date date) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.date = date;
  }
}
