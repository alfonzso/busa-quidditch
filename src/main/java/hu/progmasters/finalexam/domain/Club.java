package hu.progmasters.finalexam.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
//@Data
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWins(int wins) {
        this.wins += wins;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public Coach getCoach() {
        return coach;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
