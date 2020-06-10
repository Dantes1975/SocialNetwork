package eu.senla.socialnetwork.serviceDto;

import eu.senla.socialnetwork.dto.*;

import java.util.List;


public interface UserServiceDto {

    UserDto addUser(UserDto userDto);

    List<UserDto> getAll();

    UserDto findById(Long id);

    void delete(Long id);

    UserDto update(UserDto userDto, Long id);

    InformationDto getInformationByUserId(Long id);

    List<UserDto> getFriendsByUserId(Long id);

    PhotoDto getPhotoByUserId(Long id);

}
