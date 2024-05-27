package com.scm.SmartContactManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.SmartContactManager.config.CloudinaryConfig;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private CloudinaryConfig cloudinaryConfig;

    @Override
    public String getImageUrl(MultipartFile profilePic,String fileName) {

        try{
            //get size of profile pic
            byte[] size = new byte[profilePic.getInputStream().available()];
            // fill the size array
            profilePic.getInputStream().read(size);
            //upload the image to cloudinary
            cloudinaryConfig.cloudinary().uploader().upload(size, ObjectUtils.asMap("public_id",fileName));

            return cloudinaryConfig.cloudinary().url().transformation(
                new Transformation<>().width(500).height(500).crop("fill")
            ).generate(fileName);

        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
 
    }

}
