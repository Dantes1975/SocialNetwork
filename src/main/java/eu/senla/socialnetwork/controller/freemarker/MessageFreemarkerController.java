package eu.senla.socialnetwork.controller.freemarker;

import eu.senla.socialnetwork.dto.MessageDto;
import eu.senla.socialnetwork.serviceDto.MessageServiceDto;
import eu.senla.socialnetwork.serviceDto.UserServiceDto;
import eu.senla.socialnetwork.serviceDto.impl.UserMessageServiceDto;
import eu.senla.socialnetwork.serviceDto.impl.UserServiceDtoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static eu.senla.socialnetwork.util.ApplicationConstant.*;

@Controller
@RequestMapping("/messages")
public class MessageFreemarkerController {

    private final MessageServiceDto messageServiceImpl;
    private final UserServiceDto userServiceDto;

    public MessageFreemarkerController(UserMessageServiceDto messageServiceImpl, UserServiceDtoImpl userServiceDto) {
        this.messageServiceImpl = messageServiceImpl;
        this.userServiceDto = userServiceDto;
    }

    @PostMapping("/prepare")
    public String prepareToCreateMessage(@RequestParam String userId, @RequestParam String from, Model model) {
        model.addAttribute(FROM, from);
        model.addAttribute(USER_ID, userId);
        return CREATE_MESSAGES_PAGE;
    }

    @PostMapping("/send")
    public String createMessage(@ModelAttribute MessageDto messageDto,
                                @RequestParam String userId,
                                @RequestParam String from,
                                Model model) {
        model.addAttribute(MESSAGES, messageServiceImpl.messageListForUser(Long.parseLong(from)));
        model.addAttribute(INFORMATION, userServiceDto.findById(Long.parseLong(userId)).getInformation());
        model.addAttribute(USER_ID, from);
        model.addAttribute(CONVERSATION_ID, messageDto.getConversationId());
        return REDIRECT_USER + from;
    }

    @GetMapping("/mymessages")
    public String getUserMessages(@RequestParam String userId, Model model) {
        model.addAttribute(MESSAGES, messageServiceImpl.messageListForUser(Long.parseLong(userId)));
        model.addAttribute(USER_ID, userId);
        return MESSAGES_PAGE;
    }

    @PostMapping("/delete")
    public String deleteUserMessage(@RequestParam String messageId, @RequestParam String userId, Model model) {
        messageServiceImpl.delete(Long.parseLong(messageId));
        model.addAttribute(MESSAGES, messageServiceImpl.messageListForUser(Long.parseLong(userId)));
        model.addAttribute(USER_ID, userId);
        return MESSAGES_PAGE;
    }
}
