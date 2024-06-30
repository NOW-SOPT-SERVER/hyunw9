package org.sopt.sopt.member.presentation;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.sopt.member.presentation.dto.response.MemberResponse;
import org.sopt.sopt.member.application.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1")
public class MemberController {

  private final MemberService memberService;

  @GetMapping("member/{memberId}")
  public ResponseEntity<MemberResponse> getMember(
      @PathVariable Long memberId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(memberService.getMember(memberId));
  }

  @GetMapping("member/all")
  public ResponseEntity<List<MemberResponse>> getAllMember() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(memberService.getAllMember());
  }

//  @PostMapping("/member")
//  public ResponseEntity<UserJoinResponse> postMember(
//      @RequestBody @Valid MemberSignUpRequest memberSignUpRequest) {
//    UserJoinResponse userJoinResponse = memberService.createMember(memberSignUpRequest);
//    return ResponseEntity.status(HttpStatus.CREATED)
//        .body(userJoinResponse);
//  }

  @DeleteMapping("member/{memberId}")
  public ResponseEntity<?> deleteMember(@PathVariable Long memberId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(memberService.deleteMember(memberId));
  }

}

