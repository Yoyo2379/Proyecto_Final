package com.proyecto.dto;

import com.proyecto.entity.EstadoTarea;
import com.proyecto.entity.PrioridadTarea;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TareaDTO {
    private Long id;
    
    @NotBlank(message = "El título es obligatorio")
    private String title;
    
    private String description;
    
    @NotNull(message = "El estado es obligatorio")
    private EstadoTarea status;
    
    @NotNull(message = "La prioridad es obligatoria")
    private PrioridadTarea priority;
    
    private Long assigneeId;
    
    @NotNull(message = "El proyecto es obligatorio")
    private Long projectId;
    
    private ProyectoDTO project;
    private LocalDateTime createdAt;
    
    public TareaDTO() {}
    
    public TareaDTO(Long id, String title, String description, EstadoTarea status, PrioridadTarea priority,
                    Long assigneeId, Long projectId, ProyectoDTO project, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.assigneeId = assigneeId;
        this.projectId = projectId;
        this.project = project;
        this.createdAt = createdAt;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public EstadoTarea getStatus() {
        return status;
    }
    
    public void setStatus(EstadoTarea status) {
        this.status = status;
    }
    
    public PrioridadTarea getPriority() {
        return priority;
    }
    
    public void setPriority(PrioridadTarea priority) {
        this.priority = priority;
    }
    
    public Long getAssigneeId() {
        return assigneeId;
    }
    
    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }
    
    public Long getProjectId() {
        return projectId;
    }
    
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    
    public ProyectoDTO getProject() {
        return project;
    }
    
    public void setProject(ProyectoDTO project) {
        this.project = project;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
