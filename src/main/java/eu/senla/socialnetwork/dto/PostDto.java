package eu.senla.socialnetwork.dto;

import eu.senla.socialnetwork.model.Post;
import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private String postText;

    public Post toPost() {
        Post post = new Post();
        post.setId(id);
        post.setPostText(postText);
        return post;
    }

    public static PostDto fromPost(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setPostText(post.getPostText());
        return postDto;
    }
}
