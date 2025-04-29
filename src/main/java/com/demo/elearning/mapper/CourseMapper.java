package com.demo.elearning.mapper;

import org.mapstruct.Mapper;

import com.demo.elearning.dto.CourseDTO;
import com.demo.elearning.entity.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDTO toDto(Course course);

    Course toEntity(CourseDTO dto);
}