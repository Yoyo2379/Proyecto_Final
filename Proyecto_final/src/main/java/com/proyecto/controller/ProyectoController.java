package com.proyecto.controller;

import com.proyecto.dto.ProyectoDTO;
import com.proyecto.service.ProyectoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@Tag(name = "Projects", description = "API para gestión de proyectos")
public class ProyectoController {
    
    private final ProyectoService proyectoService;
    
    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }
    
    @PostMapping
    @Operation(summary = "Crear un nuevo proyecto")
    public ResponseEntity<ProyectoDTO> crear(@Valid @RequestBody ProyectoDTO proyectoDTO) {
        ProyectoDTO creado = proyectoService.crear(proyectoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
    
    @GetMapping
    @Operation(summary = "Obtener todos los proyectos con paginación")
    public ResponseEntity<org.springframework.data.domain.Page<ProyectoDTO>> obtenerTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String sort) {
        org.springframework.data.domain.Page<ProyectoDTO> proyectos = proyectoService.obtenerTodos(page, size, sort);
        return ResponseEntity.ok(proyectos);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un proyecto por ID")
    public ResponseEntity<ProyectoDTO> obtenerPorId(@PathVariable Long id) {
        ProyectoDTO proyecto = proyectoService.obtenerPorId(id);
        return ResponseEntity.ok(proyecto);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un proyecto")
    public ResponseEntity<ProyectoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProyectoDTO proyectoDTO) {
        ProyectoDTO actualizado = proyectoService.actualizar(id, proyectoDTO);
        return ResponseEntity.ok(actualizado);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un proyecto")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proyectoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
