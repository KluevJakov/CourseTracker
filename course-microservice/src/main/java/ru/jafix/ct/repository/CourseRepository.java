package ru.jafix.ct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jafix.ct.entity.Course;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
}
