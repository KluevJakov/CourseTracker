package ru.jafix.ct.service;

import ru.jafix.ct.entity.Course;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    Course create(Course course);
    Course edit(Course course);
    Course findById(UUID id);
    List<Course> findAll();
    void deleteById(UUID id);
}
