package eu.senla.socialnetwork.serviceDto;

import eu.senla.socialnetwork.dto.MessageDto;
import eu.senla.socialnetwork.model.Message;
import eu.senla.socialnetwork.model.User;

import java.util.List;

public interface MessageServiceDto {
    List<MessageDto> messageList();

    List<MessageDto> messageListForUser(Long id);

    MessageDto addMessage(MessageDto messageDto, Long id, Long from);

    void delete(Long id);
}
