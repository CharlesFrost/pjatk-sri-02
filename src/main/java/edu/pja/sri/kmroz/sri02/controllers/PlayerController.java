package edu.pja.sri.kmroz.sri02.controllers;

import edu.pja.sri.kmroz.sri02.dto.PlayerDTO;
import edu.pja.sri.kmroz.sri02.dto.mappers.PlayerMapper;
import edu.pja.sri.kmroz.sri02.models.Player;
import edu.pja.sri.kmroz.sri02.services.PlayerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(PlayerController.ENDPOINT)
public class PlayerController {

    public static final String ENDPOINT = "/api/players";

    private PlayerService playerService;
    private PlayerMapper playerMapper;


    public PlayerController(PlayerService playerService, PlayerMapper playerMapper) {
        this.playerService = playerService;
        this.playerMapper = playerMapper;
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getPlayers() {
        List<Player> allPlayers = playerService.findAll();
        List<PlayerDTO> result = allPlayers.stream()
                .map(this.playerMapper::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id) {
        Optional<Player> player = playerService.findById(id);
        if (player.isPresent()) {
            PlayerDTO playerDTO = this.playerMapper.convertToDto(player.get());
            return new ResponseEntity<>(playerDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity saveNewPlayer(@RequestBody @Valid PlayerDTO playerDTO) {
        Player entity = this.playerMapper.convertFromDto(playerDTO);
        this.playerService.save(entity);
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(ENDPOINT+"/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        headers.add("Location", location.toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePlayer(@PathVariable Long id,@Valid @RequestBody PlayerDTO playerDTO) {
        Optional<Player> currentPlayer = playerService.findById(id);
        if (currentPlayer.isPresent()) {
            playerDTO.setId(id);
            Player entity = this.playerMapper.convertFromDto(playerDTO);
            this.playerService.save(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id)
    {
        this.playerService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
