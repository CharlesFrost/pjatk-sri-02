package edu.pja.sri.kmroz.sri02.dto.mappers;

import edu.pja.sri.kmroz.sri02.dto.PlayerDTO;
import edu.pja.sri.kmroz.sri02.models.Player;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {
    private ModelMapper modelMapper;

    public PlayerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Player convertFromDto(PlayerDTO playerDTO) {
        return modelMapper.map(playerDTO, Player.class);
    }

    public PlayerDTO convertToDto(Player player) {
        return modelMapper.map(player, PlayerDTO.class);
    }
}
