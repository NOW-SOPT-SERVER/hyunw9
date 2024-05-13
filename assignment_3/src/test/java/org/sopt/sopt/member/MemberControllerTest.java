package org.sopt.sopt.member;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.sopt.sopt.entity.Part;
import org.sopt.sopt.common.dto.request.MemberRequest;
import org.sopt.sopt.repository.MemberRepository;
import org.sopt.sopt.service.MemberService;
import org.sopt.sopt.settings.ApiTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

public class MemberControllerTest extends ApiTest {

  @Autowired
  private MemberService memberService;

  @Autowired
  private MemberRepository memberRepository;

  @Nested
  @DisplayName("멤버 생성 테스트")
  public class CreateMember{

    public void createMemberSuccess() throws Exception{

      //given
      final var request = new MemberRequest("강현욱", Part.SERVER, 25);
      //when
      final var response = RestAssured
          .given()
          .log()
          .all()
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(request)
          .when()
          .post("/api/v1/member")
          .then()
          .log().all().extract();
      //then
      Assertions.assertThat(response.statusCode());
    }
  }
}
