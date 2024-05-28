package com.scm.SmartContactManager.annotations;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile>{


    public static final long max_file_size = 1024 * 1024 * 2;
    private BufferedImage bufferedImage;
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        
        if(file.isEmpty() || file == null){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File is Empty").addConstraintViolation();
            return false;
        }

        if(file.getSize() > max_file_size){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File size is greater than 2MB").addConstraintViolation();
            return false;
        }

        // try {
        //     BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        //     if(bufferedImage.getHeight() < 100 || bufferedImage.getWidth() < 100){
        //         context.disableDefaultConstraintViolation();
        //         context.buildConstraintViolationWithTemplate("Image size is less than 100x100").addConstraintViolation();
        //         return false;
        //     }

        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        return true;
    }

}
