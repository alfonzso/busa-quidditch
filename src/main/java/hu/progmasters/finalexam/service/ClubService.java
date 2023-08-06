package hu.progmasters.finalexam.service;

import hu.progmasters.finalexam.domain.Club;
import hu.progmasters.finalexam.domain.Player;
import hu.progmasters.finalexam.dto.ClubCreatedCommand;
import hu.progmasters.finalexam.dto.ClubInfo;
import hu.progmasters.finalexam.dto.ClubWinnerInfo;
import hu.progmasters.finalexam.exceptionhandling.ClubNotFoundException;
import hu.progmasters.finalexam.exceptionhandling.GlobalExceptionHandler;
import hu.progmasters.finalexam.repository.ClubRepository;
import hu.progmasters.finalexam.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClubService {
    private ClubRepository clubRepository;
    private ModelMapper modelMapper;
    private GlobalExceptionHandler globalExceptionHandler;
    private PlayerRepository playerRepository;

    public ClubService(ClubRepository clubRepository, ModelMapper modelMapper, GlobalExceptionHandler globalExceptionHandler, PlayerRepository playerRepository) {
        this.clubRepository = clubRepository;
        this.modelMapper = modelMapper;
        this.globalExceptionHandler = globalExceptionHandler;
        this.playerRepository = playerRepository;
    }

    public ClubInfo saveClub(ClubCreatedCommand command) {
        Club toSave = modelMapper.map(command, Club.class);
        Club saved = clubRepository.save(toSave);
        return modelMapper.map(saved, ClubInfo.class);
    }

    public ClubInfo findById(Integer id) {
        return modelMapper.map(findClubById(id), ClubInfo.class);
    }

    public Club findClubById(Integer id) {
        Optional<Club> clubOptional = clubRepository.findById(id);
        if (clubOptional.isEmpty()) {
            throw new ClubNotFoundException(id);
        }
        return clubOptional.get();
    }

    public ClubWinnerInfo update(Integer id, ClubCreatedCommand command) throws ClubNotFoundException {
        Club toUpdate = findClubById(id);
        modelMapper.map(command, toUpdate);
        List<Player> players = playerRepository.playerLookByClubId(id);
        for (Player player : players) {
            player.setWins(player.getWins() + 1);
        }
        return modelMapper.map(toUpdate, ClubWinnerInfo.class);

    }
}
