package eu.senla.socialnetwork.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Information {
    @Id
    @SequenceGenerator(name = "informationIdSeq", sequenceName = "information_id_seq", initialValue = 4, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "informationIdSeq")
    private Long id;


    @Column(name = "birth_date")
    private Date birthDate;

    @Column
    private String education;

    @Column
    private String company;

    @Column
    private String aboutMe;

    public Information(Date date, String education, String company, String aboutMe) {
        this.birthDate = date;
        this.education = education;
        this.company = company;
        this.aboutMe = aboutMe;

    }
}
