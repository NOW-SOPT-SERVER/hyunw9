package org.sopt.sopt.common.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import org.sopt.sopt.entity.Part;

public record MemberRequest(

    @NotNull
    String name,
    Part part,
    @Max(99)
    int age
) {

  public MemberRequest(String name,Part part, int age) {
    this.name = name;
    this.part = part;
    this.age = age;
  }
}
