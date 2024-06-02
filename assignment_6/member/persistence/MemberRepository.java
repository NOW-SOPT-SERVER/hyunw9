package org.sopt.sopt.member.persistence;

import java.util.Optional;
import org.sopt.sopt.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByName(String username);

  Optional<Member> findByUserId(String userId);
}
