package com.ra.sevice;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ra.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
@Service
public class UploadFileService {
    @Autowired
    private Cloudinary cloudinary;
    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()||file==null) {
            throw new CustomException("file cannot be empty or null");
        }
        try {
            Map<String,Object> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return result.get("secure_url").toString();
        }catch (Exception e){
            throw new RuntimeException("upload file error");
        }
    }
}
