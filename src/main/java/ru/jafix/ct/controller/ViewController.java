package ru.jafix.ct.controller;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.jafix.ct.entity.dto.UserDto;
import ru.jafix.ct.service.UserService;

@Controller
public class ViewController {

    @Autowired
    private UserService userService;

    @GetMapping("/view")
    public String test(Model model) {
        model.addAttribute("auth", UserDto.builder().build());

        return "test";
    }

    @PostMapping("/reg")
    public String data(@ModelAttribute UserDto user, RedirectAttributes attributes) {
        try {
            userService.createUser(user);
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/view";
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    private static class TestDto {
        private int value;
        private String name;
        private boolean active;
    }
}
