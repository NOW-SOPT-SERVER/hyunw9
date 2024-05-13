package org.sopt.sopt.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sopt.sopt.common.dto.request.PostCreateRequest;
import org.sopt.sopt.repository.BlogRepository;
import org.sopt.sopt.repository.MemberRepository;
import org.sopt.sopt.service.BlogService;
import org.sopt.sopt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BlogController.class)
@AutoConfigureMockMvc
class BlogControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  private BlogService blogService;

  @SpyBean
  private MemberService memberService;

  @MockBean
  private BlogRepository blogRepository;

  @MockBean
  private MemberRepository memberRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void updateBlogTitle() {
  }

  @Nested
  @DisplayName("블로그 생성 실패 테스트")
  class CreateBlog {


    @Test
    @DisplayName("블로그 생성 실패 테스트 - 제목 글자 수 미달")
    public void 제목은_2글자_이상이어야_한다() throws Exception {
      //given
      String request = objectMapper.writeValueAsString(new PostCreateRequest("gd", "dg"));

      //when
      mockMvc.perform(

              post("/api/v1/blog")

                  .content(request).header("memberId", 123).contentType(MediaType.APPLICATION_JSON)
          ).andExpect(status().isNotFound())
          .andDo(print());

    }


  }
}
