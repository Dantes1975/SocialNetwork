package eu.senla.socialnetwork.dto;

import eu.senla.socialnetwork.model.User;
import lombok.Data;

import java.sql.Date;


@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private PhotoDto mainPhoto;
    private InformationDto information;


    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        if (mainPhoto != null) {
            user.setMainPhoto(mainPhoto.toPhoto());
        }

        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        if (user.getInformation() == null) {
            userDto.setInformation(new InformationDto());
        } else {
            userDto.setInformation(InformationDto.fromInformation(user.getInformation()));
        }
        if (user.getMainPhoto() != null) {
            userDto.setMainPhoto(PhotoDto.fromPhoto(user.getMainPhoto()));
        }
        return userDto;
    }
}
