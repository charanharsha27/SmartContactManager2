package com.scm.SmartContactManager.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String getImageUrl(MultipartFile profilePic,String fileName);

}
