package com.myservice.domain.member;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository {

    Long save(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByLoginId(String loginId);

    List<Member> findAll();
}
