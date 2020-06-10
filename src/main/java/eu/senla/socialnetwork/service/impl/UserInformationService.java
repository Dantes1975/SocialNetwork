package eu.senla.socialnetwork.service.impl;

import eu.senla.socialnetwork.model.Information;
import eu.senla.socialnetwork.model.User;
import eu.senla.socialnetwork.repository.InformationRepository;
import eu.senla.socialnetwork.repository.UserRepository;
import eu.senla.socialnetwork.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInformationService implements InformationService {
    private final InformationRepository informationRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserInformationService(InformationRepository informationRepository, UserRepository userRepository) {
        this.informationRepository = informationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean addInformation(Information information, Long id) {
        User user = findById(id);
        user.setInformation(information);
        userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public Information getInformation(Long id) {
        return findById(id).getInformation();
    }

    @Override
    public Information updateInformation(Information information, Long id) {
        Information informationFromDB = findById(id).getInformation();
        informationFromDB.setBirthDate(information.getBirthDate());
        informationFromDB.setEducation(information.getEducation());
        informationFromDB.setCompany(information.getCompany());
        informationFromDB.setAboutMe(information.getAboutMe());
        return informationRepository.saveAndFlush(informationFromDB);
    }

    private User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
