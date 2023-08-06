package hu.progmasters.finalexam.repository;

import hu.progmasters.finalexam.domain.Player;
import hu.progmasters.finalexam.domain.PlayerType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class PlayerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Player save(Player toSave) {
        entityManager.persist(toSave);
        return toSave;
    }

    public List<Player> playerLookByClubId(Integer clubId) {
        return entityManager.createQuery("select p from Player p where p.club.id = :clubId", Player.class)
                .setParameter("clubId", clubId)
                .getResultList();
    }

    public List<Player> findAll() {
//        return entityManager.createQuery("SELECT p.id, p.name, p.joined, p.playerType, p.wins, p.club FROM Player p ORDER BY p.joined DESC, p.wins ASC ", Player.class).getResultList();
        return entityManager.createQuery("SELECT p FROM Player p ORDER BY p.joined DESC, p.wins ASC ", Player.class).getResultList();
    }

    public List<PlayerType> findAllPlayerType(Integer clubId) {
        return entityManager.createQuery("SELECT p.playerType FROM Player p Where p.club.id = " + clubId, PlayerType.class).getResultList();
    }

    public Optional<Player> findById(int playerId) {
        return Optional.ofNullable(entityManager.find(Player.class, playerId));
    }
}
