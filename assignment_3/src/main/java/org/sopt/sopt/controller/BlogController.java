package org.sopt.sopt.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.sopt.common.dto.request.BlogCreateRequest;
import org.sopt.sopt.common.dto.request.BlogTitleUpdateRequest;
import org.sopt.sopt.common.dto.response.responseEnum.SuccessMessage;
import org.sopt.sopt.common.dto.response.SuccessStatusResponse;
import org.sopt.sopt.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BlogController {

  private final BlogService blogService;

  @PostMapping("/blog")
  public ResponseEntity<SuccessStatusResponse> createBlog(
      @RequestHeader Long memberId, //보안에 민감한 부분이기 때문
      @RequestBody BlogCreateRequest blogCreateRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(SuccessStatusResponse.of(
            SuccessMessage.BLOG_CREATED_SUCCESS,blogService.create(memberId, blogCreateRequest)));
  }

  @PatchMapping("/blog/{blogId}/title")
  public ResponseEntity updateBlogTitle(
      @PathVariable Long blogId,
      @RequestBody @Valid BlogTitleUpdateRequest blogTitleUpdateRequest
  ){
    blogService.updateTitle(blogId,blogTitleUpdateRequest);
    return ResponseEntity.noContent().build();
  }
}
