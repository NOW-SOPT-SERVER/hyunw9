package org.sopt.sopt.post;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sopt.sopt.common.dto.request.BlogCreateRequest;
import org.sopt.sopt.common.dto.request.PostCreateRequest;
import org.sopt.sopt.common.dto.response.PostResponse;
import org.sopt.sopt.entity.Blog;
import org.sopt.sopt.entity.Member;
import org.sopt.sopt.entity.Part;
import org.sopt.sopt.entity.Post;
import org.sopt.sopt.repository.PostRepository;
import org.sopt.sopt.service.BlogService;
import org.sopt.sopt.service.PostService;

@DisplayName("게시물 단위 테스트")
@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

  @Mock
  private BlogService blogService;

  @Mock
  private PostRepository postRepository;

  @InjectMocks
  private PostService postService;

  @Nested
  @DisplayName("게시물 생성 관련 테스트")
  class 게시물_생성_테스트 {

    private Long memberId;
    private Member member;
    private Blog blog;
    private PostCreateRequest postCreateRequest;
    private Post post;

    @BeforeEach
    public void setup() {
      memberId = 1L;
      String memberName = "John Doe";
      member = new Member(memberName, Part.IOS, 25);

      String title = "test Title";
      String content = "test Content";

      postCreateRequest = new PostCreateRequest(title, content);
      blog = Blog.create(member, new BlogCreateRequest("test blog", "test desc"));
      post = new Post(blog, member, title, content);
    }

    @Test
    @DisplayName("게시물 생성 성공 - 정상 생성")
    public void 게시물_생성_테스트() {
      //given
      when(blogService.findById(memberId)).thenReturn(blog);
      when(postRepository.save(any(Post.class))).thenReturn(post);

      //when
      PostResponse postResponse = postService.postArticle(1L, postCreateRequest);

      //then
      assertThat(postResponse.postId()).isEqualTo((post.getId()));
      assertThat(postResponse.name()).isEqualTo(member.getName());
      assertThat(postResponse.content()).isEqualTo(post.getContent());
      assertThat(postResponse.title()).isEqualTo(post.getTitle());
    }



//    @Test
//    @DisplayName("게시물 생성 실패 - 존재하지 않는 유저")
//    public void 존재하지_않는_유저로_게시글_생성(){
//
//      //given
//      Long memberId = 3L;
//      Member newMember = new Member("Jane DOe",Part.SERVER,26);
//      when(blogService.findById(memberId)).thenReturn(blog);
//      when(postRepository.save(post)).thenReturn(post);
//
//      //when
//      assertThatThrownBy(() -> postService.postArticle(memberId,postCreateRequest))
//          .isInstanceOf(NotFoundException.class)
//          .hasMessageContaining("블로그가 없는 유저입니다.");
//    }
  }
}
