package com.demo.elearning.controller;

import com.demo.elearning.service.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class FileController {

    private final FileStorageService fileStorageService;

    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    // 上傳圖片檔案
    @PostMapping("/image")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = fileStorageService.storeFile(file);
            return ResponseEntity.ok(Map.of("img_url", fileUrl));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(500)
                    .body(Map.of("error", "檔案儲存失敗"));
        }
    }

    // 上傳教材檔案
    @PostMapping("/material")
    public ResponseEntity<Map<String, String>> uploadMaterial(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = fileStorageService.storeFile(file);
            return ResponseEntity.ok(Map.of("material_url", fileUrl));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(500)
                    .body(Map.of("error", "教材檔案儲存失敗"));
        }
    }
}
