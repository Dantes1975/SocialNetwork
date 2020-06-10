package eu.senla.socialnetwork.service;

import eu.senla.socialnetwork.model.Message;
import eu.senla.socialnetwork.model.User;

import java.util.List;

public interface MessageService {
    List<Message> messageList();

    List<Message> messageListForUser(Long id);

    Message addMessage(Message message, Long id, Long from);

    void delete(Long id);

}
