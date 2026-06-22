package com.void_cine.lost_found_service.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "lost_items")
@Data
public class LostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Qué objeto es (ej: "Celular iPhone 13", "Chaqueta de mezclilla")
    @Column(nullable = false)
    private String itemName;

    // Ubicación del hallazgo (ej: "Sala 3, Fila G, Asiento 14")
    @Column(nullable = false)
    private String foundLocation;

    // Fecha y hora en que se encontró el artículo
    @Column(nullable = false)
    private LocalDateTime foundAt;

    // Estados posibles: "EN_CUSTODIA", "RECLAMADO", "ENTREGADO"
    @Column(nullable = false)
    private String status;

    // RUT de la persona que retira el objeto (se llena al pasar a ENTREGADO)
    @Column(name = "claimed_by_rut")
    private String claimedByRut;

    // ID Remoto plano para saber qué trabajador de aseo/staff lo ingresó
    @Column(name = "reporter_staff_id", nullable = false)
    private Long reporterStaffId;
}