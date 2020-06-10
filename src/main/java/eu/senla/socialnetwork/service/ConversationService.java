package eu.senla.socialnetwork.service;

import eu.senla.socialnetwork.model.Conversation;
import eu.senla.socialnetwork.model.Message;
import eu.senla.socialnetwork.model.User;

import java.util.List;

public interface ConversationService {
    Conversation addConversation(Long authorId, Long opponentId);
    Conversation addAuthorMessage(Message message, Long conversationId);
    Conversation addOpponentMessage(Message message, Long conversationId);
    Conversation getConversation(Long id);
    Conversation getByAuthor(User author);
    List<Conversation> getAllConversations();
    void deleteConversation(Long id);

}
