package eu.senla.socialnetwork.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;



@Data
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @SequenceGenerator(name = "messagesIdSeq", sequenceName = "messages_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "messagesIdSeq")
    private Long id;

    @OneToOne
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;


    private Long conversationId;

    private String text;

    private Date departureDate;


}
