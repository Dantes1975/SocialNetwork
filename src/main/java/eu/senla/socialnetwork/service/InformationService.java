package eu.senla.socialnetwork.service;

import eu.senla.socialnetwork.model.*;


public interface InformationService {
    boolean addInformation(Information information, Long id);

    Information getInformation(Long id);

    Information updateInformation(Information information, Long id);

}
