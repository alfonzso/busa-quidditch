package hu.progmasters.finalexam.domain;

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
    @Column(name = "player_id")
    private Integer id;

    @Column(name = "player_name")
    private String name;

    @Column(name = "joined_date")
    @JsonDeserialize(as = LocalDate.class)
    private LocalDate joined;

    @Enumerated(EnumType.STRING)
    @Column(name = "player_type")
    private PlayerType playerType;

    @Column(name = "wins")
    private int wins;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
}
