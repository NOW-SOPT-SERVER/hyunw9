package org.sopt.sopt.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.sopt.sopt.auth.JwtTokenProvider;
import org.sopt.sopt.auth.dto.response.UserJoinResponse;
import org.sopt.sopt.auth.token.UserAuthentication;
import org.sopt.sopt.common.dto.response.responseEnum.ErrorStatus;
import org.sopt.sopt.common.dto.response.MemberResponse;
import org.sopt.sopt.common.dto.response.MessageResponse;
import org.sopt.sopt.entity.Member;
import org.sopt.sopt.common.dto.request.MemberCreateRequest;
import org.sopt.sopt.exception.NotFoundException;
import org.sopt.sopt.repository.MemberRepository;
import org.sopt.sopt.util.MemberUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public UserJoinResponse createMember(@Valid MemberCreateRequest memberCreateRequest) {
    Member member = memberRepository.save(
        Member.builder()
            .userId(memberCreateRequest.userId())
            .password(passwordEncoder.encode(memberCreateRequest.password()))
            .part(memberCreateRequest.part())
            .name(memberCreateRequest.name())
            .age(memberCreateRequest.age())
            .build()
    );
    Long memberId = member.getId();
    String accessToken = jwtTokenProvider.issueAccessToken(
        UserAuthentication.createUserAuthentication(memberId));
    String refreshToken = jwtTokenProvider.issueRefreshToken(
        UserAuthentication.createUserAuthentication(memberId)
    );
//    String refreshTokenn = jwtTokenProvider.issueRefreshToken()
    return UserJoinResponse.of(accessToken,refreshToken);
  }

  public MemberResponse getMember(Long id) {

    Member foundMember = memberRepository.findById(id)
        .orElseThrow(() ->
            new NotFoundException(ErrorStatus.MEMBER_NOT_FOUND));
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
        () -> new NotFoundException(ErrorStatus.MEMBER_NOT_FOUND));
  }

}
