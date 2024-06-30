package org.sopt.sopt.blog.presentation;


import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.sopt.sopt.auth.security.handler.PrincipalHandler;
import org.sopt.sopt.blog.presentation.dto.request.BlogCreateRequest;
import org.sopt.sopt.blog.presentation.dto.request.BlogTitleUpdateRequest;
import org.sopt.sopt.common.dto.response.SuccessStatusResponse;
import org.sopt.sopt.blog.application.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BlogController {

  private final BlogService blogService;
  private final PrincipalHandler principalHandler;

  @PostMapping("/blog")
  public ResponseEntity<SuccessStatusResponse> createBlog(
      @ModelAttribute BlogCreateRequest blogCreateRequest) {
    return ResponseEntity.created(URI.create(blogService.create(
        principalHandler.getUserIdFromPrincipal(), blogCreateRequest
    ))).build();
  }

  @PatchMapping("/blog/{blogId}/title")
  public ResponseEntity updateBlogTitle(
      @PathVariable Long blogId,
      @RequestBody @Valid BlogTitleUpdateRequest blogTitleUpdateRequest
  ) {
    blogService.updateTitle(blogId, blogTitleUpdateRequest);
    return ResponseEntity.noContent().build();
  }
}
