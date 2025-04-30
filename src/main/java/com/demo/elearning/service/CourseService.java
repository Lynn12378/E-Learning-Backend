package com.demo.elearning.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.elearning.dto.CourseDTO;
import com.demo.elearning.entity.Course;
import com.demo.elearning.mapper.CourseMapper;
import com.demo.elearning.repository.CourseRepository;

import jakarta.transaction.Transactional;

@Service
public class CourseService {

    private final CourseRepository courseRepo;
    private final CourseMapper courseMapper;
    private final ChapterService chapterService;

    public CourseService(CourseRepository courseRepo, CourseMapper courseMapper, ChapterService chapterService) {
        this.courseRepo = courseRepo;
        this.courseMapper = courseMapper;
        this.chapterService = chapterService;
    }

    public CourseDTO getCourseById(Long id) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("找不到該課程，id: " + id));
        return courseMapper.toDto(course);
    }

    public Page<CourseDTO> getCoursesWithPagination(String name, String category, int page, int size, String sort) {
        Sort sortOrder;
        if (sort.equals("desc")) {
            sortOrder = Sort.by(Sort.Order.desc("createdTime"));
        } else {
            sortOrder = Sort.by(Sort.Order.asc("createdTime"));
        }

        Pageable pageable = PageRequest.of(page, size, sortOrder);

        Page<Course> coursesPage;
        if (name != null && !name.trim().isEmpty() && category != null && !category.trim().isEmpty()) {
            coursesPage = courseRepo.findByNameContainingIgnoreCaseAndCategory(name, category, pageable);
        } else if (name != null && !name.trim().isEmpty()) {
            coursesPage = courseRepo.findByNameContainingIgnoreCase(name, pageable);
        } else if (category != null && !category.trim().isEmpty()) {
            coursesPage = courseRepo.findByCategory(category, pageable);
        } else {
            coursesPage = courseRepo.findAll(pageable);
        }

        return coursesPage.map(courseMapper::toDto);
    }

    public CourseDTO createCourse(CourseDTO dto) {
        dto.setCreatedTime(LocalDateTime.now());
        Course course = courseMapper.toEntity(dto);
        Course saved = courseRepo.save(course);
        return courseMapper.toDto(saved);
    }

    public CourseDTO updateCourse(Long id, CourseDTO dto) {
        Course existing = courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        existing.setName(dto.getName());
        existing.setCategory(dto.getCategory());
        existing.setImgPath(dto.getImgPath());
        existing.setDescription(dto.getDescription());

        return courseMapper.toDto(courseRepo.save(existing));
    }

    @Transactional
    public void deleteCourse(Long id) {
        if (!courseRepo.existsById(id)) {
            throw new RuntimeException("Course not found");
        }

        if (chapterService.existsByCourseId(id)) {
            chapterService.deleteChaptersByCourseId(id);
        }

        courseRepo.deleteById(id);
    }
}
