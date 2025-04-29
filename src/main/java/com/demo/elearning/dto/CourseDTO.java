package com.demo.elearning.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CourseDTO {
    private Long id;
    private String name;
    private String category;
    private String imgPath;
    private String description;
    private LocalDateTime createdTime;
}