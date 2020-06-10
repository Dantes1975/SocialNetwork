package eu.senla.socialnetwork.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @SequenceGenerator(name = "photosIdSeq", sequenceName = "photos_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "photosIdSeq")
    private Long id;

    @Column
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

}
