package hu.progmasters.finalexam.repository;

import hu.progmasters.finalexam.domain.Coach;
import hu.progmasters.finalexam.dto.ClubStatistics;
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

    public Object createStatistics(int coachId) {
        return entityManager.createQuery("SELECT p.club.wins, AVG(p.wins), MAX(p.wins), MIN(p.wins) FROM Player p where p.club.coach.id IN : coachId")
                .setParameter("coachId", coachId)
                .getSingleResult();
    }
}
