package com.proyecto.dto;

import com.proyecto.entity.EstadoTarea;
import com.proyecto.entity.PrioridadTarea;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TareaDTO {
    private Long id;
    
    @NotBlank(message = "El título es obligatorio")
    private String titulo;
    
    private String descripcion;
    
    @NotNull(message = "El estado es obligatorio")
    private EstadoTarea estado;
    
    @NotNull(message = "La prioridad es obligatoria")
    private PrioridadTarea prioridad;
    
    private String usuarioAsignado;
    
    @NotNull(message = "El proyecto es obligatorio")
    private Long proyectoId;
    
    private String proyectoNombre;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    
    public TareaDTO() {}
    
    public TareaDTO(Long id, String titulo, String descripcion, EstadoTarea estado, PrioridadTarea prioridad,
                    String usuarioAsignado, Long proyectoId, String proyectoNombre, 
                    LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.prioridad = prioridad;
        this.usuarioAsignado = usuarioAsignado;
        this.proyectoId = proyectoId;
        this.proyectoNombre = proyectoNombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public EstadoTarea getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }
    
    public PrioridadTarea getPrioridad() {
        return prioridad;
    }
    
    public void setPrioridad(PrioridadTarea prioridad) {
        this.prioridad = prioridad;
    }
    
    public String getUsuarioAsignado() {
        return usuarioAsignado;
    }
    
    public void setUsuarioAsignado(String usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }
    
    public Long getProyectoId() {
        return proyectoId;
    }
    
    public void setProyectoId(Long proyectoId) {
        this.proyectoId = proyectoId;
    }
    
    public String getProyectoNombre() {
        return proyectoNombre;
    }
    
    public void setProyectoNombre(String proyectoNombre) {
        this.proyectoNombre = proyectoNombre;
    }
    
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }
    
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
