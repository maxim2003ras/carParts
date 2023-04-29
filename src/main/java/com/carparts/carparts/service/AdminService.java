package com.carparts.carparts.service;

import com.carparts.carparts.model.Parts;
import com.carparts.carparts.repository.CarPartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class AdminService {
    @Autowired
    private CarPartsRepository carPartsRepository;

    public void saveImageForItem(final MultipartFile file, Integer partId) {
        Parts currentPart = carPartsRepository.findById(partId).get();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            currentPart.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        carPartsRepository.save(currentPart);

    }
}
