package com.proyecto.service;

import com.proyecto.dto.ProyectoDTO;
import org.springframework.data.domain.Page;

public interface ProyectoService {
    ProyectoDTO crear(ProyectoDTO proyectoDTO);
    ProyectoDTO actualizar(Long id, ProyectoDTO proyectoDTO);
    void eliminar(Long id);
    ProyectoDTO obtenerPorId(Long id);
    Page<ProyectoDTO> obtenerTodos(int page, int size, String sort);
}
