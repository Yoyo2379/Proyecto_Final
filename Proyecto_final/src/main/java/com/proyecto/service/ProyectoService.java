package com.proyecto.service;

import com.proyecto.dto.ProyectoDTO;
import java.util.List;

public interface ProyectoService {
    ProyectoDTO crear(ProyectoDTO proyectoDTO);
    ProyectoDTO actualizar(Long id, ProyectoDTO proyectoDTO);
    void eliminar(Long id);
    ProyectoDTO obtenerPorId(Long id);
    List<ProyectoDTO> obtenerTodos();
}
