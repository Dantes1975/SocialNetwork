package eu.senla.socialnetwork.serviceDto.impl;

import eu.senla.socialnetwork.dto.MessageDto;
import eu.senla.socialnetwork.model.Message;
import eu.senla.socialnetwork.service.MessageService;
import eu.senla.socialnetwork.service.impl.UserMessageService;
import eu.senla.socialnetwork.serviceDto.MessageServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMessageServiceDto implements MessageServiceDto {

    private final MessageService messageService;

    @Autowired
    public UserMessageServiceDto(UserMessageService messageService) {
        this.messageService = messageService;
    }

    public List<MessageDto> messageList() {
        List<Message> messages = messageService.messageList();
        List<MessageDto> result = new ArrayList<>();
        for (Message message : messages) {
            result.add(MessageDto.fromMessage(message));
        }
        return result;
    }

    public List<MessageDto> messageListForUser(Long id) {
        List<Message> messages = messageService.messageListForUser(id);
        List<MessageDto> result = new ArrayList<>();
        for (Message message : messages) {
            result.add(MessageDto.fromMessage(message));
        }
        return result;
    }

    @Override
    public MessageDto addMessage(MessageDto messageDto, Long id, Long from) {
        Message message = messageDto.toMessage();
        return MessageDto.fromMessage(messageService.addMessage(message, id, from));
    }

    @Override
    public void delete(Long id) {
        messageService.delete(id);
    }
}
