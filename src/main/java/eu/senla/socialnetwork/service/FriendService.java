package eu.senla.socialnetwork.service;

import eu.senla.socialnetwork.model.User;

import java.util.List;

public interface FriendService {
    void addFriend(Long friendId, Long ownerId);

    List<User> getFriends(Long id);

    void deleteFriendFromUser(Long friendId, Long userId);
}
