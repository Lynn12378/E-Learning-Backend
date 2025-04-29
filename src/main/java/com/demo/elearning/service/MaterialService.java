package com.demo.elearning.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.demo.elearning.dto.CourseDTO;
import com.demo.elearning.dto.MaterialDTO;
import com.demo.elearning.entity.Course;
import com.demo.elearning.entity.Material;
import com.demo.elearning.mapper.MaterialMapper;
import com.demo.elearning.repository.MaterialRepository;

@Service
public class MaterialService {
    private final MaterialRepository materialRepo;
    private final MaterialMapper materialMapper;

    public MaterialService(MaterialRepository materialRepo, MaterialMapper materialMapper) {
        this.materialRepo = materialRepo;
        this.materialMapper = materialMapper;
    }

    public List<Material> getAllMaterial() {
        return materialRepo.findAll();
    }

    public MaterialDTO getMaterialById(Long id) {
        Material material = materialRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("找不到該教材，id: " + id));
        return materialMapper.toDto(material);
    }

    public List<Material> getMaterialsByChapterId(Long chapterId) {
        List<Material> materials = materialRepo.findByChapterId(chapterId);

        if (materials == null || materials.isEmpty()) {
            throw new NoChaptersFoundException("找不到該章節的教材，課程ID：" + chapterId);
        }

        return materials;
    }

    public MaterialDTO createMaterial(MaterialDTO dto) {
        dto.setCreatedTime(LocalDateTime.now());
        Material material = materialMapper.toEntity(dto);

        return materialMapper.toDto(materialRepo.save(material));
    }

    public Material updateMaterial(Material material) {
        return materialRepo.save(material);
    }

    public List<Material> updateAllMaterials(List<Material> materials) {
        return materialRepo.saveAll(materials);
    }

    public void deleteMaterial(Long id) {
        if (!materialRepo.existsById(id)) {
            throw new RuntimeException("Material not found");
        }
        materialRepo.deleteById(id);
    }

    public void deleteMaterialsByChapterId(Long chapterId) {
        materialRepo.deleteByChapterId(chapterId);
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