package hu.progmasters.finalexam.service;

import hu.progmasters.finalexam.domain.Club;
import hu.progmasters.finalexam.domain.Coach;
import hu.progmasters.finalexam.dto.ClubStatistics;
import hu.progmasters.finalexam.exceptionhandling.CoachNotFoundException;
import hu.progmasters.finalexam.exceptionhandling.NoPlayersInTheClubOfCoachException;
import hu.progmasters.finalexam.repository.ClubRepository;
import hu.progmasters.finalexam.repository.CoachRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CoachService {
    private ModelMapper modelMapper;
    private CoachRepository coachRepository;
    private ClubRepository clubRepository;

    public CoachService(ModelMapper modelMapper, CoachRepository coachRepository, ClubRepository clubRepository) {
        this.modelMapper = modelMapper;
        this.coachRepository = coachRepository;
        this.clubRepository = clubRepository;
    }

    public void delete(Integer coachId) {
        Coach deleteCoach = findCoachById(coachId);
        deleteCoach.setDeleted(true);
        Club club = deleteCoach.getClub();
        club.setCoach(null);
        deleteCoach.setClub(null);
        coachRepository.delete(deleteCoach);

    }

    public Coach findCoachById(Integer id) {
        Optional<Coach> coachOptional = coachRepository.findById(id);
        if (coachOptional.isEmpty()) {
            throw new CoachNotFoundException(id);
        }
        return coachOptional.get();
    }

    public ClubStatistics listStatistics(int coachId) throws CoachNotFoundException {
        Coach c = findCoachById(coachId);
        if (c == null) {
            throw new CoachNotFoundException(coachId);
        }
        if (c.getClub().getPlayers().size() == 0){
            throw new NoPlayersInTheClubOfCoachException(coachId);
        }
        Object[] k = (Object[]) coachRepository.createStatistics(coachId);
        return modelMapper.map(new ClubStatistics((int) k[0], (double) k[1], (int) k[2], (int) k[3]), ClubStatistics.class);
    }
}

