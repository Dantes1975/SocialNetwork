package eu.senla.socialnetwork.service.impl;

import eu.senla.socialnetwork.model.Conversation;
import eu.senla.socialnetwork.model.Message;
import eu.senla.socialnetwork.model.User;
import eu.senla.socialnetwork.repository.ConversationRepository;
import eu.senla.socialnetwork.repository.MessageRepository;
import eu.senla.socialnetwork.repository.UserRepository;
import eu.senla.socialnetwork.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserConversationService implements ConversationService {
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;


    @Autowired
    public UserConversationService(ConversationRepository conversationRepository,
                                   UserRepository userRepository,
                                   MessageRepository messageRepository) {
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
        this.messageRepository=messageRepository;
    }

    @Override
    public Conversation addConversation(Long authorId, Long opponentId) {
        User author = findById(authorId);
        User opponent = findById(opponentId);
        Conversation conversation = new Conversation();
        conversation.setAuthor(author);
        conversation.setOpponent(opponent);
        conversation.setOpponentMessages(new ArrayList<>());
        conversation.setAuthorMessages(new ArrayList<>());

        return conversationRepository.save(conversation);
    }


    @Override
    public Conversation addAuthorMessage(Message message, Long conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId).orElse(null);
        message.setConversationId(conversationId);
        conversation.addAuthorMessage(message);
        return conversationRepository.saveAndFlush(conversation);
    }

    @Override
    public Conversation addOpponentMessage(Message message, Long conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId).orElse(null);
        message.setConversationId(conversationId);
        conversation.addOpponentMessage(message);
        return conversationRepository.saveAndFlush(conversation);
    }

    @Override
    public Conversation getConversation(Long id) {
        return conversationRepository.findById(id).orElse(null);
    }

    @Override
    public Conversation getByAuthor(User author) {
        return conversationRepository.getByAuthor(author);
    }

    @Override
    public List<Conversation> getAllConversations() {
        return conversationRepository.findAll();
    }

    @Override
    public void deleteConversation(Long id) {
        conversationRepository.deleteById(id);
    }

    public void deleteMessageFromConversation(Long conversationId, Long messageId){
        Conversation conversation = getConversation(conversationId);
        Message message = messageRepository.findById(messageId).orElse(null);
        List<Message> authorMessages = conversation.getAuthorMessages();
        List<Message> opponentMessages = conversation.getOpponentMessages();
        authorMessages.remove(message);
        opponentMessages.remove(message);
        messageRepository.deleteById(messageId);
    }

    private User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
