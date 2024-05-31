package org.sopt.sopt.util;

import org.sopt.sopt.common.dto.request.MemberCreateRequest;
import org.sopt.sopt.common.dto.response.MemberResponse;
import org.sopt.sopt.entity.Member;

public class MemberUtil {


  public static Member toEntity(MemberCreateRequest memberCreateRequest){
    return Member.builder()
        .name(memberCreateRequest.name())
        .userId(memberCreateRequest.userId())
        .part(memberCreateRequest.part())
        .age(memberCreateRequest.age())
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
