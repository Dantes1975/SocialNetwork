package eu.senla.socialnetwork.service.impl;

import eu.senla.socialnetwork.config.AppConfig;
import eu.senla.socialnetwork.model.Photo;
import eu.senla.socialnetwork.model.User;
import eu.senla.socialnetwork.repository.PhotoRepository;
import eu.senla.socialnetwork.repository.UserRepository;
import eu.senla.socialnetwork.service.MainPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import static eu.senla.socialnetwork.util.ApplicationConstant.ADD_FILE;

@Service
public class UserMainPhotoService implements MainPhotoService {
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;
    private final AppConfig appConfig;

    @Autowired
    public UserMainPhotoService(PhotoRepository photoRepository, UserRepository userRepository, AppConfig appConfig) {
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
        this.appConfig = appConfig;
    }

    @Override
    public Photo addPhoto(MultipartFile file, Long id) throws FileNotFoundException {
        User user = findById(id);
        Photo photo = new Photo();
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(appConfig.getUploadPath());

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            photo.setFileName(resultFilename);
            try {
                file.transferTo(new File(appConfig.getUploadPath() + "/" + resultFilename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new FileNotFoundException(ADD_FILE);
        }
        Photo resultPhoto = photoRepository.save(photo);
        user.setMainPhoto(resultPhoto);
        userRepository.saveAndFlush(user);
        return resultPhoto;
    }

    @Override
    public Photo getPhoto(Long id) {
        return findById(id).getMainPhoto();
    }

    @Override
    public void delete(Long id) {
        photoRepository.deleteById(id);
    }

    private User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}