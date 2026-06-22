package com.void_cine.lost_found_service.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class LostItemDTO {
    @NotBlank(message = "El nombre del articulo es obligatorio")
    private String itemName;
    
    @NotBlank(message = "La ubicacion es obligatoria")
    private String foundLocation;

    @NotNull(message = "la fecha del hallazgo es obligatoria")
    @PastOrPresent(message = "la fecha del hallazgo no puede estar en el futuro")
    private LocalDateTime foundAt;
    
    @NotBlank(message = "El estado es obligatorio ")
    private String status;
    
    private String claimedByRut;

    @NotNull(message = "El ID es obligatorio")
    private Long reporterStaffId;
}
