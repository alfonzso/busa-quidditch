package hu.progmasters.finalexam.service;

import hu.progmasters.finalexam.domain.Club;
import hu.progmasters.finalexam.domain.Coach;
import hu.progmasters.finalexam.dto.ClubStatistics;
import hu.progmasters.finalexam.exceptionhandling.CoachNotFoundException;
import hu.progmasters.finalexam.repository.ClubRepository;
import hu.progmasters.finalexam.repository.CoachRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        deleteCoach.setClub(null);
        Club club = deleteCoach.getClub();
        club.setCoach(null);
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
        if (findCoachById(coachId) == null) {
            throw new CoachNotFoundException(coachId);
        }
        return (ClubStatistics) coachRepository.createStatistics(coachId).stream()
                .map(club -> modelMapper.map(club, ClubStatistics.class))
                .collect(Collectors.toList());
    }
}

