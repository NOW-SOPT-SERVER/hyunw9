package org.sopt.sopt.common.dto.response;

public record SuccessStatusResponse(

    int status,
    String message,
    Object data
) {

  public static SuccessStatusResponse of(SuccessMessageResponse successMessageResponse,Object data){
    return new SuccessStatusResponse(successMessageResponse.getStatus(),
        successMessageResponse.getMessage(),data);
  }
}
