package hu.progmasters.finalexam.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coach_id")
    private Integer id;

    @Column(name = "coach_name")
    private String name;

    @Column(name = "deleted")
    private boolean deleted;

    @OneToOne
    @JoinColumn(name = "club_id")
    private Club club;
}
