package com.demo.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.elearning.dto.ChapterDTO;
import com.demo.elearning.entity.Chapter;
import com.demo.elearning.service.ChapterService;

@RestController
@RequestMapping("/chapters")
public class ChapterController {

    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping
    public List<Chapter> getAll() {
        return chapterService.getAllChapters();
    }

    @GetMapping("/course/{course_id}")
    public List<Chapter> getByCourseId(@PathVariable("course_id") Long courseId) {
        return chapterService.getChaptersByCourseId(courseId);
    }

    @PostMapping
    public ChapterDTO create(@RequestBody ChapterDTO dto) {
        return chapterService.createChapter(dto);
    }

    @PutMapping("/{id}")
    public Chapter update(@PathVariable Long id, @RequestBody Chapter updated) {
        return chapterService.updateChapter(updated);
    }

    @PutMapping("/")
    public List<Chapter> updateAllChapters(@RequestBody List<Chapter> chapters) {
        return chapterService.updateAllChapters(chapters);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        chapterService.deleteChapter(id);
    }
}