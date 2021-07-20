package com.myservice.repository;

import com.myservice.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.*;

@Primary
@Repository
public class MemoryMemberRepository implements MemberRepository {

    private final Map<Long, Member> memberMap = new ConcurrentHashMap<>();
    private Long sequence;

    @Override
    public Member save(Member member) {
        memberMap.put(sequence++, member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = memberMap.get(id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        return null;
    }

    @Override
    public List<Member> findAll() {
        return memberMap.values().stream().collect(toList());
    }
}
