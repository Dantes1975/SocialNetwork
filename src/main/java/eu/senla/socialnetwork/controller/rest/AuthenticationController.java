package eu.senla.socialnetwork.controller.rest;

import eu.senla.socialnetwork.config.AppConfig;
import eu.senla.socialnetwork.dto.UserDto;
import eu.senla.socialnetwork.model.Token;
import eu.senla.socialnetwork.service.impl.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/app")
public class AuthenticationController {

    private final TokenService tokenService;
    private final AppConfig appConfig;

    @Autowired
    public AuthenticationController(TokenService tokenService, AppConfig appConfig) {
        this.tokenService = tokenService;
        this.appConfig=appConfig;
    }

    @PostMapping("/login")
    public ResponseEntity<String> generateToken(@RequestBody UserDto userDto) {
        Token token = tokenService.generateToken(userDto);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(appConfig.getAuthHeaderName(), token.getKey());
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }
}