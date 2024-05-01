package org.sopt.sopt.member.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import org.sopt.sopt.member.entity.Part;

public record MemberReq(

    @NotNull
    String name,
    Part part,
    @Max(99)
    int age
) {

  public MemberReq(String name,Part part, int age) {
    this.name = name;
    this.part = part;
    this.age = age;
  }
}
