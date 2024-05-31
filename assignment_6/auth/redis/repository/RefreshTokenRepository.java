package org.sopt.sopt.auth.redis.repository;

import java.util.Optional;
import org.sopt.sopt.auth.redis.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {

  Optional<RefreshToken> findByRefreshToken(final String refreshToken);

  Optional<RefreshToken> findById(final Long id);
}
