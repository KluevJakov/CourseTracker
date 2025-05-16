package ru.jafix.ct.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jafix.ct.entity.Course;
import ru.jafix.ct.service.CourseService;

import java.util.UUID;

@RequestMapping("/api/courses")
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Course course) {
        return ResponseEntity.ok(courseService.create(course));
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody @Valid Course course) {
        return ResponseEntity.ok(courseService.edit(course));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> findById(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(courseService.findById(uuid));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable("uuid") UUID uuid) {
        courseService.deleteById(uuid);
        return ResponseEntity.ok().build();
    }
}
