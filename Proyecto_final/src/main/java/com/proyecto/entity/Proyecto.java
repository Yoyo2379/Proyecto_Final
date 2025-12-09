package com.proyecto.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "proyectos")
public class Proyecto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(length = 1000)
    private String description;
    
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoProyecto status = EstadoProyecto.ACTIVE;
    
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    private List<Tarea> tareas;
    
    public Proyecto() {}
    
    public Proyecto(Long id, String name, String description, LocalDateTime startDate, EstadoProyecto status, List<Tarea> tareas) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.status = status;
        this.tareas = tareas;
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
    
    public LocalDateTime getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    
    public List<Tarea> getTareas() {
        return tareas;
    }
    
    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }
    
    public EstadoProyecto getStatus() {
        return status;
    }
    
    public void setStatus(EstadoProyecto status) {
        this.status = status;
    }
    
    @PrePersist
    protected void onCreate() {
        if (startDate == null) {
            startDate = LocalDateTime.now();
        }
        if (status == null) {
            status = EstadoProyecto.ACTIVE;
        }
    }
}
