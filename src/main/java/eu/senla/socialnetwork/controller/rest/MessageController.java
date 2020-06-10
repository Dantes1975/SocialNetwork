package eu.senla.socialnetwork.controller.rest;

import eu.senla.socialnetwork.dto.MessageDto;
import eu.senla.socialnetwork.serviceDto.MessageServiceDto;
import eu.senla.socialnetwork.serviceDto.impl.UserMessageServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageServiceDto messageServiceImpl;


    @Autowired
    public MessageController(UserMessageServiceDto messageServiceImpl) {
        this.messageServiceImpl = messageServiceImpl;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<MessageDto> messagesInfo() {
        return messageServiceImpl.messageList();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<MessageDto> userInfo(@PathVariable String userId) {
        return messageServiceImpl.messageListForUser(Long.parseLong(userId));
    }

    @PostMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void createMessage(@RequestBody MessageDto messageDto, @PathVariable String userId, @RequestHeader String from) {
        messageServiceImpl.addMessage(messageDto, Long.parseLong(userId), Long.parseLong(from));
    }

    @DeleteMapping("/{messageId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void messageDelete(@PathVariable String messageId) {
        messageServiceImpl.delete(Long.parseLong(messageId));
    }
}
