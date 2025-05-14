package ru.jafix.ct.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jafix.ct.entity.Member;
import ru.jafix.ct.repository.MemberRepository;
import ru.jafix.ct.service.MemberService;

import java.util.List;
import java.util.UUID;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member create(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member edit(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member findById(UUID id) {
        return memberRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        memberRepository.deleteById(id);
    }
}
