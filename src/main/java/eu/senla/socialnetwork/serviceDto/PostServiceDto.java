package eu.senla.socialnetwork.serviceDto;

import eu.senla.socialnetwork.dto.PostDto;

import java.util.List;

public interface PostServiceDto {
    List<PostDto> getAllPostsForUser(Long id);

    boolean addPost(PostDto postDto, Long id);

    PostDto getPost(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePost(Long id);
}
