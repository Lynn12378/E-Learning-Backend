package com.demo.elearning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.elearning.dto.ChapterDTO;
import com.demo.elearning.entity.Chapter;
import com.demo.elearning.entity.Material;
import com.demo.elearning.mapper.ChapterMapper;
import com.demo.elearning.repository.ChapterRepository;

import jakarta.transaction.Transactional;

@Service
public class ChapterService {

    private final ChapterRepository chapterRepo;
    private final ChapterMapper chapterMapper;
    private final MaterialService materialService;

    public ChapterService(ChapterRepository chapterRepo, ChapterMapper chapterMapper, MaterialService materialService) {
        this.chapterRepo = chapterRepo;
        this.chapterMapper = chapterMapper;
        this.materialService = materialService;
    }

    public List<Chapter> getAllChapters() {
        return chapterRepo.findAll();
    }

    public List<Chapter> getChaptersByCourseId(Long courseId) {
        List<Chapter> chapters = chapterRepo.findByCourseId(courseId);

        if (chapters == null || chapters.isEmpty()) {
            throw new NoChaptersFoundException("找不到該課程的章節，課程ID：" + courseId);
        }

        return chapters;
    }

    public ChapterDTO createChapter(ChapterDTO chapterDTO) {
        Chapter chapter = chapterMapper.toEntity(chapterDTO);
        return chapterMapper.toDto(chapterRepo.save(chapter));
    }

    public Chapter updateChapter(Chapter chapter) {
        return chapterRepo.save(chapter);
    }

    public List<Chapter> updateAllChapters(List<Chapter> chapters) {
        return chapterRepo.saveAll(chapters);
    }

    @Transactional
    public void deleteChapter(Long id) {
        if (!chapterRepo.existsById(id)) {
            throw new RuntimeException("Course not found");
        }

        materialService.deleteMaterialsByChapterId(id);
        chapterRepo.deleteById(id);
    }

    public void deleteChaptersByCourseId(Long courseId) {
        List<Chapter> chapters = this.getChaptersByCourseId(courseId);
        for (Chapter chapter : chapters) {
            deleteChapter(chapter.getId());
        }
    }

    public class NoChaptersFoundException extends RuntimeException {
        public NoChaptersFoundException(String message) {
            super(message);
        }
    }

    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
