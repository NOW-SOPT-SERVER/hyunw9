package org.sopt.sopt.common.dto.response;

public record PostResponse(

    Long postId,
    String name,
    String title,
    String content

) {

  public static PostResponse from(Long postId, String name, String title, String content){
    return new PostResponse(postId, name, title, content);
  }

}
