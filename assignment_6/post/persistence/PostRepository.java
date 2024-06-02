package org.sopt.sopt.post.persistence;

import java.util.List;
import java.util.Optional;
import org.sopt.sopt.blog.domain.Blog;
import org.sopt.sopt.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

  Optional<List<Post>> findAllByBlog(Blog blog);

}
