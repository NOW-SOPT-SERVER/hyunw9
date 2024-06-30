package org.sopt.sopt.auth.application;

import lombok.RequiredArgsConstructor;
import org.sopt.sopt.auth.security.bridge.JwtHandlerAdapter;
import org.sopt.sopt.auth.security.bridge.Token;
import org.sopt.sopt.auth.application.exception.UnauthorizedException;
import org.sopt.sopt.member.domain.Member;
import org.sopt.sopt.auth.domain.RefreshToken;
import org.sopt.sopt.auth.persistence.RefreshTokenRepository;
import org.sopt.sopt.auth.presentation.dto.request.MemberAuthReissueRequest;
import org.sopt.sopt.auth.presentation.dto.request.MemberAuthSignInRequest;
import org.sopt.sopt.auth.presentation.dto.request.MemberAuthSignUpRequest;
import org.sopt.sopt.auth.presentation.dto.response.MemberAuthServiceResponse;
import org.sopt.sopt.common.dto.response.responseEnum.ErrorStatus;
import org.sopt.sopt.common.exception.NotFoundException;
import org.sopt.sopt.member.persistence.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final MemberRepository memberRepository;
  private final JwtHandlerAdapter jwtHandlerAdapter;
  private final RefreshTokenRepository refreshTokenRepository;
  private final PasswordEncoder passwordEncoder;

  public MemberAuthServiceResponse signUp(MemberAuthSignUpRequest request){

    String encodedPassword = passwordEncoder.encode(request.password());

    Member member = Member.builder()
        .name(request.name())
        .userId(request.userId())
        .password(encodedPassword)
        .age(request.age())
        .part(request.part())
        .build();
    Member savedMember = memberRepository.save(member);
    Token issuedToken = issueTokenAndStoreRefreshToken(savedMember.getId());
    return MemberAuthServiceResponse.of(issuedToken.accessToken(), issuedToken.refreshToken());
  }

  public MemberAuthServiceResponse signIn(MemberAuthSignInRequest request){
    Member findMember = findMemberBy(request.userId());
    Token issuedToken = issueTokenAndStoreRefreshToken(findMember.getId());
    return MemberAuthServiceResponse.of(issuedToken.accessToken(), issuedToken.refreshToken());
  }

  public MemberAuthServiceResponse reissue(MemberAuthReissueRequest request){
    try{
      jwtHandlerAdapter.validateRefreshToken(request.refreshToken());
      Long memberId = jwtHandlerAdapter.getSubject(request.refreshToken());
      RefreshToken findRefreshToken = findRefreshTokenBy(memberId);
      jwtHandlerAdapter.equalsRefreshToken(request.refreshToken(), findRefreshToken.getRefreshToken());
      Token issuedToken = issueTokenAndStoreRefreshToken(memberId);
      return MemberAuthServiceResponse.of(issuedToken.accessToken(), issuedToken.refreshToken());
    }catch (UnauthorizedException e){
      //sign out 처리
      throw e;
    }
  }

  private RefreshToken findRefreshTokenBy(Long userId) {
    return refreshTokenRepository.findById(userId)
        .orElseThrow(() -> new UnauthorizedException(ErrorStatus.EXPIRED_REFRESH_TOKEN));
  }

  private Member findMemberBy(String userId) {
    return memberRepository.findByUserId(userId)
        .orElseThrow(() -> new NotFoundException(ErrorStatus.MEMBER_NOT_FOUND));
  }

  private Token issueTokenAndStoreRefreshToken(Long userId) {
    Token issuedToken = jwtHandlerAdapter.issueToken(userId);
    RefreshToken refreshToken = RefreshToken.builder()
        .id(userId)
        .refreshToken(issuedToken.refreshToken())
        .build();
    refreshTokenRepository.save(refreshToken);
    return issuedToken;
  }


}
