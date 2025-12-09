package com.proyecto.repository;

import com.proyecto.entity.EstadoTarea;
import com.proyecto.entity.PrioridadTarea;
import com.proyecto.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    
    List<Tarea> findByEstado(EstadoTarea estado);
    
    List<Tarea> findByPrioridad(PrioridadTarea prioridad);
    
    List<Tarea> findByProyectoId(Long proyectoId);
    
    List<Tarea> findByEstadoAndPrioridad(EstadoTarea estado, PrioridadTarea prioridad);
    
    List<Tarea> findByEstadoAndProyectoId(EstadoTarea estado, Long proyectoId);
    
    List<Tarea> findByPrioridadAndProyectoId(PrioridadTarea prioridad, Long proyectoId);
    
    @Query("SELECT COUNT(t) FROM Tarea t WHERE t.estado = :estado")
    Long countByEstado(EstadoTarea estado);
    
    @Query("SELECT COUNT(t) FROM Tarea t WHERE t.prioridad = :prioridad")
    Long countByPrioridad(PrioridadTarea prioridad);
}
