package eu.senla.socialnetwork.serviceDto;

import eu.senla.socialnetwork.dto.UserDto;

import java.util.List;

public interface FriendServiceDto {
    void addFriend(Long friendId, Long ownerId);

    List<UserDto> getFriends(Long id);

    void deleteFriendFromUser(Long friendId, Long ownerId);
}
