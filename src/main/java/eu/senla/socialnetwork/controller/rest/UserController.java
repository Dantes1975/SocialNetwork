package eu.senla.socialnetwork.controller.rest;

import eu.senla.socialnetwork.config.AppConfig;
import eu.senla.socialnetwork.dto.*;
import eu.senla.socialnetwork.logger.LoggerProcessor;
import eu.senla.socialnetwork.model.User;
import eu.senla.socialnetwork.serviceDto.UserServiceDto;
import eu.senla.socialnetwork.serviceDto.impl.UserServiceDtoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class UserController {


    private final UserServiceDto userServiceDto;
    private final AppConfig appConfig;

    @Autowired
    public UserController(UserServiceDtoImpl userServiceDto, AppConfig appConfig) {
        this.userServiceDto = userServiceDto;
        this.appConfig = appConfig;
    }


    @PostMapping("/user/create")
    @ResponseStatus(code = HttpStatus.OK)
    public void createUser(@RequestBody UserDto userDto) {
        userServiceDto.addUser(userDto);
        //LoggerProcessor.postHttpRequest(userDto, appConfig.getLoggerPath());
    }

    @PutMapping("/user/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable String userId) {
        return userServiceDto.update(userDto, Long.parseLong(userId));
    }

    @GetMapping("/user")
    @ResponseStatus(code = HttpStatus.OK)
    public UserDto userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return UserDto.fromUser(user);
    }

    @GetMapping("/users")
    @ResponseStatus(code = HttpStatus.OK)
    public List<UserDto> usersInfo() {
        return userServiceDto.getAll();
    }

    @DeleteMapping("/user/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void userDelete(@PathVariable String userId) {
        userServiceDto.delete(Long.parseLong(userId));
    }
}
