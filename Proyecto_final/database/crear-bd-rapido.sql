-- ============================================
-- SCRIPT RAPIDO - Solo crea la BD y tablas
-- Sin datos de ejemplo
-- ============================================

CREATE DATABASE IF NOT EXISTS gestion_tareas 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE gestion_tareas;

CREATE TABLE IF NOT EXISTS proyectos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(1000),
    fecha_creacion DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS tareas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descripcion VARCHAR(2000),
    estado VARCHAR(50) NOT NULL,
    prioridad VARCHAR(50) NOT NULL,
    usuario_asignado VARCHAR(255),
    proyecto_id BIGINT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    fecha_actualizacion DATETIME,
    FOREIGN KEY (proyecto_id) REFERENCES proyectos(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SELECT 'Base de datos creada!' as mensaje;
