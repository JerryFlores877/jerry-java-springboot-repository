package com.example.interview.controller;

import com.example.interview.service.CruiseService;
import com.example.interview.dto.CruiseRequestDTO;
import com.example.interview.dto.CruiseResponseDTO;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/cruises")
public class CruiseController {

    @Autowired
    private CruiseService cruiseService;

    // Create - POST
    // Client sends CruiseRequestDTO, receives CruiseResponseDTO
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CruiseResponseDTO> createCruise(@Valid @RequestBody CruiseRequestDTO requestDTO) {
        CruiseResponseDTO responseDTO = cruiseService.createCruise(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // Read All - GET
    @GetMapping
    public ResponseEntity<List<CruiseResponseDTO>> getAllCruises() {
        List<CruiseResponseDTO> cruises = cruiseService.getAllCruises();
        return ResponseEntity.ok(cruises);
    }

    // Read One by ID - GET
    @GetMapping("/{id}")
    public ResponseEntity<CruiseResponseDTO> getCruiseById(@PathVariable @Min(1) Long id) {
        CruiseResponseDTO cruise = cruiseService.getCruiseById(id);
        return ResponseEntity.ok(cruise);
    }

    // Read One by Name - GET
    @GetMapping("/name/{name}")
    public ResponseEntity<CruiseResponseDTO> getCruiseByName(@PathVariable String name) {
        CruiseResponseDTO cruise = cruiseService.getCruiseByName(name);
        return ResponseEntity.ok(cruise);
    }

    // Update by Name - PUT
    @PutMapping("/name/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CruiseResponseDTO> updateCruiseByName(
            @PathVariable String name,
            @Valid @RequestBody CruiseRequestDTO requestDTO) {
        CruiseResponseDTO updatedCruise = cruiseService.updateCruiseByName(name, requestDTO);
        return ResponseEntity.ok(updatedCruise);
    }

    // Delete by Name - DELETE
    @DeleteMapping("/name/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCruiseByName(@PathVariable String name) {
        cruiseService.deleteCruiseByName(name);
        return ResponseEntity.noContent().build();
    }
}