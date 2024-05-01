package org.sopt.sopt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.sopt.common.dto.request.BlogCreateRequest;

@Entity
@Getter
@NoArgsConstructor
public class Blog extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  private Member member;

  private String title;

  private String description;

  public Blog(Member member, String title, String description) {
    this.member = member;
    this.title = title;
    this.description = description;
  }

  public static Blog create(Member member, BlogCreateRequest blogCreateRequest) {
    return new Blog(member, blogCreateRequest.title(), blogCreateRequest.description());
  }

  public void updateTitle(String title){
    this.title = title;
  }
}
