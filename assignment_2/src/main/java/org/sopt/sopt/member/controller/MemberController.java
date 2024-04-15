package org.sopt.sopt.member.controller;


import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.sopt.member.dto.MemberReq;
import org.sopt.sopt.member.dto.MemberRes;
import org.sopt.sopt.member.service.MemberService;
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
  public ResponseEntity<MemberRes> getMember(
      @PathVariable Long memberId) {
    return new ResponseEntity<>(memberService.getMember(memberId), HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<MemberRes>> getAllMember() {
    return new ResponseEntity<>(memberService.getAllMember(),HttpStatus.OK);
  }

  @PostMapping("/")
  public ResponseEntity<MemberRes> postMember(@RequestBody @Valid MemberReq memberReq) {
    return new ResponseEntity<>(memberService.createMember(memberReq), HttpStatus.CREATED);
  }

  @DeleteMapping("/{memberId}")
  public ResponseEntity<?> deleteMember(@PathVariable Long memberId) {
    return new ResponseEntity<>(memberService.deleteMember(memberId), HttpStatus.OK);
  }

}

