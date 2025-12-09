package com.proyecto.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PrioridadTarea {
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high");
    
    private final String value;
    
    PrioridadTarea(String value) {
        this.value = value;
    }
    
    @JsonValue
    public String getValue() {
        return value;
    }
    
    public static PrioridadTarea fromValue(String value) {
        for (PrioridadTarea prioridad : PrioridadTarea.values()) {
            if (prioridad.value.equals(value)) {
                return prioridad;
            }
        }
        throw new IllegalArgumentException("Prioridad inválida: " + value);
    }
}
