package eu.senla.socialnetwork.serviceDto.impl;

import eu.senla.socialnetwork.dto.PostDto;
import eu.senla.socialnetwork.model.Post;
import eu.senla.socialnetwork.service.PostService;
import eu.senla.socialnetwork.service.impl.UserPostService;
import eu.senla.socialnetwork.serviceDto.PostServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserPostServiceDto implements PostServiceDto {
    private final PostService postService;

    @Autowired
    public UserPostServiceDto(UserPostService postService) {
        this.postService = postService;
    }

    @Override
    public List<PostDto> getAllPostsForUser(Long id) {
        List<Post> posts = postService.getAllPostsForUser(id);
        List<PostDto> result = new ArrayList<>();
        for (Post post : posts) {
            result.add(PostDto.fromPost(post));
        }
        return result;
    }

    @Override
    public boolean addPost(PostDto postDto, Long id) {
        Post post = postDto.toPost();
        postService.addPost(post, id);
        return true;
    }

    @Override
    public PostDto getPost(Long id) {
        Post post = postService.getPost(id);
        return PostDto.fromPost(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Post post = postService.updatePost(postDto.toPost(), id);
        return PostDto.fromPost(post);
    }

    @Override
    public void deletePost(Long id) {
        postService.deletePost(id);
    }

}
