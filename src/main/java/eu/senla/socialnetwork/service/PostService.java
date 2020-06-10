package eu.senla.socialnetwork.service;

import eu.senla.socialnetwork.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPostsForUser(Long id);

    boolean addPost(Post post, Long id);

    Post getPost(Long id);

    Post updatePost(Post post, Long id);

    void deletePost(Long id);
}
