package org.sopt.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.sopt.common.dto.request.BlogCreateRequest;
import org.sopt.sopt.common.dto.request.BlogTitleUpdateRequest;
import org.sopt.sopt.common.dto.response.MessageResponse;
import org.sopt.sopt.entity.Blog;
import org.sopt.sopt.entity.Member;
import org.sopt.sopt.exception.ErrorMessage;
import org.sopt.sopt.exception.NotFoundException;
import org.sopt.sopt.repository.BlogRepository;
import org.sopt.sopt.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlogService {

  private final BlogRepository blogRepository;
  private final MemberService memberService;
  private final MemberRepository memberRepository;

  public String create(Long memberId, BlogCreateRequest blogCreateRequest) {

    Member member = memberService.findById(memberId);
    Blog blog = blogRepository.save(Blog.create(member, blogCreateRequest));

    return blog.getId().toString();
  }

  @Transactional
  public MessageResponse updateTitle(Long memberId, BlogTitleUpdateRequest blogTitleUpdateRequest) {

    Blog blog = findById(memberId);
    blog.updateTitle(blogTitleUpdateRequest.title());

    return MessageResponse.builder()
        .message("성공적으로 수정되었습니다.")
        .build();
  }

  public Blog findById(Long memberId) {
    return blogRepository.findById(memberId).orElseThrow(
        () -> new NotFoundException(
            ErrorMessage.BLOG_NOT_FOUND));
  }

  public String getUsernameForBlog(Long memberId) {
    return memberRepository.findById(memberId)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND)).getName();
  }

}
