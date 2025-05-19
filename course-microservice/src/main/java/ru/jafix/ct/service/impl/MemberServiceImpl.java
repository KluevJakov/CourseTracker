package ru.jafix.ct.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jafix.ct.entity.Member;
import ru.jafix.ct.entity.dto.MemberDto;
import ru.jafix.ct.entity.dto.UserDto;
import ru.jafix.ct.repository.MemberRepository;
import ru.jafix.ct.service.MemberService;
import ru.jafix.ct.service.feign.UserClient;

import java.util.List;
import java.util.UUID;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserClient userClient;

    @Override
    public Member create(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member edit(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public MemberDto findById(UUID id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        UserDto userFromAuth = userClient.getUserById(member.getUser_id());

        return MemberDto.builder()
                .id(member.getId())
                .user(userFromAuth)
                .course(member.getCourse())
                .build();
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
