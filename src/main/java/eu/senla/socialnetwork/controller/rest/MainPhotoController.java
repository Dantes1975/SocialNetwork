package eu.senla.socialnetwork.controller.rest;

import eu.senla.socialnetwork.dto.PhotoDto;
import eu.senla.socialnetwork.serviceDto.impl.UserMainPhotoServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/photo")
public class MainPhotoController {

    private final UserMainPhotoServiceDto photoServiceDto;


    @Autowired
    public MainPhotoController(UserMainPhotoServiceDto photoServiceDto) {
        this.photoServiceDto = photoServiceDto;
    }

    @GetMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public PhotoDto getUserPhoto(@PathVariable String userId) {
        return photoServiceDto.getPhoto(Long.parseLong(userId));
    }

    @PostMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void addPhotoToUser(@RequestParam MultipartFile file, @PathVariable String userId) throws FileNotFoundException {
        photoServiceDto.addPhoto(file, Long.parseLong(userId));
    }

    @DeleteMapping("/users/{photoId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void userDelete(@PathVariable String photoId) {
        photoServiceDto.delete(Long.parseLong(photoId));
    }
}
