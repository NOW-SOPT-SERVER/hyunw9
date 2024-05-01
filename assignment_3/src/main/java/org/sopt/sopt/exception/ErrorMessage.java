package org.sopt.sopt.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

  MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(),"ID에 해당하는 멤버가 존재하지 않습니다."),
  BLOG_NOT_FOUND(HttpStatus.NOT_FOUND.value(),"유저와 일치하는 블로그가 없습니다."),
  MEMBER_NOT_OWN(HttpStatus.NOT_FOUND.value(),"블로그가 없는 유저입니다."),
  POST_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "게시글이 없습니다.")
  ;
  private final int status;
  private final String message;
}
