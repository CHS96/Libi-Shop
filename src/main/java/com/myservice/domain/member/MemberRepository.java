package com.myservice.domain.member;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findById(Long id);

    List<Member> findAll();

    void clearStore();

    Optional<Member> findByLoginId(String loginId);
}
