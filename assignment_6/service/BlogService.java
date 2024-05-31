package org.sopt.sopt.service;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.sopt.sopt.aws.service.S3Service;
import org.sopt.sopt.common.dto.request.BlogCreateRequest;
import org.sopt.sopt.common.dto.request.BlogTitleUpdateRequest;
import org.sopt.sopt.common.dto.response.MessageResponse;
import org.sopt.sopt.entity.Blog;
import org.sopt.sopt.entity.Member;
import org.sopt.sopt.common.dto.response.responseEnum.ErrorStatus;
import org.sopt.sopt.exception.IllegalStateException;
import org.sopt.sopt.exception.NotFoundException;
import org.sopt.sopt.repository.BlogRepository;
import org.sopt.sopt.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlogService {

  private static final String BLOG_S3_UPLOAD_FOLDER = "blog/";
  private final BlogRepository blogRepository;
  private final MemberService memberService;
  private final MemberRepository memberRepository;
  private final S3Service s3Service;

  public String create(Long memberId, BlogCreateRequest blogCreateRequest) {

    Member member = memberService.findById(memberId);
    existsById(memberId);
    try {
      Blog blog = blogRepository.save(
          Blog.create(member, blogCreateRequest.title(), blogCreateRequest.description(),
              s3Service.uploadImage(BLOG_S3_UPLOAD_FOLDER, blogCreateRequest.image())));
      return blog.getId().toString();
    } catch (RuntimeException | IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Transactional
  public MessageResponse updateTitle(Long memberId, BlogTitleUpdateRequest blogTitleUpdateRequest) {

    Blog blog = findById(memberId);
    blog.updateTitle(blogTitleUpdateRequest.title());

    return MessageResponse.builder()
        .message("성공적으로 수정되었습니다.")
        .build();
  }

  public String getUsernameForBlog(Long memberId) {
    return memberRepository.findById(memberId)
        .orElseThrow(() -> new NotFoundException(ErrorStatus.MEMBER_NOT_FOUND)).getName();
  }

  public Blog findById(Long memberId) {
    return blogRepository.findById(memberId).orElseThrow(
        () -> new NotFoundException(
            ErrorStatus.BLOG_NOT_FOUND));
  }

  private void existsById(Long memberId) {
    if (blogRepository.existsById(memberId)) {
      throw new IllegalStateException(ErrorStatus.USER_ALREADY_OWN);
    }
  }
}
