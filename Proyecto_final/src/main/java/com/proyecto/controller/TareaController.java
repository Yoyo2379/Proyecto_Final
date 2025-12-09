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
@RequestMapping("/api/tareas")
@Tag(name = "Tareas", description = "API para gestión de tareas")
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
    @Operation(summary = "Obtener todas las tareas con filtros opcionales")
    public ResponseEntity<List<TareaDTO>> obtenerTodas(
            @RequestParam(required = false) EstadoTarea estado,
            @RequestParam(required = false) PrioridadTarea prioridad,
            @RequestParam(required = false) Long proyectoId) {
        
        List<TareaDTO> tareas;
        
        if (estado != null && prioridad != null) {
            tareas = tareaService.filtrarPorEstadoYPrioridad(estado, prioridad);
        } else if (estado != null) {
            tareas = tareaService.filtrarPorEstado(estado);
        } else if (prioridad != null) {
            tareas = tareaService.filtrarPorPrioridad(prioridad);
        } else if (proyectoId != null) {
            tareas = tareaService.filtrarPorProyecto(proyectoId);
        } else {
            tareas = tareaService.obtenerTodas();
        }
        
        return ResponseEntity.ok(tareas);
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
