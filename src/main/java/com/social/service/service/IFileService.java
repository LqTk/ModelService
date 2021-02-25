package com.social.service.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    public String upload(MultipartFile file, String uploadPath);
    public String upload(MultipartFile file, String uploadPath, String userId);
    public void deleteFile(String type, String path);
}
