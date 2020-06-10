package eu.senla.socialnetwork.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @SequenceGenerator(name = "postsIdSeq", sequenceName = "posts_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "postsIdSeq")
    private Long id;

    @ManyToOne
    private User author;

    @ManyToOne
    private User owner;

    @Column
    private String postText;

    @Column
    private String fileName;
}
