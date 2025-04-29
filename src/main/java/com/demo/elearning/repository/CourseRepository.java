package com.demo.elearning.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.elearning.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Course> findByCategory(String category, Pageable pageable);

    Page<Course> findByNameContainingIgnoreCaseAndCategory(String name, String category, Pageable pageable);
}