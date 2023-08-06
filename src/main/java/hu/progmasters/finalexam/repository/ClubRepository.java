package hu.progmasters.finalexam.repository;

import hu.progmasters.finalexam.domain.Club;
import hu.progmasters.finalexam.domain.Player;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class ClubRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Club save(Club toSave) {
        entityManager.persist(toSave);
        return toSave;
    }


    public Optional<Club> findAllSupStar(Integer id) {
        List<Club> ke = entityManager.createQuery("SELECT c FROM Club c ", Club.class).getResultList();
        Optional<Club> club = Optional.ofNullable(entityManager.find(Club.class, id));
        return Optional.ofNullable(entityManager.find(Club.class, id));
    }

    public Optional<Club> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Club.class, id));
    }
}
