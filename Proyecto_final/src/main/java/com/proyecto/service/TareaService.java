package com.proyecto.service;

import com.proyecto.dto.DashboardDTO;
import com.proyecto.dto.TareaDTO;
import com.proyecto.entity.EstadoTarea;
import com.proyecto.entity.PrioridadTarea;
import java.util.List;

public interface TareaService {
    TareaDTO crear(TareaDTO tareaDTO);
    TareaDTO actualizar(Long id, TareaDTO tareaDTO);
    void eliminar(Long id);
    TareaDTO obtenerPorId(Long id);
    List<TareaDTO> obtenerTodas();
    List<TareaDTO> filtrarPorEstado(EstadoTarea estado);
    List<TareaDTO> filtrarPorPrioridad(PrioridadTarea prioridad);
    List<TareaDTO> filtrarPorProyecto(Long proyectoId);
    List<TareaDTO> filtrarPorEstadoYPrioridad(EstadoTarea estado, PrioridadTarea prioridad);
    DashboardDTO obtenerDashboard();
}
