package eu.senla.socialnetwork.controller.freemarker;

import eu.senla.socialnetwork.dto.UserDto;
import eu.senla.socialnetwork.serviceDto.UserServiceDto;
import eu.senla.socialnetwork.serviceDto.impl.UserFriendServiceDto;
import eu.senla.socialnetwork.serviceDto.impl.UserServiceDtoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static eu.senla.socialnetwork.util.ApplicationConstant.*;

@Controller
@RequestMapping("/friends")
public class FriendFreemarkerController {

    private final UserFriendServiceDto friendServiceDto;
    private final UserServiceDto userServiceDto;

    @Autowired
    public FriendFreemarkerController(UserFriendServiceDto friendServiceDto, UserServiceDtoImpl userServiceDto) {
        this.friendServiceDto = friendServiceDto;
        this.userServiceDto = userServiceDto;
    }

    @PostMapping("/add")
    public String createFriend(@RequestParam String friendId, @RequestParam String userId, Model model) {
        friendServiceDto.addFriend(Long.parseLong(friendId), Long.parseLong(userId));
        model.addAttribute(INFORMATION, userServiceDto.findById(Long.parseLong(userId)).getInformation());
        model.addAttribute(USER, userServiceDto.findById(Long.parseLong(userId)));
        model.addAttribute(USER_ID, userId);
        return REDIRECT_FRIENDS + userId;
    }

    @GetMapping
    public String getUserFriends(@RequestParam String userId, Model model) {
        model.addAttribute(FRIENDS, userServiceDto.getFriendsByUserId(Long.parseLong(userId)));
        model.addAttribute(USERS, userServiceDto.getAll());
        model.addAttribute(USER_ID, userId);
        return FRIENDS_PAGE;
    }

    @GetMapping("/get/{userId}")
    public String getFriends(@PathVariable String userId, Model model) {
        model.addAttribute(FRIENDS, userServiceDto.getFriendsByUserId(Long.parseLong(userId)));
        model.addAttribute(USERS, userServiceDto.getAll());
        model.addAttribute(USER_ID, userId);
        return FRIENDS_PAGE;
    }

    @PostMapping("/delete")
    public String deleteFriendFromUser(@RequestParam String friendId, @RequestParam String userId, Model model) {
        friendServiceDto.deleteFriendFromUser(Long.parseLong(friendId), Long.parseLong(userId));
        model.addAttribute(FRIENDS, userServiceDto.getFriendsByUserId(Long.parseLong(userId)));
        model.addAttribute(INFORMATION, userServiceDto.findById(Long.parseLong(userId)).getInformation());
        model.addAttribute(USER_ID, userId);
        return REDIRECT_FRIENDS + userId;
    }

    @GetMapping("/information")
    public String getFriendInfo(@RequestParam String friendId, @RequestParam String userId, Model model) {
        UserDto userDto = userServiceDto.findById(Long.parseLong(friendId));
        model.addAttribute(INFORMATION, userDto.getInformation());
        model.addAttribute(USER, userDto);
        model.addAttribute(USER_ID, userId);
        model.addAttribute("friendId", friendId);
        return FRIEND_INFO_PAGE;
    }
}
