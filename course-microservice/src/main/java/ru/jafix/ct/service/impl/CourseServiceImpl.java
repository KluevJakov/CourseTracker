package ru.jafix.ct.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jafix.ct.entity.Course;
import ru.jafix.ct.repository.CourseRepository;
import ru.jafix.ct.service.CourseService;

import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course edit(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course findById(UUID id) {
        return courseRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        courseRepository.deleteById(id);
    }
}
