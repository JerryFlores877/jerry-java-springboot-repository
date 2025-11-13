package com.example.interview.service;

import com.example.interview.exception.DuplicateCruiseException;
import com.example.interview.exception.ResourceNotFoundException;
import com.example.interview.model.Cruise;
import com.example.interview.repository.CruiseRepository;
import com.example.interview.dto.CruiseRequestDTO;
import com.example.interview.dto.CruiseResponseDTO;
import com.example.interview.dto.CruiseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CruiseService {
    @Autowired
    private CruiseRepository cruiseRepository;

    @Autowired
    private CruiseMapper cruiseMapper;

    // Create - Save cruise name and get auto-generated ID
    public CruiseResponseDTO createCruise(CruiseRequestDTO requestDTO) {
        // Check if cruise with same name already exists
        if (cruiseRepository.existsByName(requestDTO.getName())) {
            throw new DuplicateCruiseException("Cruise with name '" + requestDTO.getName() + "' already exists");
        }
        // Convert DTO to Entity
        Cruise cruise = cruiseMapper.toEntity(requestDTO);

        // Save to database
        Cruise savedCruise = cruiseRepository.save(cruise);

        // Convert Entity back to DTO and return
        return cruiseMapper.toResponseDTO(savedCruise);
    }

    // Read All
    public List<CruiseResponseDTO> getAllCruises() {
        List<Cruise> cruises = cruiseRepository.findAll();
        return cruiseMapper.toResponseDTOList(cruises);
    }

    // Read One by ID
    public CruiseResponseDTO getCruiseById(Long id) {
        Cruise cruise = cruiseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cruise not found with id: " + id));
        return cruiseMapper.toResponseDTO(cruise);
    }

    // Read One by Name
    public CruiseResponseDTO getCruiseByName(String name) {
        Cruise cruise = cruiseRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Cruise not found with name: " + name));
        return cruiseMapper.toResponseDTO(cruise);
    }

    // Update by Name
    public CruiseResponseDTO updateCruiseByName(String name, CruiseRequestDTO requestDTO) {
        Cruise cruise = cruiseRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Cruise not found with name: " + name));

        // Update entity with DTO data
        cruiseMapper.updateEntityFromDTO(cruise, requestDTO);

        // Save updated entity
        Cruise updatedCruise = cruiseRepository.save(cruise);

        // Return DTO
        return cruiseMapper.toResponseDTO(updatedCruise);
    }

    // Delete by Name
    @Transactional
    public void deleteCruiseByName(String name) {
        if (!cruiseRepository.existsByName(name)) {
            throw new ResourceNotFoundException("Cruise not found with name: " + name);
        }
        cruiseRepository.deleteByName(name);
    }
}
