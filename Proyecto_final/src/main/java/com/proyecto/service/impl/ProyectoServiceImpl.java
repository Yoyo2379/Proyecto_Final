package com.proyecto.service.impl;

import com.proyecto.dto.ProyectoDTO;
import com.proyecto.entity.Proyecto;
import com.proyecto.repository.ProyectoRepository;
import com.proyecto.service.ProyectoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProyectoServiceImpl implements ProyectoService {
    
    private final ProyectoRepository proyectoRepository;
    
    public ProyectoServiceImpl(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }
    
    @Override
    @Transactional
    public ProyectoDTO crear(ProyectoDTO proyectoDTO) {
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(proyectoDTO.getNombre());
        proyecto.setDescripcion(proyectoDTO.getDescripcion());
        
        Proyecto guardado = proyectoRepository.save(proyecto);
        return convertirADTO(guardado);
    }
    
    @Override
    @Transactional
    public ProyectoDTO actualizar(Long id, ProyectoDTO proyectoDTO) {
        Proyecto proyecto = proyectoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + id));
        
        proyecto.setNombre(proyectoDTO.getNombre());
        proyecto.setDescripcion(proyectoDTO.getDescripcion());
        
        Proyecto actualizado = proyectoRepository.save(proyecto);
        return convertirADTO(actualizado);
    }
    
    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!proyectoRepository.existsById(id)) {
            throw new RuntimeException("Proyecto no encontrado con id: " + id);
        }
        proyectoRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public ProyectoDTO obtenerPorId(Long id) {
        Proyecto proyecto = proyectoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + id));
        return convertirADTO(proyecto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProyectoDTO> obtenerTodos() {
        return proyectoRepository.findAll().stream()
            .map(this::convertirADTO)
            .collect(Collectors.toList());
    }
    
    private ProyectoDTO convertirADTO(Proyecto proyecto) {
        ProyectoDTO dto = new ProyectoDTO();
        dto.setId(proyecto.getId());
        dto.setNombre(proyecto.getNombre());
        dto.setDescripcion(proyecto.getDescripcion());
        dto.setFechaCreacion(proyecto.getFechaCreacion());
        return dto;
    }
}
