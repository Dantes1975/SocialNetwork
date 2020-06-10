package eu.senla.socialnetwork.service.impl;

import eu.senla.socialnetwork.dto.AuthenticationRequestDto;
import eu.senla.socialnetwork.model.User;
import eu.senla.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static eu.senla.socialnetwork.util.ApplicationConstant.INVALID_USERNAME_PASSWORD;
import static eu.senla.socialnetwork.util.ApplicationConstant.USER_NOT_FOUND_WITH_NAME;

@Service
public class AuthenticationService {

    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @Autowired
    public AuthenticationService(UserCredentialService userDetailsService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    public User getUserFromAuthenticationRequest(AuthenticationRequestDto requestDto) throws BadCredentialsException {
        try {
            String username = requestDto.getUsername();
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException(USER_NOT_FOUND_WITH_NAME + username);
            }
            return user;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(INVALID_USERNAME_PASSWORD);
        }
    }

}
