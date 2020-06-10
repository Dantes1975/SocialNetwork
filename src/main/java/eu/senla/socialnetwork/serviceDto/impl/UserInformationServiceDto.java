package eu.senla.socialnetwork.serviceDto.impl;

import eu.senla.socialnetwork.dto.InformationDto;
import eu.senla.socialnetwork.model.Information;
import eu.senla.socialnetwork.service.InformationService;
import eu.senla.socialnetwork.service.impl.UserInformationService;
import eu.senla.socialnetwork.serviceDto.InformationServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInformationServiceDto implements InformationServiceDto {
    private final InformationService informationService;

    @Autowired
    public UserInformationServiceDto(UserInformationService informationService) {
        this.informationService = informationService;
    }

    @Override
    public boolean addInformation(InformationDto informationDto, Long id) {
        Information information = informationDto.toInformation();
        return informationService.addInformation(information, id);
    }

    @Override
    public InformationDto getInformation(Long id) {
        Information information = informationService.getInformation(id);
        return InformationDto.fromInformation(information);
    }

    @Override
    public InformationDto update(InformationDto informationDto, Long id) {
        Information information = informationService.updateInformation(informationDto.toInformation(), id);
        return InformationDto.fromInformation(information);
    }

}
