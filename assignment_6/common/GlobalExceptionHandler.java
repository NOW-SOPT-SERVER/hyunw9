package org.sopt.sopt.common;

import lombok.extern.slf4j.Slf4j;
import org.sopt.sopt.auth.exception.UnauthorizedException;
import org.sopt.sopt.common.dto.response.responseEnum.ErrorResponse;
import org.sopt.sopt.exception.IllegalStateException;
import org.sopt.sopt.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  protected ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ErrorResponse.of(e.getErrorStatus().getStatus(), e.getMessage()));
  }

  @ExceptionHandler(IllegalStateException.class)
  protected ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(ErrorResponse.of(e.getErrorStatus().getStatus(), e.getMessage()));
  }

  @ExceptionHandler(UnauthorizedException.class)
  protected ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException e){
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(ErrorResponse.of(e.getErrorStatus().getStatus(),e.getErrorStatus().getMessage()));
  }
}
