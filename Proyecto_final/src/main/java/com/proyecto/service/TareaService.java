package com.proyecto.service;

import com.proyecto.dto.TareaDTO;
import org.springframework.data.domain.Page;
import java.util.Map;

public interface TareaService {
    TareaDTO crear(TareaDTO tareaDTO);
    TareaDTO actualizar(Long id, TareaDTO tareaDTO);
    void eliminar(Long id);
    TareaDTO obtenerPorId(Long id);
    Page<TareaDTO> obtenerTodas(int page, int size, String sort, String status, String priority, Long projectId, Long assigneeId);
    Map<String, Long> obtenerEstadisticasPorEstado();
}
