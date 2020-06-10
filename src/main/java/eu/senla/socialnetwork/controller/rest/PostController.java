package eu.senla.socialnetwork.controller.rest;

import eu.senla.socialnetwork.dto.PostDto;
import eu.senla.socialnetwork.serviceDto.PostServiceDto;
import eu.senla.socialnetwork.serviceDto.impl.UserPostServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostServiceDto postServiceDto;

    @Autowired
    public PostController(UserPostServiceDto postServiceDto) {
        this.postServiceDto = postServiceDto;
    }

    @PostMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void createPost(@RequestBody PostDto postDto, @PathVariable String userId) {
        postServiceDto.addPost(postDto, Long.parseLong(userId));
    }

    @GetMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<PostDto> userPosts(@PathVariable String userId) {
        return postServiceDto.getAllPostsForUser(Long.parseLong(userId));
    }

    @PutMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public PostDto updatePost(@RequestBody PostDto postDto, @PathVariable String userId) {
        return postServiceDto.updatePost(postDto, Long.parseLong(userId));
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deletePost(@PathVariable String postId) {
        postServiceDto.deletePost(Long.parseLong(postId));
    }
}
