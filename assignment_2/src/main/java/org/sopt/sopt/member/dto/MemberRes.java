package org.sopt.sopt.member.dto;

import lombok.Builder;
import org.sopt.sopt.member.entity.Part;

public record MemberRes(

    Long id,
    String name,
    Part part,
    int age
) {

  @Builder
  public MemberRes(Long id, String name, Part part, int age) {
    this.id = id;
    this.name = name;
    this.part = part;
    this.age = age;
  }

}
