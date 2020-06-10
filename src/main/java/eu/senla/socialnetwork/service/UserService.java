package eu.senla.socialnetwork.service;

import eu.senla.socialnetwork.model.*;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User addUser(User user);

    User saveUser(User user);

    User update(User user, Long id);

    List<User> getAll();

    Information getInformationByUserId(Long id);

    List<User> getFriendsByUserId(Long id);

    Photo getPhotoByUserId(Long id);

    Optional<User> findById(Long id);

    void delete(Long id);

    User findByUsername(String username);
}
