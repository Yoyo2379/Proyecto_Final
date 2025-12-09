package com.proyecto.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EstadoProyecto {
    ACTIVE("active"),
    COMPLETED("completed"),
    ON_HOLD("on_hold");
    
    private final String value;
    
    EstadoProyecto(String value) {
        this.value = value;
    }
    
    @JsonValue
    public String getValue() {
        return value;
    }
    
    public static EstadoProyecto fromValue(String value) {
        for (EstadoProyecto estado : EstadoProyecto.values()) {
            if (estado.value.equals(value)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado de proyecto inválido: " + value);
    }
}
