package eu.senla.socialnetwork.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "usr")
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "usersIdSeq", sequenceName = "users_id_seq", initialValue = 4, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "usersIdSeq")
    private Long id;

    @Column
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String email;

    @Column
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Column
    private boolean enabled;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Message> myMessages;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToOne(fetch = FetchType.EAGER)
    Photo mainPhoto;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Photo> photos;

    @OneToMany
    private List<User> friends;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info")
    private Information information;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.name())
                ).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void addMessage(Message message) {
        myMessages.add(message);
    }

    public void addFriend(User user) {
        friends.add(user);
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void addPhoto(Photo photo) {
        photos.add(photo);
    }
}
