package org.sopt.sopt.blog.persistance;

import java.util.Optional;
import org.sopt.sopt.blog.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog,Long> {

  Optional<Blog> findByMemberId(Long memberId);

}
