package org.sopt.sopt.repository;

import java.util.Optional;
import org.sopt.sopt.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog,Long> {

  Optional<Blog> findByMemberId(Long memberId);

}
