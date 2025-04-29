package com.demo.elearning.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath); // 創建目錄
        }

        // 取得原始檔案的副檔名
        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // 只用 UUID + 副檔名組成新的檔名
        String fileName = UUID.randomUUID().toString() + fileExtension;
        Path targetLocation = uploadPath.resolve(fileName);

        Files.copy(file.getInputStream(), targetLocation);

        return "http://localhost:8080/uploads/" + fileName;
    }
}
