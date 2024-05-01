package org.sopt.sopt.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.sopt.sopt.exception.ErrorMessage;
import org.sopt.sopt.common.dto.response.MemberResponse;
import org.sopt.sopt.common.dto.response.MessageResponse;
import org.sopt.sopt.entity.Member;
import org.sopt.sopt.common.dto.request.MemberRequest;
import org.sopt.sopt.exception.NotFoundException;
import org.sopt.sopt.repository.MemberRepository;
import org.sopt.sopt.util.MemberUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  @Transactional
  public MemberResponse createMember(@Valid MemberRequest memberRequest) {
    Member member = memberRepository.save(MemberUtil.toEntity(memberRequest));

    return MemberUtil.fromEntity(member);
  }

  public MemberResponse getMember(Long id) {

    Member foundMember = memberRepository.findById(id)
        .orElseThrow(() ->
            new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND));
    return MemberUtil.fromEntity(foundMember);
  }

  @Transactional
  public MessageResponse deleteMember(Long id) {
    Member member = memberRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("해당 ID 유저가 존재하지 않습니다."));

    memberRepository.delete(member);

    //존재시 False , 미존재시 True
    boolean isDeleted = !memberRepository.existsById(id);
    if (isDeleted) {
      return new MessageResponse("삭제가 완료되었습니다.");
    } else {
      return new MessageResponse("삭제할 수 없습니다.");
    }
  }

  public List<MemberResponse> getAllMember() {

    List<Member> allMembers = memberRepository.findAll();
    if (allMembers.isEmpty()) {
      throw new EntityNotFoundException("존재하는 유저가 없습니다.");
    }

    return allMembers.stream()
        .map(MemberUtil::fromEntity)
        .collect(Collectors.toUnmodifiableList());
  }

  public Member findById(Long memberId) {
    return memberRepository.findById(memberId).orElseThrow(
        () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND));
  }

}
