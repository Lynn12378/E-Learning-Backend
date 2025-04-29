package com.demo.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.elearning.entity.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    List<Material> findByChapterId(Long chapterId);

    void deleteByChapterId(Long chapterId);

}