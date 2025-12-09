package com.proyecto.dto;

import com.proyecto.entity.EstadoProyecto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class ProyectoDTO {
    private Long id;
    
    @NotBlank(message = "El nombre del proyecto es obligatorio")
    private String name;
    
    private String description;
    
    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate startDate;
    
    @NotNull(message = "El estado es obligatorio")
    private EstadoProyecto status;
    
    public ProyectoDTO() {}
    
    public ProyectoDTO(Long id, String name, String description, LocalDate startDate, EstadoProyecto status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.status = status;
    }
    
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
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public EstadoProyecto getStatus() {
        return status;
    }
    
    public void setStatus(EstadoProyecto status) {
        this.status = status;
    }
}
