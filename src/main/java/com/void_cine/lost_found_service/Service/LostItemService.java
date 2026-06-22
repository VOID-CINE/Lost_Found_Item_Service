package com.void_cine.lost_found_service.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.void_cine.lost_found_service.DTO.LostItemDTO;
import com.void_cine.lost_found_service.Model.LostItem;
import com.void_cine.lost_found_service.Repository.LostItemRepository;

@Service
public class LostItemService {
    private final LostItemRepository lostItemRepository;

    public LostItemService(LostItemRepository lostItemRepository){
        this.lostItemRepository = lostItemRepository;
    }

    public List<LostItem> listarTodos(){
        return lostItemRepository.findAll();
    }

    public Optional<LostItem> buscarPorId(Long id) {
        return lostItemRepository.findById(id);
    }

    public List<LostItem> buscarPorEstado(String status) {
        return lostItemRepository.findByStatus(status);
    }

    public LostItem guardar(LostItemDTO dto) {
        LostItem item = new LostItem();
        item.setItemName(dto.getItemName());
        item.setFoundLocation(dto.getFoundLocation());
        item.setFoundAt(dto.getFoundAt());
        item.setStatus(dto.getStatus());
        item.setClaimedByRut(dto.getClaimedByRut());
        item.setReporterStaffId(dto.getReporterStaffId());
        return lostItemRepository.save(item);
    }

    public Optional<LostItem> actualizar(Long id, LostItemDTO dto) {
        return lostItemRepository.findById(id).map(item -> {
            item.setItemName(dto.getItemName());
            item.setFoundLocation(dto.getFoundLocation());
            item.setFoundAt(dto.getFoundAt());
            item.setStatus(dto.getStatus());
            item.setClaimedByRut(dto.getClaimedByRut());
            item.setReporterStaffId(dto.getReporterStaffId());
            return lostItemRepository.save(item);
        });
    }

    public boolean eliminar(Long id) {
        if (lostItemRepository.existsById(id)) {
            lostItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
