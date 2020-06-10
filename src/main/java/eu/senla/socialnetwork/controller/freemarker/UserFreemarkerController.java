package eu.senla.socialnetwork.controller.freemarker;

import eu.senla.socialnetwork.dto.UserDto;
import eu.senla.socialnetwork.model.User;
import eu.senla.socialnetwork.serviceDto.UserServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import static eu.senla.socialnetwork.util.ApplicationConstant.*;

@Controller
public class UserFreemarkerController {

    private final UserServiceDto userServiceDto;

    @Autowired
    public UserFreemarkerController(UserServiceDto userServiceDto) {
        this.userServiceDto = userServiceDto;
    }

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/registration")
    public String registration() {
        return REGISTRATION_PAGE;
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute UserDto userDto, Model model) {
        try {
            UserDto user = userServiceDto.addUser(userDto);
            model.addAttribute(USER, user);
            model.addAttribute(INFORMATION, user.getInformation());
            return MAIN_PAGE;
        } catch (BadCredentialsException e) {
            model.addAttribute(ERROR, e.getMessage());
            return REGISTRATION_PAGE;
        }
    }

    @GetMapping("/users/update")
    public String updateProfile(String userId, Model model) {
        model.addAttribute(USER, userServiceDto.findById(Long.parseLong(userId)));
        return EDIT_PROFILE_PAGE;
    }

    @PostMapping("/users/update")
    public String updateUser(@ModelAttribute UserDto userDto, Model model) {
        try {
            model.addAttribute(USER, userServiceDto.update(userDto, userDto.getId()));
            model.addAttribute(INFORMATION, userServiceDto.findById(userDto.getId()).getInformation());
            return MAIN_PAGE;
        } catch (BadCredentialsException e) {
            model.addAttribute(ERROR, e.getMessage());
            return REDIRECT_USER + userDto.getId();
        }
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute(USERS, userServiceDto.getAll());
        return USERS;
    }

    @GetMapping("/users/{userId}")
    public String userInfo(@PathVariable String userId, Model model) {
        model.addAttribute(USER, userServiceDto.findById(Long.parseLong(userId)));
        model.addAttribute(INFORMATION, userServiceDto.findById(Long.parseLong(userId)).getInformation());
        return MAIN_PAGE;
    }

    @GetMapping("/user")
    public String userInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if(user!=null){
            model.addAttribute(USER, user);
            model.addAttribute(INFORMATION, user.getInformation());
            return MAIN_PAGE;
        }
        return GREETING_PAGE;
    }

    @GetMapping("/users/back")
    public String back(@RequestParam String userId, Model model) {
        model.addAttribute(USER, userServiceDto.findById(Long.parseLong(userId)));
        model.addAttribute(INFORMATION, userServiceDto.findById(Long.parseLong(userId)).getInformation());
        return MAIN_PAGE;
    }

    @PostMapping("/users/delete")
    public String userDelete(@RequestParam String userId) {
        userServiceDto.delete(Long.parseLong(userId));
        return GREETING_PAGE;
    }

}
