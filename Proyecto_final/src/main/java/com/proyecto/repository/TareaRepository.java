package com.proyecto.repository;

import com.proyecto.entity.EstadoTarea;
import com.proyecto.entity.PrioridadTarea;
import com.proyecto.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long>, JpaSpecificationExecutor<Tarea> {
    
    List<Tarea> findByStatus(EstadoTarea status);
    
    List<Tarea> findByPriority(PrioridadTarea priority);
    
    List<Tarea> findByProyectoId(Long proyectoId);
    
    List<Tarea> findByStatusAndPriority(EstadoTarea status, PrioridadTarea priority);
    
    List<Tarea> findByStatusAndProyectoId(EstadoTarea status, Long proyectoId);
    
    List<Tarea> findByPriorityAndProyectoId(PrioridadTarea priority, Long proyectoId);
    
    @Query("SELECT COUNT(t) FROM Tarea t WHERE t.status = :status")
    Long countByStatus(EstadoTarea status);
    
    @Query("SELECT COUNT(t) FROM Tarea t WHERE t.priority = :priority")
    Long countByPriority(PrioridadTarea priority);
}
