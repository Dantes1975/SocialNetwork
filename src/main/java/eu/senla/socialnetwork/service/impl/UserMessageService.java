package eu.senla.socialnetwork.service.impl;

import eu.senla.socialnetwork.model.Message;
import eu.senla.socialnetwork.model.User;
import eu.senla.socialnetwork.repository.MessageRepository;
import eu.senla.socialnetwork.repository.UserRepository;
import eu.senla.socialnetwork.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserMessageService implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserMessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Message> messageList() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> messageListForUser(Long id) {
        return findById(id).getMyMessages();
    }


    @Override
    public Message addMessage(Message message, Long id, Long from) {
        User user = findById(id);
        User author = findById(from);
        message.setAuthor(author);
        message.setOwner(user);
        message.setDepartureDate(Date.valueOf(LocalDate.now()));
        user.addMessage(message);

        return messageRepository.save(message);
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);
    }

    private User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}