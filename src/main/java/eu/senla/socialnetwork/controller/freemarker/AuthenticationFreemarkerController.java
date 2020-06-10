package eu.senla.socialnetwork.controller.freemarker;

import eu.senla.socialnetwork.dto.AuthenticationRequestDto;
import eu.senla.socialnetwork.model.Role;
import eu.senla.socialnetwork.model.User;
import eu.senla.socialnetwork.service.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static eu.senla.socialnetwork.util.ApplicationConstant.*;

@Controller
public class AuthenticationFreemarkerController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationFreemarkerController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public String greeting() {
        return LOGIN_PAGE;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute AuthenticationRequestDto requestDto, Model model) {
        try {
            User user = authenticationService.getUserFromAuthenticationRequest(requestDto);

            model.addAttribute(USER, user);
            model.addAttribute(INFORMATION, user.getInformation());
            if (user.getRoles().contains(Role.ADMIN)) {
                return ADMIN_PAGE;
            } else return MAIN_PAGE;
        } catch (BadCredentialsException e) {
            model.addAttribute(ERROR, e.getMessage());
            return LOGIN_PAGE;
        }
    }
}
