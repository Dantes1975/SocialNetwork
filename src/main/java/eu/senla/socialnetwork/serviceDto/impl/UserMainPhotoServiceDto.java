package eu.senla.socialnetwork.serviceDto.impl;

import eu.senla.socialnetwork.dto.PhotoDto;
import eu.senla.socialnetwork.model.Photo;
import eu.senla.socialnetwork.service.MainPhotoService;
import eu.senla.socialnetwork.service.impl.UserMainPhotoService;
import eu.senla.socialnetwork.serviceDto.MainPhotoServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@Service
public class UserMainPhotoServiceDto implements MainPhotoServiceDto {
    private final MainPhotoService mainPhotoService;

    @Autowired
    public UserMainPhotoServiceDto(UserMainPhotoService photoService) {
        this.mainPhotoService = photoService;
    }

    @Override
    public PhotoDto addPhoto(MultipartFile file, Long id) throws FileNotFoundException {
        return PhotoDto.fromPhoto(mainPhotoService.addPhoto(file, id));
    }

    @Override
    public PhotoDto getPhoto(Long id) {
        Photo photo = mainPhotoService.getPhoto(id);
        return PhotoDto.fromPhoto(photo);
    }

    @Override
    public void delete(Long id) {
        mainPhotoService.delete(id);
    }

}