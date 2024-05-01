package org.sopt.sopt.util;

import org.sopt.sopt.common.dto.response.MemberResponse;
import org.sopt.sopt.entity.Member;
import org.sopt.sopt.common.dto.request.MemberRequest;

public class MemberUtil {

  public static Member toEntity(MemberRequest memberRequest){
    return Member.builder()
        .name(memberRequest.name())
        .part(memberRequest.part())
        .age(memberRequest.age())
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
