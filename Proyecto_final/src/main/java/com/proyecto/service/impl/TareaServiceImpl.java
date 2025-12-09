package com.proyecto.service.impl;

import com.proyecto.dto.ProyectoDTO;
import com.proyecto.dto.TareaDTO;
import com.proyecto.entity.EstadoTarea;
import com.proyecto.entity.PrioridadTarea;
import com.proyecto.entity.Proyecto;
import com.proyecto.entity.Tarea;
import com.proyecto.repository.ProyectoRepository;
import com.proyecto.repository.TareaRepository;
import com.proyecto.service.TareaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Proyecto proyecto = proyectoRepository.findById(tareaDTO.getProjectId())
            .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + tareaDTO.getProjectId()));
        
        Tarea tarea = new Tarea();
        tarea.setTitle(tareaDTO.getTitle());
        tarea.setDescription(tareaDTO.getDescription());
        tarea.setStatus(tareaDTO.getStatus());
        tarea.setPriority(tareaDTO.getPriority());
        tarea.setAssigneeId(tareaDTO.getAssigneeId());
        tarea.setProyecto(proyecto);
        
        Tarea guardada = tareaRepository.save(tarea);
        return convertirADTO(guardada);
    }
    
    @Override
    @Transactional
    public TareaDTO actualizar(Long id, TareaDTO tareaDTO) {
        Tarea tarea = tareaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id: " + id));
        
        tarea.setTitle(tareaDTO.getTitle());
        tarea.setDescription(tareaDTO.getDescription());
        tarea.setStatus(tareaDTO.getStatus());
        tarea.setPriority(tareaDTO.getPriority());
        tarea.setAssigneeId(tareaDTO.getAssigneeId());
        
        if (tareaDTO.getProjectId() != null && !tareaDTO.getProjectId().equals(tarea.getProyecto().getId())) {
            Proyecto proyecto = proyectoRepository.findById(tareaDTO.getProjectId())
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + tareaDTO.getProjectId()));
            tarea.setProyecto(proyecto);
        }
        
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
    public Page<TareaDTO> obtenerTodas(int page, int size, String sort, String status, String priority, Long projectId, Long assigneeId) {
        // Parsear el sort
        String[] sortParams = sort.split(",");
        String sortField = sortParams[0];
        Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc") 
            ? Sort.Direction.DESC 
            : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        
        // Crear especificación para filtros
        Specification<Tarea> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (status != null && !status.isEmpty()) {
                try {
                    EstadoTarea estadoTarea = EstadoTarea.fromValue(status);
                    predicates.add(criteriaBuilder.equal(root.get("status"), estadoTarea));
                } catch (IllegalArgumentException e) {
                    // Ignorar si el estado no es válido
                }
            }
            
            if (priority != null && !priority.isEmpty()) {
                try {
                    PrioridadTarea prioridadTarea = PrioridadTarea.fromValue(priority);
                    predicates.add(criteriaBuilder.equal(root.get("priority"), prioridadTarea));
                } catch (IllegalArgumentException e) {
                    // Ignorar si la prioridad no es válida
                }
            }
            
            if (projectId != null) {
                predicates.add(criteriaBuilder.equal(root.get("proyecto").get("id"), projectId));
            }
            
            if (assigneeId != null) {
                predicates.add(criteriaBuilder.equal(root.get("assigneeId"), assigneeId));
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        
        Page<Tarea> tareas = tareaRepository.findAll(spec, pageable);
        return tareas.map(this::convertirADTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> obtenerEstadisticasPorEstado() {
        List<Tarea> todasLasTareas = tareaRepository.findAll();
        
        Map<String, Long> stats = new HashMap<>();
        stats.put("todo", todasLasTareas.stream().filter(t -> t.getStatus() == EstadoTarea.TODO).count());
        stats.put("in_progress", todasLasTareas.stream().filter(t -> t.getStatus() == EstadoTarea.IN_PROGRESS).count());
        stats.put("completed", todasLasTareas.stream().filter(t -> t.getStatus() == EstadoTarea.COMPLETED).count());
        
        return stats;
    }
    
    private TareaDTO convertirADTO(Tarea tarea) {
        TareaDTO dto = new TareaDTO();
        dto.setId(tarea.getId());
        dto.setTitle(tarea.getTitle());
        dto.setDescription(tarea.getDescription());
        dto.setStatus(tarea.getStatus());
        dto.setPriority(tarea.getPriority());
        dto.setAssigneeId(tarea.getAssigneeId());
        dto.setProjectId(tarea.getProyecto().getId());
        dto.setCreatedAt(tarea.getCreatedAt());
        
        // Agregar información del proyecto
        if (tarea.getProyecto() != null) {
            ProyectoDTO proyectoDTO = new ProyectoDTO();
            proyectoDTO.setId(tarea.getProyecto().getId());
            proyectoDTO.setName(tarea.getProyecto().getName());
            dto.setProject(proyectoDTO);
        }
        
        return dto;
    }
}
