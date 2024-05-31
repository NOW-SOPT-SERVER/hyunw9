package org.sopt.sopt.auth.service;

import lombok.RequiredArgsConstructor;
import org.sopt.sopt.auth.type.CustomUserDetails;
import org.sopt.sopt.common.dto.response.responseEnum.ErrorStatus;
import org.sopt.sopt.entity.Member;
import org.sopt.sopt.exception.BusinessException;
import org.sopt.sopt.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member member = memberRepository.findByName(username)
        .orElseThrow(()->new BusinessException(ErrorStatus.MEMBER_NOT_FOUND));
    return new CustomUserDetails(member);
  }
}
