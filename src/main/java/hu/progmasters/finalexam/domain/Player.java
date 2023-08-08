package hu.progmasters.finalexam.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    @JsonDeserialize(as = LocalDate.class)
    private LocalDate joined;

    @Enumerated(EnumType.STRING)
    @Column
    private PlayerType playerType;

    @Column
    private int wins;

    @ManyToOne
    @JoinColumn(name = "club_id")
    @JsonBackReference
    private Club club;

    public void setWins(int wins) {
        this.wins += wins;
    }
}
