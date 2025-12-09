package com.proyecto.controller;

import com.proyecto.dto.DashboardDTO;
import com.proyecto.service.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@Tag(name = "Dashboard", description = "API para estadísticas del dashboard")
public class DashboardController {
    
    private final TareaService tareaService;
    
    public DashboardController(TareaService tareaService) {
        this.tareaService = tareaService;
    }
    
    @GetMapping
    @Operation(summary = "Obtener estadísticas del dashboard")
    public ResponseEntity<DashboardDTO> obtenerDashboard() {
        DashboardDTO dashboard = tareaService.obtenerDashboard();
        return ResponseEntity.ok(dashboard);
    }
}
