package org.sopt.sopt.auth.redis.domain;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 1000L * 14 )
@AllArgsConstructor
@Getter
@Builder
public class RefreshToken {

  @Id
  private UUID id;

  @Indexed
  private String accessToken;

  private String refreshToken;

  private String userName;

  private long expiryDate;

  public static RefreshToken of(
      final UUID id,
      final String refreshToken,
      final String accessToken,
      final String userName,
      final long expiryDate
  ){
    return RefreshToken.builder()
        .id(id)
        .refreshToken(refreshToken)
        .accessToken(accessToken)
        .userName(userName)
        .expiryDate(expiryDate)
        .build();
  }

  public void setAccessToken(String newAccessToken) {
    this.accessToken = newAccessToken;
  }
}
