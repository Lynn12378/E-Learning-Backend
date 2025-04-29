package com.demo.elearning.dto;

import lombok.Data;

@Data
public class ChapterDTO {
    private Long id;
    private String name;
    private int orderIndex;
    private Long courseId;
}