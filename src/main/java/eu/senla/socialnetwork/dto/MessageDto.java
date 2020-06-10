package eu.senla.socialnetwork.dto;

import eu.senla.socialnetwork.model.Conversation;
import eu.senla.socialnetwork.model.Message;
import lombok.Data;

import java.sql.Date;

@Data
public class MessageDto {
    private Long id;
    private UserDto author;
    private Long conversationId;
    private String text;
    private Date departureDate;


    public Message toMessage() {
        Message message = new Message();
        message.setId(id);
        //message.setAuthor(author.toUser());
        message.setText(text);
        message.setDepartureDate(departureDate);
        return message;
    }

    public static MessageDto fromMessage(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setAuthor(UserDto.fromUser(message.getAuthor()));
        messageDto.setText(message.getText());
        messageDto.setDepartureDate(message.getDepartureDate());
        messageDto.setConversationId(message.getConversationId());
        return messageDto;
    }
}
