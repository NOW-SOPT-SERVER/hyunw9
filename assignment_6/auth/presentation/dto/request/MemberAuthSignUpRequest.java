package org.sopt.sopt.auth.presentation.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import org.sopt.sopt.common.entity.Part;

public record MemberAuthSignUpRequest(

    @NotNull
    String name,
    String userId,
    String password,
    Part part,
    @Max(99)
    int age
) {

  public MemberAuthSignUpRequest(String name,String userId, String password,Part part, int age) {
    this.name = name;
    this.userId = userId;
    this.password = password;
    this.part = part;
    this.age = age;
  }
}
