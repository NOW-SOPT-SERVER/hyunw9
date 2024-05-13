package org.sopt.sopt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.sopt.common.dto.request.PostCreateRequest;
import org.sopt.sopt.common.dto.response.responseEnum.SuccessMessage;
import org.sopt.sopt.common.dto.response.SuccessStatusResponse;
import org.sopt.sopt.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Validated
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @PostMapping("/post")
  public ResponseEntity<?> createArticle(
      @RequestHeader Long memberId,
      @RequestBody @Valid PostCreateRequest postCreateRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(SuccessStatusResponse.of(
        SuccessMessage.POST_WRITE_SUCCESS,postService.postArticle(memberId, postCreateRequest)
    ));
  }

  @GetMapping("/post/all")
  public ResponseEntity<?> getAllMemberPost(
      @RequestHeader Long memberId
  ) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(SuccessStatusResponse.of(
        SuccessMessage.POST_GET_SUCCESS,postService.getMemberAllArticle(memberId)
    ));
  }

  @GetMapping("/{postId}")
  public ResponseEntity<?> getSpecificPost(
      @PathVariable Long postId
  ){
    return ResponseEntity.status(HttpStatus.OK)
        .body(SuccessStatusResponse.of(
        SuccessMessage.POST_GET_SUCCESS,postService.getSpecificPost(postId)
    ));
  }
}
