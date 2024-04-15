package org.sopt.sopt.member.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.sopt.sopt.member.dto.MemberReq;
import org.sopt.sopt.member.dto.MemberRes;
import org.sopt.sopt.member.dto.MessageRes;
import org.sopt.sopt.member.entity.Member;
import org.sopt.sopt.member.repository.MemberRepository;
import org.sopt.sopt.member.util.MemberUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  @Transactional
  public MemberRes createMember(@Valid MemberReq memberReq) {
    Member member = memberRepository.save(MemberUtil.toEntity(memberReq));

    return MemberUtil.fromEntity(member);
  }

  public MemberRes getMember(Long id) {

    Member foundMember = memberRepository.findById(id)
        .orElseThrow(()->
            new EntityNotFoundException("해당 ID 유저가 존재하지 않습니다."));
    return MemberUtil.fromEntity(foundMember);
  }

  @Transactional
  public MessageRes deleteMember(Long id) {
    Member member = memberRepository.findById(id)
        .orElseThrow(()->new EntityNotFoundException("해당 ID 유저가 존재하지 않습니다."));

    memberRepository.delete(member);

    //존재시 False , 미존재시 True
    boolean isDeleted = !memberRepository.existsById(id);
     if(isDeleted){
       return new MessageRes("삭제가 완료되었습니다.");
     }else{
       return new MessageRes("삭제할 수 없습니다.");
     }
  }

  public List<MemberRes> getAllMember(){

    List<Member> allMembers = memberRepository.findAll();
    if(allMembers.isEmpty()){
      throw new EntityNotFoundException("존재하는 유저가 없습니다.");
    }

    return allMembers.stream()
        .map(MemberUtil::fromEntity)
        .collect(Collectors.toList());
  }

}
