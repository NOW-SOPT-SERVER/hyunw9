package org.sopt.sopt.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.sopt.common.entity.BaseEntity;
import org.sopt.sopt.common.entity.Part;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  private String userId;

  private String password;

  private String name;

  @Enumerated(EnumType.STRING)
  private Part part;

  private int age;

  @Builder
  public Member(String name, String userId, String password, Part part, int age) {
    this.name = name;
    this.userId = userId;
    this.password = password;
    this.part = part;
    this.age = age;
  }
}
