package org.sopt.sopt.member.util;

import org.sopt.sopt.member.entity.Member;
import org.sopt.sopt.member.dto.MemberReq;
import org.sopt.sopt.member.dto.MemberRes;

public class MemberUtil {

  public static Member toEntity(MemberReq memberReq){
    return Member.builder()
        .name(memberReq.name())
        .part(memberReq.part())
        .age(memberReq.age())
        .build();
  }

  public static MemberRes fromEntity(Member member){
    return MemberRes.builder()
        .id(member.getId())
        .name(member.getName())
        .part(member.getPart())
        .age(member.getAge())
        .build();
  }

}
