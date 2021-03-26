package edu.pja.sri.kmroz.sri02.services;

import edu.pja.sri.kmroz.sri02.dto.PlayerDTO;
import edu.pja.sri.kmroz.sri02.dto.mappers.PlayerMapper;
import edu.pja.sri.kmroz.sri02.models.Player;
import edu.pja.sri.kmroz.sri02.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findAll() {
        return this.playerRepository.findAll();
    }

    public Optional<Player> findById(Long id) {
        return this.playerRepository.findById(id);
    }

    public Player save(Player entity) {
        return this.playerRepository.save(entity);
    }

    public void deleteById(Long id) {
        this.playerRepository.deleteById(id);
    }
}
