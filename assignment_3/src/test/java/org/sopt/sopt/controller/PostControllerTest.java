package org.sopt.sopt.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sopt.sopt.common.dto.request.PostCreateRequest;
import org.sopt.sopt.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PostController.class)
@AutoConfigureMockMvc
class PostControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean //SpyBean 으로 처리시 해당 Bean 의 종속성을 모두 처리해 줘야 한다. 지금 하는 테스트는 Valid test 이므로 service 계층을 MockBean으로 처리해도 상관 없다 !
  private PostService postService;

  @Autowired
  private ObjectMapper objectMapper;

  @Nested
  @DisplayName("게시물 생성 - 양식 검증 테스트")
  class CreatePost {

    @Test
    @DisplayName("게시물 작성 실패 - 제목 길이 미달")
    public void post_title_over_2() throws Exception {

      //given
      String request = objectMapper.writeValueAsString(new PostCreateRequest("a", "asdas"));

      //when
      mockMvc.perform(
          post("/api/v1/post")
              .content(request)
              .header("memberId", 1)
              .contentType(MediaType.APPLICATION_JSON)
      ).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("게시물 작성 실패 테스트 - 제목 글자 수 초과")
    public void 제목은_400글자_이하여야_한다() throws Exception {

      //given
      StringBuilder sb = new StringBuilder();
      int MAX_LENGTH = 400;
      for (int i = 0; i <= MAX_LENGTH; i++) {
        sb.append("i");
      }
      String request = objectMapper.writeValueAsString(new PostCreateRequest(sb.toString(), "내용"));

      //when
      mockMvc.perform(
              post("/api/v1/post")
                  .content(request)
                  .header("memberId", 1)
                  .contentType(MediaType.APPLICATION_JSON)
          )
          .andExpect(status().isBadRequest())
          .andDo(print());

    }

    @Test
    @DisplayName("블로그 생성 실패 테스트 - 내용 글자 수 미달")
    public void 내용은_1글자_이상이어야_한다() throws Exception {

      //given
      String request = objectMapper.writeValueAsString(new PostCreateRequest("","내용"));

      //when
      mockMvc.perform(
          post("/api/v1/post")
              .header("memberId",1)
              .content(request)
              .contentType(MediaType.APPLICATION_JSON)
      ).andExpect(status().isBadRequest())
          .andDo(print());
    }
  }
}
