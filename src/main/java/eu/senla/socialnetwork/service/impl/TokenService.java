package eu.senla.socialnetwork.service.impl;

import eu.senla.socialnetwork.config.AppConfig;
import eu.senla.socialnetwork.dto.UserDto;
import eu.senla.socialnetwork.model.Token;
import eu.senla.socialnetwork.model.User;
import eu.senla.socialnetwork.repository.TokenRepository;
import eu.senla.socialnetwork.security.auth.UserAuth;
import eu.senla.socialnetwork.security.handler.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final TokenHandler tokenHandler;
    private final UserCredentialService userService;
    private final AppConfig appConfig;

    @Autowired
    public TokenService(TokenRepository tokenRepository,
                        TokenHandler tokenHandler,
                        UserCredentialService userService,
                        AppConfig appConfig) {
        this.tokenRepository = tokenRepository;
        this.tokenHandler = tokenHandler;
        this.userService = userService;
        this.appConfig = appConfig;
    }

    public Optional<Authentication> getAuthentication(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        return Optional.ofNullable(request.getHeader(appConfig.getAuthHeaderName()))
                .flatMap(tokenHandler::extractUserId)
                .flatMap(userService::findById)
                .map(UserAuth::new);
    }

    public Token generateToken(UserDto userDto) {
        User user = userService.loadUserByUsername(userDto.getUsername());
        if (user != null) {
            Token token = createToken(user);
            tokenRepository.save(token);
            return token;
        }
        return null;
    }

    private Token createToken(User user) {
        return new Token(tokenHandler.generateAccessToken(user.getId(), LocalDateTime.now().plusDays(appConfig.getValidityInDays())), user);
    }
}
