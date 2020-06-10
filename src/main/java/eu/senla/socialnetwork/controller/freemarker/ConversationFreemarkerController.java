package eu.senla.socialnetwork.controller.freemarker;

import eu.senla.socialnetwork.dto.MessageDto;
import eu.senla.socialnetwork.model.Conversation;
import eu.senla.socialnetwork.model.Message;
import eu.senla.socialnetwork.model.User;
import eu.senla.socialnetwork.service.impl.UserConversationService;
import eu.senla.socialnetwork.service.impl.UserMessageService;
import eu.senla.socialnetwork.service.impl.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static eu.senla.socialnetwork.util.ApplicationConstant.*;

@Controller
@RequestMapping("/conversation")
public class ConversationFreemarkerController {
    private final UserConversationService conversationService;
    private final UserCredentialService userService;
    private final UserMessageService messageService;


    @Autowired
    public ConversationFreemarkerController(UserConversationService conversationService, UserCredentialService userService, UserMessageService messageService) {
        this.conversationService = conversationService;
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/page")
    public String registration(Map<String, Object> model) {
        return CONVERSATION_PAGE;
    }

    @PostMapping("/get")
    public String toConversation(@RequestParam String userId, @RequestParam String conversationId, Model model) {
        Conversation conversation = getConversation(conversationId);
        model.addAttribute(CONVERSATION_ID, conversation.getId());
        model.addAttribute(USER_ID, userId);
        model.addAttribute(AUTHOR_ID, conversation.getAuthor().getId());
        model.addAttribute(AUTHOR, conversation.getAuthor());
        model.addAttribute(IS_AUTHOR, conversation.getAuthor().equals(getUser(userId)));
        model.addAttribute(OPPONENT, conversation.getOpponent());
        model.addAttribute(OUTGOING, conversation.getAuthorMessages());
        model.addAttribute(INBOX, conversation.getOpponentMessages());
        return CONVERSATION_PAGE;
    }

    @GetMapping("/get/{conversationId}/{userId}")
    public String toConversationPage(@PathVariable String userId, @PathVariable String conversationId, Model model) {
        Conversation conversation = getConversation(conversationId);
        model.addAttribute(CONVERSATION_ID, conversation.getId());
        model.addAttribute(USER_ID, userId);
        model.addAttribute(AUTHOR_ID, conversation.getAuthor().getId());
        model.addAttribute(AUTHOR, conversation.getAuthor());
        model.addAttribute(IS_AUTHOR, conversation.getAuthor().equals(getUser(userId)));
        model.addAttribute(OPPONENT, conversation.getOpponent());
        model.addAttribute(OUTGOING, conversation.getAuthorMessages());
        model.addAttribute(INBOX, conversation.getOpponentMessages());
        return CONVERSATION_PAGE;
    }


    @PostMapping("/create")
    public String prepareToCreateMessage(@RequestParam String authorId, @RequestParam String opponentId, Model model) {
        Conversation conversation = conversationService.addConversation(Long.parseLong(authorId), Long.parseLong(opponentId));
        model.addAttribute(CONVERSATION_ID, conversation.getId());
        model.addAttribute(AUTHOR, conversation.getAuthor());
        model.addAttribute(OPPONENT, conversation.getOpponent());
        model.addAttribute(OUTGOING, conversation.getAuthorMessages());
        model.addAttribute(INBOX, conversation.getOpponentMessages());
        model.addAttribute(IS_AUTHOR, conversation.getAuthor().equals(getUser(authorId)));
        return CONVERSATION_PAGE;
    }

    @PostMapping("/send/author")
    public String createAuthorMessageFromConversation(@ModelAttribute MessageDto messageDto,
                                                      @RequestParam String conversationId,
                                                      @RequestParam String from,
                                                      @RequestParam String userId,
                                                      Model model) {
        Message message = messageService.addMessage(messageDto.toMessage(), Long.parseLong(userId), Long.parseLong(from));
        Conversation conversation = conversationService.addAuthorMessage(message, Long.parseLong(conversationId));
        model.addAttribute(CONVERSATION_ID, conversation.getId());
        model.addAttribute(IS_AUTHOR, conversation.getAuthor().equals(getUser(userId)));
        model.addAttribute(OPPONENT, conversation.getOpponent());
        model.addAttribute(OUTGOING, conversation.getAuthorMessages());
        model.addAttribute(INBOX, conversation.getOpponentMessages());
        return REDIRECT_CONVERSATION + conversationId + "/" + conversation.getAuthor().getId();
    }

    @PostMapping("/send/opponent")
    public String createOpponentMessageFromConversation(@ModelAttribute MessageDto messageDto,
                                                        @RequestParam String conversationId,
                                                        @RequestParam String from,
                                                        @RequestParam String userId,
                                                        Model model) {
        Message message = messageService.addMessage(messageDto.toMessage(), Long.parseLong(userId), Long.parseLong(from));
        Conversation conversation = conversationService.addOpponentMessage(message, Long.parseLong(conversationId));
        model.addAttribute(CONVERSATION_ID, conversation.getId());
        model.addAttribute(AUTHOR, conversation.getAuthor());
        model.addAttribute(OPPONENT, conversation.getOpponent());
        model.addAttribute(OUTGOING, conversation.getAuthorMessages());
        model.addAttribute(INBOX, conversation.getOpponentMessages());
        return REDIRECT_CONVERSATION + conversationId + "/" + conversation.getOpponent().getId();
    }

    @GetMapping("/mymessages")
    public String getUserMessages(@RequestParam String userId, Model model) {
        model.addAttribute(MESSAGES, messageService.messageListForUser(Long.parseLong(userId)));
        model.addAttribute(USER_ID, userId);
        return MESSAGES_PAGE;
    }

    @PostMapping("/delete")
    public String deleteUserMessage(@RequestParam String messageId,
                                    @RequestParam String userId,
                                    @RequestParam String conversationId,
                                    Model model) {
        Conversation conversation = getConversation(conversationId);
        conversationService.deleteMessageFromConversation(Long.parseLong(conversationId), Long.parseLong(messageId));
        model.addAttribute(CONVERSATION_ID, conversation.getId());
        model.addAttribute(AUTHOR, conversation.getAuthor());
        model.addAttribute(OPPONENT, conversation.getOpponent());
        model.addAttribute(OUTGOING, conversation.getAuthorMessages());
        model.addAttribute(INBOX, conversation.getOpponentMessages());
        return REDIRECT_CONVERSATION + conversationId + "/" + userId;
    }

    private User getUser(String userId) {
        return userService.findById(Long.parseLong(userId)).get();
    }

    private Conversation getConversation(String conversationId) {
        return conversationService.getConversation(Long.parseLong(conversationId));
    }
}
