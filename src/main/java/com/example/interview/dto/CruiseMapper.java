package com.example.interview.dto;

import com.example.interview.model.Cruise;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CruiseMapper {

    /**
     * Converts Request DTO to Entity
     * Used when creating or updating
     */
    public Cruise toEntity(CruiseRequestDTO dto) {
        Cruise cruise = new Cruise();
        cruise.setName(dto.getName());
        cruise.setDescription(dto.getDescription());
        cruise.setCapacity(dto.getCapacity());
        cruise.setPricePerNight(dto.getPricePerNight());
        cruise.setInternalCode(UUID.randomUUID().toString()); // Generate internal code
        cruise.setIsActive(true);
        cruise.setCreatedAt(LocalDateTime.now());
        return cruise;
    }

    /**
     * Converts Entity to Response DTO
     * Used when returning data to client
     */
    public CruiseResponseDTO toResponseDTO(Cruise entity) {
        CruiseResponseDTO dto = new CruiseResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCapacity(entity.getCapacity());
        dto.setPricePerNight(entity.getPricePerNight());
        dto.setCreatedAt(entity.getCreatedAt());
        // Note: internalCode is NOT mapped - it's internal only
        return dto;
    }

    /**
     * Converts list of entities to list of DTOs
     */
    public List<CruiseResponseDTO> toResponseDTOList(List<Cruise> entities) {
        return entities.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Updates existing entity with DTO data
     * Used for PUT operations
     */
    public void updateEntityFromDTO(Cruise entity, CruiseRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCapacity(dto.getCapacity());
        entity.setPricePerNight(dto.getPricePerNight());
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
