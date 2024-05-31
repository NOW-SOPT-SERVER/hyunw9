package org.sopt.sopt.repository;

import java.util.List;
import java.util.Optional;
import org.sopt.sopt.entity.Blog;
import org.sopt.sopt.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

  Optional<List<Post>> findAllByBlog(Blog blog);

}
