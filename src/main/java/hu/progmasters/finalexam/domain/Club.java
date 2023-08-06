package hu.progmasters.finalexam.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Integer id;

    @Column(name = "club_name")
    private String name;

    @Column(name = "wins")
    private int wins;

    @OneToMany(mappedBy = "club")
    private List<Player> players;

    @OneToOne
    private Coach coach;


}
