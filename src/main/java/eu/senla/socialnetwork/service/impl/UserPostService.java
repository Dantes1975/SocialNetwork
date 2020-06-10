package eu.senla.socialnetwork.service.impl;

import eu.senla.socialnetwork.model.Post;
import eu.senla.socialnetwork.model.User;
import eu.senla.socialnetwork.repository.PostRepository;
import eu.senla.socialnetwork.repository.UserRepository;
import eu.senla.socialnetwork.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserPostService implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserPostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Post> getAllPostsForUser(Long id) {
        return findById(id).getPosts();
    }

    @Override
    public boolean addPost(Post post, Long id) {
        User user = findById(id);
        post.setAuthor(user);
        user.addPost(post);
        postRepository.save(post);
        return true;
    }

    @Override
    public Post getPost(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Post updatePost(Post post, Long id) {
        Post postFromDB = getPost(id);
        if (post.getPostText() != null) {
            postFromDB.setPostText(post.getPostText());
        }
        return postRepository.saveAndFlush(postFromDB);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    private User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
