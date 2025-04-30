package com.demo.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.elearning.entity.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    List<Chapter> findByCourseId(Long courseId);

    void deleteByCourseId(Long courseId);

    boolean existsByCourseId(Long courseId);
}