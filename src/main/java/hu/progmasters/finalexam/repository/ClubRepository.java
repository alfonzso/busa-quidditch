package hu.progmasters.finalexam.repository;

import hu.progmasters.finalexam.domain.Club;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class ClubRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Club save(Club toSave) {
        entityManager.persist(toSave);
        return toSave;
    }

    public Optional<Club> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Club.class, id));
    }
}
