package com.demo.elearning.mapper;

import com.demo.elearning.dto.MaterialDTO;
import com.demo.elearning.entity.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    @Mapping(source = "chapter.id", target = "chapterId")
    MaterialDTO toDto(Material material);

    @Mapping(source = "chapterId", target = "chapter.id")
    Material toEntity(MaterialDTO dto);
}
