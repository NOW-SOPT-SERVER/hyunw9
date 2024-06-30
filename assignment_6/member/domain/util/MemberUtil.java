package org.sopt.sopt.member.domain.util;

import org.sopt.sopt.auth.presentation.dto.request.MemberAuthSignUpRequest;
import org.sopt.sopt.member.presentation.dto.response.MemberResponse;
import org.sopt.sopt.member.domain.Member;

public class MemberUtil {


  public static Member toEntity(MemberAuthSignUpRequest memberAuthSignUpRequest){
    return Member.builder()
        .name(memberAuthSignUpRequest.name())
        .userId(memberAuthSignUpRequest.userId())
        .part(memberAuthSignUpRequest.part())
        .age(memberAuthSignUpRequest.age())
        .build();
  }

  public static MemberResponse fromEntity(Member member){
    return MemberResponse.builder()
        .id(member.getId())
        .name(member.getName())
        .part(member.getPart())
        .age(member.getAge())
        .build();
  }

}
