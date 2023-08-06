package hu.progmasters.finalexam.repository;

import hu.progmasters.finalexam.domain.Coach;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CoachRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Coach> findById(Integer coachId) {
        return Optional.ofNullable(entityManager.find(Coach.class, coachId));
    }

    public void delete(Coach coach) {
        entityManager.remove(coach);
    }

    public List createStatistics(int coachId) {
        return entityManager.createQuery("SELECT c.wins,AVG(p.wins),MAX(p.wins), MIN(p.wins) FROM Club c JOIN Player p where Coach.id IN : coachId")
                .setParameter("coachId", coachId)
                .getResultList();
    }
}
