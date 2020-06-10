package eu.senla.socialnetwork.serviceDto;

import eu.senla.socialnetwork.dto.InformationDto;

public interface InformationServiceDto {
    boolean addInformation(InformationDto information, Long id);

    InformationDto getInformation(Long id);

    InformationDto update(InformationDto information, Long id);

}
