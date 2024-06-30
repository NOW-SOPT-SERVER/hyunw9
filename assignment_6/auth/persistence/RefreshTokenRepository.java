package org.sopt.sopt.auth.persistence;

import java.util.Optional;
import org.sopt.sopt.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {

  Optional<RefreshToken> findByRefreshToken(final String refreshToken);

  Optional<RefreshToken> findById(final Long id);
}
