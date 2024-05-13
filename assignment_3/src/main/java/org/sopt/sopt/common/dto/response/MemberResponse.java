package org.sopt.sopt.common.dto.response;

import lombok.Builder;
import org.sopt.sopt.entity.Part;

public record MemberResponse(

    Long id,
    String name,
    Part part,
    int age
) {

  @Builder
  public MemberResponse(Long id, String name, Part part, int age) {
    this.id = id;
    this.name = name;
    this.part = part;
    this.age = age;
  }

}
