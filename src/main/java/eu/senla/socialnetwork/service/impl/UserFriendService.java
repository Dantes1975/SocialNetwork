package eu.senla.socialnetwork.service.impl;

import eu.senla.socialnetwork.model.User;
import eu.senla.socialnetwork.repository.UserRepository;
import eu.senla.socialnetwork.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFriendService implements FriendService {
    private final UserRepository userRepository;

    @Autowired
    public UserFriendService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addFriend(Long friendId, Long OwnerId) {
        User owner = findById(OwnerId);
        User friend = findById(friendId);
        owner.addFriend(friend);
        userRepository.saveAndFlush(owner);
    }

    public List<User> getFriends(Long id) {
        return findById(id).getFriends();
    }

    public void deleteFriendFromUser(Long friendId, Long userId) {
        User owner = findById(userId);
        User friend = findById(friendId);
        List<User> friends = owner.getFriends();
        friends.remove(friend);
        userRepository.saveAndFlush(owner);
        System.out.println();
    }

    private User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
