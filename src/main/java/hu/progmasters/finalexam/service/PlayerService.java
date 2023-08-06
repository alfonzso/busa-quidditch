package hu.progmasters.finalexam.service;

import hu.progmasters.finalexam.domain.Club;
import hu.progmasters.finalexam.domain.Player;
import hu.progmasters.finalexam.domain.PlayerType;
import hu.progmasters.finalexam.dto.AllPlayerInfo;
import hu.progmasters.finalexam.dto.PlayerCreatedCommand;
import hu.progmasters.finalexam.dto.PlayerInfo;
import hu.progmasters.finalexam.exceptionhandling.ClubNotFoundException;
import hu.progmasters.finalexam.exceptionhandling.PlayerNotFoundException;
import hu.progmasters.finalexam.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlayerService {
    private ClubService clubService;
    private ModelMapper modelMapper;
    private PlayerRepository playerRepository;
    private PlayerType playerType;

    public PlayerService(ClubService clubService, ModelMapper modelMapper, PlayerRepository playerRepository) {
        this.clubService = clubService;
        this.modelMapper = modelMapper;
        this.playerRepository = playerRepository;
    }

    public PlayerInfo savePlayer(PlayerCreatedCommand command) throws ClubNotFoundException {
        Player toSave = modelMapper.map(command, Player.class);
        Club clubForThePlayer = clubService.findClubById(command.getClubId());
        if (command.getClubId() > 0) {
            if (playerTypeIsCorrect(command)) {
                toSave.setClub(clubForThePlayer);
                Player saved = playerRepository.save(toSave);
                return modelMapper.map(saved, PlayerInfo.class);
            }
            throw new PlayerNotFoundException(command.getClubId());
        }

        throw new ClubNotFoundException(command.getClubId());
    }

    public List<AllPlayerInfo> listPlayer() {
        return playerRepository.findAll().stream()
                .map(player -> modelMapper.map(player, AllPlayerInfo.class))
                .collect(Collectors.toList());
    }

    public boolean playerTypeIsCorrect(PlayerCreatedCommand command) {
        if (command.getPlayerType() == PlayerType.CHASER || command.getPlayerType() == PlayerType.KEEPER || command.getPlayerType() == PlayerType.SEEKER || command.getPlayerType() == PlayerType.BEATER) {
            return true;
        }
        return false;
    }

    public Player findPlayerById(Integer id) {
        Optional<Player> playerOptional = playerRepository.findById(id);
        if (playerOptional.isEmpty()) {
            throw new PlayerNotFoundException(id);
        }
        return playerOptional.get();
    }

    public PlayerInfo update(int playerId, int clubId, PlayerCreatedCommand command) {
        if (playerTypeIsCorrect(command)) {
            clubService.findClubById(clubId);
            Player toUpdate = findPlayerById(playerId);
            command.setJoined(LocalDate.now());
            modelMapper.map(command, toUpdate);
            return modelMapper.map(toUpdate, PlayerInfo.class);
        }
        throw new PlayerNotFoundException(command.getClubId());
    }
}
