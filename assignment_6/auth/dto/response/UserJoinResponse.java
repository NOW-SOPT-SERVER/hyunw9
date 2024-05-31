package org.sopt.sopt.auth.dto.response;

public record UserJoinResponse(
    String accessToken,
    String refreshToken
) {

  public static UserJoinResponse of(
      String accessToken,
      String refreshToken
  ) {
    return new UserJoinResponse(accessToken, refreshToken);
  }
}

