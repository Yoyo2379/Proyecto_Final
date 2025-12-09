package com.proyecto.controller;

import com.proyecto.dto.TareaDTO;
import com.proyecto.entity.EstadoTarea;
import com.proyecto.entity.PrioridadTarea;
import com.proyecto.service.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tasks", description = "API para gestión de tareas")
public class TareaController {
    
    private final TareaService tareaService;
    
    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }
    
    @PostMapping
    @Operation(summary = "Crear una nueva tarea")
    public ResponseEntity<TareaDTO> crear(@Valid @RequestBody TareaDTO tareaDTO) {
        TareaDTO creada = tareaService.crear(tareaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }
    
    @GetMapping
    @Operation(summary = "Obtener todas las tareas con paginación y filtros")
    public ResponseEntity<org.springframework.data.domain.Page<TareaDTO>> obtenerTodas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sort,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) Long projectId,
            @RequestParam(required = false) Long assigneeId) {
        
        org.springframework.data.domain.Page<TareaDTO> tareas = tareaService.obtenerTodas(
            page, size, sort, status, priority, projectId, assigneeId);
        
        return ResponseEntity.ok(tareas);
    }
    
    @GetMapping("/stats/by-status")
    @Operation(summary = "Obtener estadísticas de tareas por estado")
    public ResponseEntity<java.util.Map<String, Long>> obtenerEstadisticasPorEstado() {
        java.util.Map<String, Long> stats = tareaService.obtenerEstadisticasPorEstado();
        return ResponseEntity.ok(stats);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una tarea por ID")
    public ResponseEntity<TareaDTO> obtenerPorId(@PathVariable Long id) {
        TareaDTO tarea = tareaService.obtenerPorId(id);
        return ResponseEntity.ok(tarea);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una tarea")
    public ResponseEntity<TareaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody TareaDTO tareaDTO) {
        TareaDTO actualizada = tareaService.actualizar(id, tareaDTO);
        return ResponseEntity.ok(actualizada);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una tarea")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tareaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
