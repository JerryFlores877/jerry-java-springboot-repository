package com.example.interview.dto;

import java.time.LocalDateTime;

public class CruiseResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Integer capacity;
    private Double pricePerNight;
    private LocalDateTime createdAt;

    // Note: internalCode, updatedAt, isActive are NOT included
    // These are internal fields that clients don't need to see

    // Constructors
    public CruiseResponseDTO() {}

    public CruiseResponseDTO(Long id, String name, String description,
                             Integer capacity, Double pricePerNight, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}