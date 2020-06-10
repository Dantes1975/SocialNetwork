package eu.senla.socialnetwork.repository;

import eu.senla.socialnetwork.model.Message;
import eu.senla.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByAuthor(User author);
}
