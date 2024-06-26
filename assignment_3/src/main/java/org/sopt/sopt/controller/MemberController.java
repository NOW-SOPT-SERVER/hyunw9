package org.sopt.sopt.controller;


import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.sopt.common.dto.response.MemberResponse;
import org.sopt.sopt.common.dto.request.MemberRequest;
import org.sopt.sopt.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/member")
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/{memberId}")
  public ResponseEntity<MemberResponse> getMember(
      @PathVariable Long memberId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(memberService.getMember(memberId));
  }

  @GetMapping("/all")
  public ResponseEntity<List<MemberResponse>> getAllMember() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(memberService.getAllMember());
  }

  @PostMapping("/")
  public ResponseEntity<MemberResponse> postMember(
      @RequestBody @Valid MemberRequest memberRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(memberService.createMember(memberRequest));
  }

  @DeleteMapping("/{memberId}")
  public ResponseEntity<?> deleteMember(@PathVariable Long memberId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(memberService.deleteMember(memberId));
  }

}

