package eu.senla.socialnetwork.controller.freemarker;

import eu.senla.socialnetwork.dto.InformationDto;
import eu.senla.socialnetwork.serviceDto.InformationServiceDto;
import eu.senla.socialnetwork.serviceDto.UserServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static eu.senla.socialnetwork.util.ApplicationConstant.*;

@Controller
@RequestMapping("/information")
public class InformationFreemarkerController {
    private final InformationServiceDto informationService;
    private final UserServiceDto userServiceDto;

    @Autowired
    public InformationFreemarkerController(InformationServiceDto informationService, UserServiceDto userServiceDto) {
        this.informationService = informationService;
        this.userServiceDto = userServiceDto;
    }

    @GetMapping("/get")
    public String getUserInformation(@RequestParam String userId, Model model) {
        model.addAttribute(INFORMATION, informationService.getInformation(Long.parseLong(userId)));
        model.addAttribute(USER_ID, userId);
        return INFORMATION_PAGE;
    }

    @GetMapping("/create")
    public String getPageCreateInformation(@RequestParam String userId, Model model) {
        model.addAttribute(USER_ID, userId);
        return CREATE_INFORMATION_PAGE;
    }

    @PostMapping("/create")
    public String createInformation(@ModelAttribute InformationDto informationDto, @RequestParam String userId, Model model) {
        informationService.addInformation(informationDto, Long.parseLong(userId));
        model.addAttribute(INFORMATION, informationService.getInformation(Long.parseLong(userId)));
        model.addAttribute(USER, userServiceDto.findById(Long.parseLong(userId)));
        model.addAttribute(USER_ID, userId);
        return REDIRECT_USER + userId;
    }

    @PostMapping("/update")
    public String updateInformation(@ModelAttribute InformationDto informationDto, @RequestParam String userId, Model model) {
        model.addAttribute(INFORMATION, informationService.update(informationDto, Long.parseLong(userId)));
        model.addAttribute(USER_ID, userId);
        return REDIRECT_USER + userId;
    }

}
