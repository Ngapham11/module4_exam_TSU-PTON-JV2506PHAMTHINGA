package com.ra.config;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    private static final String CLOUD_NAME="dl9if8ehg";
    private static final String API_KEY="895578577973735";
    private static final String API_SECRET="_K_cln7jTeiHXJER7wQ3YG9HjaI";
    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap("cloud_name",CLOUD_NAME,"api_key",
                API_KEY,"api_secret",API_SECRET));
    }
}
