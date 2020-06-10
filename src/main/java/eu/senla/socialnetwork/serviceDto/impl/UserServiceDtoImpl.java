package eu.senla.socialnetwork.serviceDto.impl;

import eu.senla.socialnetwork.dto.*;
import eu.senla.socialnetwork.model.*;
import eu.senla.socialnetwork.service.UserService;
import eu.senla.socialnetwork.service.impl.UserCredentialService;
import eu.senla.socialnetwork.serviceDto.UserServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceDtoImpl implements UserServiceDto {

    private final UserService userServiceImpl;

    @Autowired
    public UserServiceDtoImpl(UserCredentialService userCredentialService) {
        this.userServiceImpl = userCredentialService;
    }

    public UserDto addUser(UserDto userDto) {
        User user = userDto.toUser();
        return UserDto.fromUser(userServiceImpl.addUser(user));
    }

    public List<UserDto> getAll() {
        List<User> users = userServiceImpl.getAll();
        List<UserDto> result = new ArrayList<>();
        for (User user : users) {
            result.add(UserDto.fromUser(user));
        }
        return result;
    }

    public UserDto findById(Long id) {
        User result = userServiceImpl.findById(id).get();

        if (result == null) {
            return null;
        }
        return UserDto.fromUser(result);
    }

    public void delete(Long id) {
        userServiceImpl.delete(id);
    }

    public UserDto update(UserDto userDto, Long id) {
        User user = userServiceImpl.update(userDto.toUser(), id);
        return UserDto.fromUser(user);
    }

    public InformationDto getInformationByUserId(Long id) {
        Information information = userServiceImpl.getInformationByUserId(id);
        if (information == null) {
            return null;
        }
        return InformationDto.fromInformation(information);
    }

        public List<UserDto> getFriendsByUserId(Long id) {
        List<User> friends = userServiceImpl.getFriendsByUserId(id);
        List<UserDto> result = new ArrayList<>();
        for (User friend : friends) {
            result.add(UserDto.fromUser(friend));
        }
        return result;
    }

    public PhotoDto getPhotoByUserId(Long id) {
        Photo photo = userServiceImpl.getPhotoByUserId(id);
        if (photo == null) {
            return null;
        }
        return PhotoDto.fromPhoto(photo);
    }
}
