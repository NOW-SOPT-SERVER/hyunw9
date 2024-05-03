package org.sopt.sopt.common.dto.response.responseEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessMessage {

  BLOG_CREATED_SUCCESS(HttpStatus.CREATED.value(), "블로그 생성이 완료되었습니다."),
  POST_WRITE_SUCCESS(HttpStatus.CREATED.value(),"게시글 작성이 완료되었습니다."),
  POST_GET_SUCCESS(HttpStatus.OK.value(), "게시글 조회 성공");
  private final int status;
  private final String message;
}
