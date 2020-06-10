package eu.senla.socialnetwork.controller.freemarker;

import eu.senla.socialnetwork.serviceDto.UserServiceDto;
import eu.senla.socialnetwork.serviceDto.impl.UserPhotoDtoService;
import eu.senla.socialnetwork.serviceDto.impl.UserServiceDtoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static eu.senla.socialnetwork.util.ApplicationConstant.*;

@Controller
@RequestMapping("/photos")
public class PhotoFreemarkerController {

    private final UserPhotoDtoService photoDtoService;
    private final UserServiceDto userServiceDto;

    @Autowired
    public PhotoFreemarkerController(UserPhotoDtoService photoDtoService, UserServiceDtoImpl userServiceDto) {
        this.photoDtoService = photoDtoService;
        this.userServiceDto = userServiceDto;
    }

    @PostMapping("/add")
    public String addPhoto(@RequestParam String userId,
                           @RequestParam MultipartFile file,
                           Model model) {
        photoDtoService.addPhoto(file, Long.parseLong(userId));
        model.addAttribute(PHOTOS, photoDtoService.getPhotos((Long.parseLong(userId))));
        model.addAttribute(INFORMATION, userServiceDto.findById(Long.parseLong(userId)).getInformation());
        return REDIRECT_PHOTOS + userId;
    }

    @GetMapping("/get/")
    public String getAllPhoto(@RequestParam String userId, Model model) {
        model.addAttribute(PHOTOS, photoDtoService.getPhotos((Long.parseLong(userId))));
        model.addAttribute(USER_ID, userId);
        return PHOTOS_PAGE;
    }

    @GetMapping("/get/{userId}")
    public String getPhotos(@PathVariable String userId, Model model) {
        model.addAttribute(PHOTOS, photoDtoService.getPhotos((Long.parseLong(userId))));
        model.addAttribute(USER_ID, userId);
        return PHOTOS_PAGE;
    }

    @PostMapping("/delete")
    public String deletePhoto(@RequestParam String photoId, @RequestParam String userId, Model model) {
        photoDtoService.delete(Long.parseLong(photoId));
        model.addAttribute(INFORMATION, userServiceDto.findById(Long.parseLong(userId)).getInformation());
        model.addAttribute(USER_ID, userId);
        return REDIRECT_PHOTOS + userId;
    }
}
