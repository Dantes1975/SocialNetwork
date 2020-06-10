package eu.senla.socialnetwork.dto;

import eu.senla.socialnetwork.model.Information;
import eu.senla.socialnetwork.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationDto {
    private Date birthDate;
    private String education;
    private String company;
    private String aboutMe;

    public Information toInformation() {
        Information information = new Information();
        information.setBirthDate(birthDate);
        information.setEducation(education);
        information.setCompany(company);
        information.setAboutMe(aboutMe);
        return information;
    }

    public static InformationDto fromInformation(Information information) {
        InformationDto informationDto = new InformationDto();
        informationDto.setBirthDate(information.getBirthDate());
        informationDto.setEducation(information.getEducation());
        informationDto.setCompany(information.getCompany());
        informationDto.setAboutMe(information.getAboutMe());
        return informationDto;
    }
}
