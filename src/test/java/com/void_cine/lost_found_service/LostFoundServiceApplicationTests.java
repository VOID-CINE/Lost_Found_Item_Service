package com.void_cine.lost_found_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.void_cine.lost_found_service.DTO.LostItemDTO;
import com.void_cine.lost_found_service.Model.LostItem;
import com.void_cine.lost_found_service.Repository.LostItemRepository;
import com.void_cine.lost_found_service.Service.LostItemService;

@SpringBootTest
class LostFoundServiceApplicationTests {

	@Mock
    private LostItemRepository lostItemRepository;

    @InjectMocks
    private LostItemService lostItemService;

    @Test
    public void testListarTodos_DebeRetornarListaDeObjetos() {
        // Arrange (Preparar datos simulados)
        LostItem item = new LostItem();
        item.setId(1L);
        item.setItemName("Billetera de cuero");
        when(lostItemRepository.findAll()).thenReturn(List.of(item));

        // Act (Ejecutar el método real del servicio)
        List<LostItem> resultado = lostItemService.listarTodos();

        // Assert (Verificar expectativas de calidad)
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Billetera de cuero", resultado.get(0).getItemName());
        verify(lostItemRepository, times(1)).findAll();
    }

    @Test
    public void testGuardar_DebePersistirObjetoCorrectamente() {
        // Arrange
        LostItemDTO dto = new LostItemDTO(null, null, null, null, null, null);
        dto.setItemName("Audífonos Bluetooth");
        dto.setFoundLocation("Sala 4");
        dto.setFoundAt(LocalDateTime.now());
        dto.setStatus("EN_CUSTODIA");
        dto.setReporterStaffId(10L);

        LostItem itemEsperado = new LostItem();
        itemEsperado.setId(99L);
        itemEsperado.setItemName("Audífonos Bluetooth");

        when(lostItemRepository.save(any(LostItem.class))).thenReturn(itemEsperado);

        // Act
        LostItem resultado = lostItemService.guardar(dto);

        // Assert
        assertNotNull(resultado);
        assertEquals(99L, resultado.getId());
        assertEquals("Audífonos Bluetooth", resultado.getItemName());
        verify(lostItemRepository, times(1)).save(any(LostItem.class));
    }
}
