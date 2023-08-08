package hu.progmasters.finalexam.service;

import hu.progmasters.finalexam.domain.Club;
import hu.progmasters.finalexam.domain.Player;
import hu.progmasters.finalexam.domain.PlayerType;
import hu.progmasters.finalexam.dto.PlayerCreatedCommand;
import hu.progmasters.finalexam.dto.PlayerInfo;
import hu.progmasters.finalexam.exceptionhandling.ClubNotFoundException;
import hu.progmasters.finalexam.exceptionhandling.PlayerAlreadyJoinedException;
import hu.progmasters.finalexam.exceptionhandling.PlayerNotFoundException;
import hu.progmasters.finalexam.exceptionhandling.PlayerTypeMaxedException;
import hu.progmasters.finalexam.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlayerService {
    private final ClubService clubService;
    private final ModelMapper modelMapper;
    private final PlayerRepository playerRepository;
    private PlayerType playerType;

    public PlayerService(ClubService clubService, ModelMapper modelMapper, PlayerRepository playerRepository) {
        this.clubService = clubService;
        this.modelMapper = modelMapper;
        this.playerRepository = playerRepository;
    }

    public PlayerInfo savePlayer(PlayerCreatedCommand command) throws ClubNotFoundException {
        Club club = clubService.findClubById(command.getClubId());
        Player playerToSave = modelMapper.map(command, Player.class);

        if (club == null) {
            throw new ClubNotFoundException(command.getClubId());
        }

        if (this.playerTypeMaxed(playerToSave.getPlayerType(), club.getId())) {
            throw new PlayerTypeMaxedException(club.getId(), command.getPlayerType());
        }

        if (playerTypeIsCorrect(command)) {
            playerToSave.setClub(club);
            Player saved = playerRepository.save(playerToSave);
            PlayerInfo saveMapped = modelMapper.map(saved, PlayerInfo.class);
            saveMapped.setClubName(club.getName());
            return saveMapped;
        }
        throw new PlayerNotFoundException(command.getClubId());

    }

    public List<PlayerInfo> listPlayer() {
        return playerRepository.findAll();
    }

    public boolean playerTypeMaxed(PlayerType playerType, Integer clubId) {
        int maxPlayer = playerType.getMaxPlayerFromType();
        long countedType = playerRepository.findAllPlayerType(clubId).stream().filter(t -> t == playerType).count();
        return maxPlayer == countedType;
    }

    public boolean playerTypeIsCorrect(PlayerCreatedCommand command) {
        return command.getPlayerType() == PlayerType.CHASER || command.getPlayerType() == PlayerType.KEEPER || command.getPlayerType() == PlayerType.SEEKER || command.getPlayerType() == PlayerType.BEATER;
    }

    public Player findPlayerById(Integer id) {
        Optional<Player> playerOptional = playerRepository.findById(id);
        if (playerOptional.isEmpty()) {
            throw new PlayerNotFoundException(id);
        }
        return playerOptional.get();
    }

    public PlayerInfo update(int playerId, int clubId) {
        Club club = clubService.findClubById(clubId);
        Player playerToUpdate = findPlayerById(playerId);

        if (playerToUpdate == null) {
            throw new PlayerNotFoundException(playerId);
        }

        if (playerToUpdate.getClub().getId() == club.getId()) {
            throw new PlayerAlreadyJoinedException(club.getId(), playerToUpdate.getId());
        }

        if (this.playerTypeMaxed(playerToUpdate.getPlayerType(), club.getId())) {
            throw new PlayerTypeMaxedException(club.getId(), playerToUpdate.getPlayerType());
        }

        playerToUpdate.setJoined(LocalDate.now());
        playerToUpdate.setClub(club);
        PlayerInfo info = modelMapper.map(playerToUpdate, PlayerInfo.class);
        info.setClubName(club.getName());
        return info;
    }
}
