package com.void_cine.lost_found_service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.void_cine.lost_found_service.Model.LostItem;

@Repository
public interface LostItemRepository extends JpaRepository<LostItem, Long> {
    // Permite filtrar por estado en la interfaz o App
    List<LostItem> findByStatus(String status);
}
