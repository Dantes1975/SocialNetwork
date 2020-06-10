package eu.senla.socialnetwork.controller.freemarker;

import eu.senla.socialnetwork.serviceDto.UserServiceDto;
import eu.senla.socialnetwork.serviceDto.impl.UserMainPhotoServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

import static eu.senla.socialnetwork.util.ApplicationConstant.*;

@Controller
@RequestMapping("/photo")
public class MainPhotoFreemarkerController {
    private final UserMainPhotoServiceDto mainPhotoServiceDto;
    private final UserServiceDto userServiceDto;

    @Autowired
    public MainPhotoFreemarkerController(UserMainPhotoServiceDto mainPhotoServiceDto, UserServiceDto userServiceDto) {
        this.mainPhotoServiceDto = mainPhotoServiceDto;
        this.userServiceDto = userServiceDto;
    }

    @PostMapping("/add")
    public String addPhoto(@RequestParam String userId,
                           @RequestParam MultipartFile file,
                           Model model) {
        try {
            model.addAttribute(PHOTO, mainPhotoServiceDto.addPhoto(file, Long.parseLong(userId)));
            model.addAttribute(USER, userServiceDto.findById(Long.parseLong(userId)));
            model.addAttribute(INFORMATION, userServiceDto.findById(Long.parseLong(userId)).getInformation());
            return REDIRECT_USER + userId;
        } catch (FileNotFoundException e) {
            model.addAttribute(ERROR, e.getMessage());
            return REDIRECT_USER + userId;
        }

    }

    @GetMapping("/get")
    public String getUserPhoto(@RequestParam String userId, Model model) {
        model.addAttribute(PHOTO, mainPhotoServiceDto.getPhoto(Long.parseLong(userId)));
        model.addAttribute(USER, userServiceDto.findById(Long.parseLong(userId)));
        model.addAttribute(INFORMATION, userServiceDto.findById(Long.parseLong(userId)).getInformation());
        return MAIN_PAGE;
    }

    @PostMapping("/delete")
    public void userDelete(@RequestParam String photoId) {
        mainPhotoServiceDto.delete(Long.parseLong(photoId));
    }
}
