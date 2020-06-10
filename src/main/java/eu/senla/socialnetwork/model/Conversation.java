package eu.senla.socialnetwork.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Conversation {
    @Id
    @SequenceGenerator(name = "conversationIdSeq", sequenceName = "conversation_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "conversationIdSeq")
    private Long id;

    @OneToOne
    private User author;

    @OneToOne
    private User opponent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> authorMessages;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> opponentMessages;

    public void addAuthorMessage(Message message){
        authorMessages.add(message);
    }

    public void addOpponentMessage(Message message){
        opponentMessages.add(message);
    }
}
