package org.sopt.sopt.global.exception;

import jakarta.persistence.EntityNotFoundException;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e){
    ErrorResponse errorResponse = ErrorResponse.builder()
        .errorCode("NOT_FOUND")
        .errorMessage(e.getMessage())
        .date(new Date())
        .build();
    return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);

  }

}
