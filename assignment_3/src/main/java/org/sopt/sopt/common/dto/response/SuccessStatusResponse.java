package org.sopt.sopt.common.dto.response;

import java.util.Optional;
import org.sopt.sopt.common.dto.response.responseEnum.SuccessMessage;

public record SuccessStatusResponse<T>(
    int status,
    String message,
    Optional<T> data
) {
  public static <T> SuccessStatusResponse<T> of(SuccessMessage successMessage){
    return new SuccessStatusResponse(successMessage.getStatus(), successMessage.getMessage(),Optional.empty());
  }

  public static <T> SuccessStatusResponse<T> of(SuccessMessage successMessage, T data){
    return new SuccessStatusResponse(successMessage.getStatus(), successMessage.getMessage(), Optional.of(data));
  }
}

