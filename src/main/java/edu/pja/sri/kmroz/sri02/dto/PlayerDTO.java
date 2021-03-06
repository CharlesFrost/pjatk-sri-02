package edu.pja.sri.kmroz.sri02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private Long id;
    @NotNull(message = "Nickname may not be null")
    @Size.List ({
            @Size(min=3, message="The nickname must be at least {min} characters"),
            @Size(max=20, message="The nickname must be less than {max} characters")
    })
    private String nickname;
    private Integer wins;
    private Integer losses;
    private Integer draws;
    @NotNull(message = "Register at date may not be null")
    @PastOrPresent
    private LocalDateTime registeredAt;
}
