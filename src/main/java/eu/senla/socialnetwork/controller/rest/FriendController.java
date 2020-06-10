package eu.senla.socialnetwork.controller.rest;

import eu.senla.socialnetwork.dto.UserDto;
import eu.senla.socialnetwork.serviceDto.FriendServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {
    private final FriendServiceDto friendServiceDto;


    @Autowired
    public FriendController(FriendServiceDto friendServiceDto) {
        this.friendServiceDto = friendServiceDto;
    }

    @PostMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void createFriend(@RequestBody Long friendId, @PathVariable String userId) {
        friendServiceDto.addFriend(friendId, Long.parseLong(userId));
    }

    @GetMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<UserDto> userFriends(@PathVariable String userId) {
        return friendServiceDto.getFriends(Long.parseLong(userId));
    }

    @DeleteMapping("/{friendId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void userDelete(@PathVariable String friendId, @RequestParam String ownerId) {
        friendServiceDto.deleteFriendFromUser(Long.parseLong(friendId), Long.parseLong(ownerId));
    }
}
