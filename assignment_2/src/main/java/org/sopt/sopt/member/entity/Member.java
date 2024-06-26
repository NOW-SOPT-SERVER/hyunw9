package org.sopt.sopt.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  private String name;

  @Enumerated(EnumType.STRING)
  private Part part;

  private int age;

  @Builder
  public Member(String name, Part part, int age) {
    this.name = name;
    this.part = part;
    this.age = age;
  }
}
