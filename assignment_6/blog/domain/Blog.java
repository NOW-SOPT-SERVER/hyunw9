package org.sopt.sopt.blog.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.sopt.member.domain.Member;
import org.sopt.sopt.common.entity.BaseEntity;

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

  private String imageUrl;

  public Blog(Member member, String title, String imageUrl, String description) {
    this.member = member;
    this.title = title;
    this.imageUrl= imageUrl;
    this.description = description;
  }

  public static Blog create(Member member, String title, String description, String imageUrl) {
    return new Blog(member, title,description,imageUrl);
  }

  public void updateTitle(String title){
    this.title = title;
  }
}
