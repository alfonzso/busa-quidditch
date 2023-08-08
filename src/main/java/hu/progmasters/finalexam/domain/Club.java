package hu.progmasters.finalexam.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    private int wins;

    @OneToOne(mappedBy = "club")
    private Coach coach;

    @OneToMany(mappedBy = "club")
    @JsonManagedReference
    private List<Player> players;

    public void setWins(int wins) {
        this.wins += wins;
    }

}
