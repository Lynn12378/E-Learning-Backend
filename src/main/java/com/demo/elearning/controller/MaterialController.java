package com.demo.elearning.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.elearning.dto.MaterialDTO;
import com.demo.elearning.entity.Material;
import com.demo.elearning.service.MaterialService;

@RestController
@RequestMapping("/materials")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public List<Material> getAll() {
        return materialService.getAllMaterial();
    }

    @GetMapping("/{id}")
    public MaterialDTO getById(@PathVariable Long id) {
        return materialService.getMaterialById(id);
    }

    @GetMapping("/chapter/{chapter_id}")
    public List<Material> getByChapterId(@PathVariable("chapter_id") Long chapterId) {
        return materialService.getMaterialsByChapterId(chapterId);
    }

    @PostMapping
    public MaterialDTO create(@RequestBody MaterialDTO dto) {
        return materialService.createMaterial(dto);
    }

    @PutMapping("/{id}")
    public Material update(@PathVariable Long id, @RequestBody Material updated) {
        return materialService.updateMaterial(updated);
    }

    @PutMapping("/")
    public List<Material> updateAllChapters(@RequestBody List<Material> chapters) {
        return materialService.updateAllMaterials(chapters);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        materialService.deleteMaterial(id);
    }

}