package org.sopt.sopt.common.dto.response.responseEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus {

  MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(),"ID에 해당하는 멤버가 존재하지 않습니다."),
  BLOG_NOT_FOUND(HttpStatus.NOT_FOUND.value(),"유저와 일치하는 블로그가 없습니다."),
  MEMBER_NOT_OWN(HttpStatus.NOT_FOUND.value(),"블로그가 없는 유저입니다."),
  POST_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "게시글이 없습니다."),
  USER_ALREADY_OWN(HttpStatus.CONFLICT.value(), "이미 블로그를 소유하고 있습니다."),
  JWT_UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), "사용자의 로그인 검증을 실패했습니다."),
  INVALID_REFRESH_TOKEN(HttpStatus.FORBIDDEN.value(), "올바르지 않은 리프레시 토큰입니다."),
  ;
  private final int status;
  private final String message;
}
