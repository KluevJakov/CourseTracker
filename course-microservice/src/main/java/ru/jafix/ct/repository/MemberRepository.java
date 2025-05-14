package ru.jafix.ct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jafix.ct.entity.Member;

import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
}
