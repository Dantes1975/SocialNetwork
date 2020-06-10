package eu.senla.socialnetwork.serviceDto.impl;


import eu.senla.socialnetwork.dto.UserDto;
import eu.senla.socialnetwork.service.impl.UserFriendService;
import eu.senla.socialnetwork.serviceDto.FriendServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFriendServiceDto implements FriendServiceDto {
    private final UserFriendService friendService;
    private final UserServiceDtoImpl userServiceDto;

    @Autowired
    public UserFriendServiceDto(UserFriendService friendService, UserServiceDtoImpl userServiceDto) {
        this.friendService = friendService;
        this.userServiceDto = userServiceDto;
    }

    @Override
    public void addFriend(Long friendId, Long ownerId) {
        friendService.addFriend(friendId, ownerId);
    }

    @Override
    public List<UserDto> getFriends(Long id) {
        return userServiceDto.getFriendsByUserId(id);
    }

    @Override
    public void deleteFriendFromUser(Long friendId, Long ownerId) {
        friendService.deleteFriendFromUser(friendId, ownerId);
    }
}
