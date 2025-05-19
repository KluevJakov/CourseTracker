package ru.jafix.ct.service;

import ru.jafix.ct.entity.Member;
import ru.jafix.ct.entity.dto.MemberDto;

import java.util.List;
import java.util.UUID;

public interface MemberService {
    Member create(Member member);
    Member edit(Member member);
    MemberDto findById(UUID id);
    List<Member> findAll();
    void deleteById(UUID id);
}
