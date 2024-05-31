package org.sopt.sopt.common.dto.response;

import java.util.Optional;
import org.sopt.sopt.common.dto.response.responseEnum.SuccessStatus;

public record SuccessStatusResponse<T>(
    int status,
    String message,
    Optional<T> data
) {
  public static <T> SuccessStatusResponse<T> of(SuccessStatus successStatus){
    return new SuccessStatusResponse(successStatus.getStatus(), successStatus.getMessage(),Optional.empty());
  }

  public static <T> SuccessStatusResponse<T> of(SuccessStatus successStatus, T data){
    return new SuccessStatusResponse(successStatus.getStatus(), successStatus.getMessage(), Optional.of(data));
  }
}

