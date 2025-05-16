package ru.jafix.ct.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jafix.ct.entity.Member;
import ru.jafix.ct.service.MemberService;

import java.util.UUID;

@RequestMapping("/api/members")
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Member member) {
        return ResponseEntity.ok(memberService.create(member));
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody @Valid Member member) {
        return ResponseEntity.ok(memberService.edit(member));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> findById(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(memberService.findById(uuid));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable("uuid") UUID uuid) {
        memberService.deleteById(uuid);
        return ResponseEntity.ok().build();
    }
}
