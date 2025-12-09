package com.proyecto.service.impl;

import com.proyecto.dto.DashboardDTO;
import com.proyecto.dto.TareaDTO;
import com.proyecto.entity.EstadoTarea;
import com.proyecto.entity.PrioridadTarea;
import com.proyecto.entity.Proyecto;
import com.proyecto.entity.Tarea;
import com.proyecto.repository.ProyectoRepository;
import com.proyecto.repository.TareaRepository;
import com.proyecto.service.TareaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TareaServiceImpl implements TareaService {
    
    private final TareaRepository tareaRepository;
    private final ProyectoRepository proyectoRepository;
    
    public TareaServiceImpl(TareaRepository tareaRepository, ProyectoRepository proyectoRepository) {
        this.tareaRepository = tareaRepository;
        this.proyectoRepository = proyectoRepository;
    }
    
    @Override
    @Transactional
    public TareaDTO crear(TareaDTO tareaDTO) {
        Proyecto proyecto = proyectoRepository.findById(tareaDTO.getProyectoId())
            .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + tareaDTO.getProyectoId()));
        
        Tarea tarea = new Tarea();
        tarea.setTitulo(tareaDTO.getTitulo());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        tarea.setEstado(tareaDTO.getEstado());
        tarea.setPrioridad(tareaDTO.getPrioridad());
        tarea.setUsuarioAsignado(tareaDTO.getUsuarioAsignado());
        tarea.setProyecto(proyecto);
        
        Tarea guardada = tareaRepository.save(tarea);
        return convertirADTO(guardada);
    }
    
    @Override
    @Transactional
    public TareaDTO actualizar(Long id, TareaDTO tareaDTO) {
        Tarea tarea = tareaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id: " + id));
        
        if (tareaDTO.getProyectoId() != null && !tareaDTO.getProyectoId().equals(tarea.getProyecto().getId())) {
            Proyecto proyecto = proyectoRepository.findById(tareaDTO.getProyectoId())
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + tareaDTO.getProyectoId()));
            tarea.setProyecto(proyecto);
        }
        
        tarea.setTitulo(tareaDTO.getTitulo());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        tarea.setEstado(tareaDTO.getEstado());
        tarea.setPrioridad(tareaDTO.getPrioridad());
        tarea.setUsuarioAsignado(tareaDTO.getUsuarioAsignado());
        
        Tarea actualizada = tareaRepository.save(tarea);
        return convertirADTO(actualizada);
    }
    
    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!tareaRepository.existsById(id)) {
            throw new RuntimeException("Tarea no encontrada con id: " + id);
        }
        tareaRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public TareaDTO obtenerPorId(Long id) {
        Tarea tarea = tareaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id: " + id));
        return convertirADTO(tarea);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TareaDTO> obtenerTodas() {
        return tareaRepository.findAll().stream()
            .map(this::convertirADTO)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TareaDTO> filtrarPorEstado(EstadoTarea estado) {
        return tareaRepository.findByEstado(estado).stream()
            .map(this::convertirADTO)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TareaDTO> filtrarPorPrioridad(PrioridadTarea prioridad) {
        return tareaRepository.findByPrioridad(prioridad).stream()
            .map(this::convertirADTO)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TareaDTO> filtrarPorProyecto(Long proyectoId) {
        return tareaRepository.findByProyectoId(proyectoId).stream()
            .map(this::convertirADTO)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TareaDTO> filtrarPorEstadoYPrioridad(EstadoTarea estado, PrioridadTarea prioridad) {
        return tareaRepository.findByEstadoAndPrioridad(estado, prioridad).stream()
            .map(this::convertirADTO)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public DashboardDTO obtenerDashboard() {
        DashboardDTO dashboard = new DashboardDTO();
        
        dashboard.setTotalTareas(tareaRepository.count());
        dashboard.setTotalProyectos(proyectoRepository.count());
        
        Map<String, Long> tareasPorEstado = new HashMap<>();
        for (EstadoTarea estado : EstadoTarea.values()) {
            tareasPorEstado.put(estado.name(), tareaRepository.countByEstado(estado));
        }
        dashboard.setTareasPorEstado(tareasPorEstado);
        
        Map<String, Long> tareasPorPrioridad = new HashMap<>();
        for (PrioridadTarea prioridad : PrioridadTarea.values()) {
            tareasPorPrioridad.put(prioridad.name(), tareaRepository.countByPrioridad(prioridad));
        }
        dashboard.setTareasPorPrioridad(tareasPorPrioridad);
        
        return dashboard;
    }
    
    private TareaDTO convertirADTO(Tarea tarea) {
        TareaDTO dto = new TareaDTO();
        dto.setId(tarea.getId());
        dto.setTitulo(tarea.getTitulo());
        dto.setDescripcion(tarea.getDescripcion());
        dto.setEstado(tarea.getEstado());
        dto.setPrioridad(tarea.getPrioridad());
        dto.setUsuarioAsignado(tarea.getUsuarioAsignado());
        dto.setProyectoId(tarea.getProyecto().getId());
        dto.setProyectoNombre(tarea.getProyecto().getNombre());
        dto.setFechaCreacion(tarea.getFechaCreacion());
        dto.setFechaActualizacion(tarea.getFechaActualizacion());
        return dto;
    }
}
