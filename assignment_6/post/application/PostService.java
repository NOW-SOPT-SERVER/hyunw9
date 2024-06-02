package org.sopt.sopt.post.application;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.sopt.sopt.blog.application.BlogService;
import org.sopt.sopt.post.presentation.dto.request.PostCreateRequest;
import org.sopt.sopt.post.presentation.dto.response.PostResponse;
import org.sopt.sopt.blog.domain.Blog;
import org.sopt.sopt.member.domain.Member;
import org.sopt.sopt.post.domain.Post;
import org.sopt.sopt.common.dto.response.responseEnum.ErrorStatus;
import org.sopt.sopt.common.exception.NotFoundException;
import org.sopt.sopt.post.persistence.PostRepository;
import org.sopt.sopt.member.application.MemberService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final BlogService blogService;
  private final PostRepository postRepository;
  private final MemberService memberService;

  public PostResponse postArticle(Long memberId, PostCreateRequest postCreateRequest) {

    Blog blog = blogService.findById(memberId);
    Member member = memberService.findById(memberId);

    Post post = postRepository.save(
        Post.of(blog, member, postCreateRequest.title(), postCreateRequest.content()));
    return PostResponse.from(post.getId(), member.getName(), post.getTitle(), post.getContent());
  }

  public List<PostResponse> getMemberAllArticle(Long memberId) {
    List<Post> postList = findAllByBlog(memberId);
    Member member = memberService.findById(memberId);
    String name = member.getName();

    return postList.stream()
        .map((post) -> PostResponse.from(post.getId(), name, post.getTitle(), post.getContent()))
        .collect(Collectors.toUnmodifiableList());
  }

  private List<Post> findAllByBlog(Long memberId) {
    Blog blog = blogService.findById(memberId);
    return postRepository.findAllByBlog(blog)
        .orElseThrow(() -> new NotFoundException(ErrorStatus.MEMBER_NOT_OWN));
  }

  public PostResponse getSpecificPost(Long postId) {
    Post post = findByPostId(postId);
    Long blogId = post.getBlog().getId();
    Member member = memberService.findById(blogId);
    String name = member.getName();
    return PostResponse.from(postId, name, post.getTitle(), post.getContent());

  }

  private Post findByPostId(Long postId) {
    return postRepository.findById(postId).orElseThrow(
        () -> new NotFoundException(ErrorStatus.POST_NOT_FOUND)
    );
  }
}
