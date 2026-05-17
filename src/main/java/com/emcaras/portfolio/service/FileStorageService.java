package com.emcaras.portfolio.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

//Cloudinary
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Map;

@Service
public class FileStorageService {

    @Value("${file.upload.dir}")
    private String uploadDir;

    Dotenv dotenv = Dotenv.load();
    Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));

    public String storeFile(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new IOException("El archivo esta vacio");
        }

        String fileName = file.getOriginalFilename();
        String extension = "";
        if (fileName != null) {
            int dotPosition = fileName.lastIndexOf(".");
            if (dotPosition >= 0) {
                extension = fileName.substring(dotPosition);
            }
        }

        String newFileName = UUID.randomUUID() + extension;

        Path path = Paths.get(uploadDir, newFileName).normalize();
        Files.copy(file.getInputStream(), path);

        return "/img/projects/" + newFileName;
    }

    public String storeFileCloudinary(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new IOException("El archivo esta vacio");
        }

        String filename = file.getOriginalFilename();

        String extension = "";
        if (filename != null) {
            int dotPosition = filename.lastIndexOf(".");
            if (dotPosition >= 0) {
                extension = filename.substring(dotPosition);
            }
        }

        String newFileName = String.valueOf(UUID.randomUUID());

        try {
            Map params1 = ObjectUtils.asMap(
                    "public_id", newFileName,
                    "use_filename", true,
                    "unique_filename", false,
                    "folder", "portfolio/projects",
                    "overwrite", true,
                    "resource_type", "auto"
            );
            return cloudinary.uploader().upload( file.getBytes(), params1).get("secure_url").toString();

        } catch (Exception ex) {
            throw new IOException("Error al subir a clodinary");
        }

    }
}
