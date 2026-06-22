package com.void_cine.lost_found_service.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.void_cine.lost_found_service.DTO.LostItemDTO;
import com.void_cine.lost_found_service.Model.LostItem;
import com.void_cine.lost_found_service.Service.LostItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/lost-items")
@Tag(name = "Perdida y hallazgo API", description = "Módulo de gestión e inventario de objetos perdidos en el cine")
public class LostItemController {
private final LostItemService lostItemService;

    public LostItemController(LostItemService lostItemService) {
        this.lostItemService = lostItemService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los objetos perdidos", description = "Retorna una colección de objetos enriquecida con enlaces relacionales HATEOAS")
    public List<LostItem> obtenerTodos() {
        return lostItemService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un objeto por ID")
    @ApiResponse(responseCode = "200", description = "Objeto encontrado exitosamente")
    @ApiResponse(responseCode = "404", description = "El ID del objeto no existe")
    public ResponseEntity<LostItem> obtenerPorId(@PathVariable Long id) {
        return lostItemService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public List<LostItem> obtenerPorEstado(@PathVariable String status) {
        return lostItemService.buscarPorEstado(status);
    }

    @PostMapping
    @Operation(summary = "Registrar un objeto encontrado", description = "Recibe los datos validados del objeto capturado por el personal de aseo")
    @ApiResponse(responseCode = "201", description = "Objeto registrado e inventariado en custodia")
    public ResponseEntity<LostItem> crear(@Valid @RequestBody LostItemDTO dto) {
        LostItem nuevoItem = lostItemService.guardar(dto);
        return new ResponseEntity<>(nuevoItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LostItem> modificar(@PathVariable Long id, @Valid @RequestBody LostItemDTO dto) {
        return lostItemService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un registro", description = "Elimina físicamente el ítem de la base de datos por ID")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        if (lostItemService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
