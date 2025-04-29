package com.demo.elearning.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MaterialDTO {
    private Long id;
    private String name;
    private String category;
    private String path;
    private Integer orderIndex;
    private Long chapterId;
    private LocalDateTime createdTime;
}
