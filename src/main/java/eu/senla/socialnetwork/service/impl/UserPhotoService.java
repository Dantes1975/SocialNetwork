package eu.senla.socialnetwork.service.impl;

import eu.senla.socialnetwork.config.AppConfig;
import eu.senla.socialnetwork.model.Photo;
import eu.senla.socialnetwork.model.User;
import eu.senla.socialnetwork.repository.PhotoRepository;
import eu.senla.socialnetwork.repository.UserRepository;
import eu.senla.socialnetwork.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class UserPhotoService implements PhotoService {

    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;
    private final AppConfig appConfig;

    @Autowired
    public UserPhotoService(UserRepository userRepository, PhotoRepository photoRepository, AppConfig appConfig) {
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
        this.appConfig = appConfig;
    }

    @Override
    public void addPhoto(MultipartFile file, Long id) {
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
            photo.setOwner(user);
            try {
                file.transferTo(new File(appConfig.getUploadPath() + "/" + resultFilename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Photo resultPhoto = photoRepository.save(photo);
        user.addPhoto(resultPhoto);
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<Photo> getPhotos(Long id) {
        return findById(id).getPhotos();
    }

    @Override
    public void delete(Long id) {
        photoRepository.deleteById(id);
        System.out.println();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
