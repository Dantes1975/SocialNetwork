package eu.senla.socialnetwork.repository;

import eu.senla.socialnetwork.model.Conversation;
import eu.senla.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Conversation getByAuthor(User user);
}
