package com.proyecto.service.impl;

import com.proyecto.dto.ProyectoDTO;
import com.proyecto.entity.Proyecto;
import com.proyecto.repository.ProyectoRepository;
import com.proyecto.service.ProyectoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        proyecto.setName(proyectoDTO.getName());
        proyecto.setDescription(proyectoDTO.getDescription());
        proyecto.setStartDate(convertToLocalDateTime(proyectoDTO.getStartDate()));
        proyecto.setStatus(proyectoDTO.getStatus());
        
        Proyecto guardado = proyectoRepository.save(proyecto);
        return convertirADTO(guardado);
    }
    
    @Override
    @Transactional
    public ProyectoDTO actualizar(Long id, ProyectoDTO proyectoDTO) {
        Proyecto proyecto = proyectoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + id));
        
        proyecto.setName(proyectoDTO.getName());
        proyecto.setDescription(proyectoDTO.getDescription());
        proyecto.setStartDate(convertToLocalDateTime(proyectoDTO.getStartDate()));
        proyecto.setStatus(proyectoDTO.getStatus());
        
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
    public Page<ProyectoDTO> obtenerTodos(int page, int size, String sort) {
        // Parsear el sort (formato: "campo,direccion")
        String[] sortParams = sort.split(",");
        String sortField = sortParams[0];
        Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc") 
            ? Sort.Direction.DESC 
            : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        Page<Proyecto> proyectos = proyectoRepository.findAll(pageable);
        
        return proyectos.map(this::convertirADTO);
    }
    
    private ProyectoDTO convertirADTO(Proyecto proyecto) {
        ProyectoDTO dto = new ProyectoDTO();
        dto.setId(proyecto.getId());
        dto.setName(proyecto.getName());
        dto.setDescription(proyecto.getDescription());
        dto.setStartDate(convertToLocalDate(proyecto.getStartDate()));
        dto.setStatus(proyecto.getStatus());
        return dto;
    }
    
    private LocalDateTime convertToLocalDateTime(LocalDate localDate) {
        if (localDate == null) {
            return LocalDateTime.now();
        }
        return localDate.atStartOfDay();
    }
    
    private LocalDate convertToLocalDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return LocalDate.now();
        }
        return localDateTime.toLocalDate();
    }
}
