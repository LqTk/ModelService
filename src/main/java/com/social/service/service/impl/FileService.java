package com.social.service.service.impl;

import com.social.service.common.Const;
import com.social.service.service.IFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service("iFileService")
public class FileService implements IFileService {

    @Override
    public String upload(MultipartFile file, String uploadPath) {
        String originalFileName = file.getOriginalFilename();
        return saveFile(file, uploadPath, originalFileName);
    }

    @Override
    public String upload(MultipartFile file, String uploadPath, String userId) {
        String originalFileName = file.getOriginalFilename();
        String extendName = originalFileName.substring(originalFileName.lastIndexOf("."));
        originalFileName = userId+"-"+System.currentTimeMillis()+extendName;
        return saveFile(file, uploadPath, originalFileName);
    }

    private String saveFile(MultipartFile file, String uploadPath, String originalFileName) {
        String fileName = originalFileName;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()){
            uploadDir.setWritable(true);
            uploadDir.mkdirs();
        }
        File uploadFile = new File(uploadDir,fileName);
        try {
            file.transferTo(uploadFile);//上传文件到Tomcat服务器
//            uploadFile.delete();//删除上传的文件
            return uploadFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteFile(String type, String path) {
        File file = new File(Const.uploadDir+path);
        if (file.exists())
            file.delete();
    }
}
