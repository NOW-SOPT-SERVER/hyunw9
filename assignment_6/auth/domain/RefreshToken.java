package org.sopt.sopt.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 1000L * 14 )
@AllArgsConstructor
@Getter
@Builder
public class RefreshToken {

  @Id
  private final long id;
  private String refreshToken;
}
