package org.sopt.sopt.member.dto;


import lombok.Builder;

public record MessageRes(
    String message
) {

  @Builder
  public MessageRes(String message) {
    this.message = message; 
  }
}
