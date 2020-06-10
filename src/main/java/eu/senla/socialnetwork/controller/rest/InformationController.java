package eu.senla.socialnetwork.controller.rest;

import eu.senla.socialnetwork.dto.InformationDto;
import eu.senla.socialnetwork.serviceDto.InformationServiceDto;
import eu.senla.socialnetwork.serviceDto.impl.UserInformationServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/info")
public class InformationController {
    private final InformationServiceDto informationService;

    @Autowired
    public InformationController(UserInformationServiceDto informationService) {
        this.informationService = informationService;
    }

    @GetMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public InformationDto userInformation(@PathVariable String userId) {
        return informationService.getInformation(Long.parseLong(userId));
    }

    @PostMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void createInformation(@RequestBody InformationDto informationDto, @PathVariable String userId) {
        informationService.addInformation(informationDto, Long.parseLong(userId));
    }

    @PutMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public InformationDto updateInformation(@RequestBody InformationDto informationDto, @PathVariable String userId) {
        return informationService.update(informationDto, Long.parseLong(userId));
    }

}
