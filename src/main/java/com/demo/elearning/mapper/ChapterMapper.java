package com.demo.elearning.mapper;

import com.demo.elearning.dto.ChapterDTO;
import com.demo.elearning.entity.Chapter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChapterMapper {

    @Mapping(source = "course.id", target = "courseId")
    ChapterDTO toDto(Chapter chapter);

    @Mapping(source = "courseId", target = "course.id")
    Chapter toEntity(ChapterDTO dto);
}
