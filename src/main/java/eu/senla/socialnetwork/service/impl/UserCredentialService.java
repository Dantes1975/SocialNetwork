package eu.senla.socialnetwork.service.impl;

import eu.senla.socialnetwork.model.*;
import eu.senla.socialnetwork.repository.UserRepository;
import eu.senla.socialnetwork.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static eu.senla.socialnetwork.util.ApplicationConstant.*;


@Service
public class UserCredentialService implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserCredentialService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = passwordEncoder;
    }

    public User addUser(User user) throws BadCredentialsException {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            throw new BadCredentialsException(USER_IS_EXIST + user.getUsername());
        }

        user.setEnabled(true);
        user.setRoles(Collections
                .singleton(Role.USER));
        user.setFriends(new ArrayList<>());
        user.setInformation(new Information(new Date(1990, 01, 01), EMPTY_FIELD, EMPTY_FIELD, EMPTY_FIELD));
        user.setPassword(encodePassword(user));

        return userRepository.save(user);
    }


    public User saveUser(User user) {
        user.setPassword(user.getPassword());
        user.setRoles(Collections.singleton(Role.USER));
        User registeredUser = userRepository.save(user);
        return registeredUser;
    }


    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        return result;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(USER_NOT_FOUND_WITH_NAME + username);
        }
        return user;
    }


    public User update(User user, Long id) throws BadCredentialsException {
        User authUser = findById(id).get();
        if (user.getUsername() != null) {
            User userTest = userRepository.findByUsername(user.getUsername());
            if (userTest != null) {
                throw new BadCredentialsException(USER_IS_EXIST + user.getUsername());
            }
        }
        authUser.setUsername(user.getUsername());
        authUser.setPassword(encodePassword(user));
        authUser.setFirstName(user.getFirstName());
        authUser.setLastName(user.getLastName());
        authUser.setEmail(user.getEmail());
        return userRepository.saveAndFlush(authUser);
    }

    private String encodePassword(User user) {
        return bCryptPasswordEncoder.encode(user.getPassword());
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(USER_NOT_FOUND_WITH_NAME + username);
        }
        return user;
    }

    public Information getInformationByUserId(Long id) {
        return findById(id).get().getInformation();
    }

    public List<User> getFriendsByUserId(Long id) {
        return findById(id).get().getFriends();
    }

    public Photo getPhotoByUserId(Long id) {
        return findById(id).get().getMainPhoto();
    }

    public Optional<User> findById(@NonNull Long id) {
        return userRepository.findById(id);
    }

    private User getAuthenticationUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
