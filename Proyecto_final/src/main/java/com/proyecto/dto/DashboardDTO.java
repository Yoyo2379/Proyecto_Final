package com.proyecto.dto;

import java.util.Map;

public class DashboardDTO {
    private Long totalTareas;
    private Map<String, Long> tareasPorEstado;
    private Map<String, Long> tareasPorPrioridad;
    private Long totalProyectos;
    
    public DashboardDTO() {}
    
    public DashboardDTO(Long totalTareas, Map<String, Long> tareasPorEstado, 
                        Map<String, Long> tareasPorPrioridad, Long totalProyectos) {
        this.totalTareas = totalTareas;
        this.tareasPorEstado = tareasPorEstado;
        this.tareasPorPrioridad = tareasPorPrioridad;
        this.totalProyectos = totalProyectos;
    }
    
    public Long getTotalTareas() {
        return totalTareas;
    }
    
    public void setTotalTareas(Long totalTareas) {
        this.totalTareas = totalTareas;
    }
    
    public Map<String, Long> getTareasPorEstado() {
        return tareasPorEstado;
    }
    
    public void setTareasPorEstado(Map<String, Long> tareasPorEstado) {
        this.tareasPorEstado = tareasPorEstado;
    }
    
    public Map<String, Long> getTareasPorPrioridad() {
        return tareasPorPrioridad;
    }
    
    public void setTareasPorPrioridad(Map<String, Long> tareasPorPrioridad) {
        this.tareasPorPrioridad = tareasPorPrioridad;
    }
    
    public Long getTotalProyectos() {
        return totalProyectos;
    }
    
    public void setTotalProyectos(Long totalProyectos) {
        this.totalProyectos = totalProyectos;
    }
}
