-- ============================================
-- DATOS INICIALES PARA PRUEBAS
-- ============================================

USE gestion_tareas;

-- Limpiar datos existentes
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE tareas;
TRUNCATE TABLE proyectos;
SET FOREIGN_KEY_CHECKS = 1;

-- Insertar Proyectos
INSERT INTO proyectos (id, name, description, start_date, status) VALUES
(1, 'Sistema de Gestión', 'Proyecto principal del sistema de gestión de tareas', '2024-01-15 00:00:00', 'ACTIVE'),
(2, 'App Móvil', 'Desarrollo de aplicación móvil multiplataforma', '2024-02-01 00:00:00', 'ACTIVE'),
(3, 'Portal Web', 'Portal web corporativo con dashboard', '2024-01-20 00:00:00', 'ACTIVE'),
(4, 'API REST', 'Desarrollo de API REST para integración', '2024-03-01 00:00:00', 'COMPLETED'),
(5, 'Dashboard Analytics', 'Dashboard de análisis de datos en tiempo real', '2024-02-15 00:00:00', 'ON_HOLD');

-- Insertar Tareas
INSERT INTO tareas (id, title, description, status, priority, assignee_id, project_id, created_at) VALUES
(1, 'Diseñar base de datos', 'Crear el modelo de datos y relaciones', 'COMPLETED', 'HIGH', 1, 1, '2024-01-16 10:00:00'),
(2, 'Implementar API REST', 'Desarrollar endpoints del backend', 'IN_PROGRESS', 'HIGH', 2, 1, '2024-01-17 09:00:00'),
(3, 'Crear interfaz de usuario', 'Diseñar y desarrollar el frontend con Angular', 'IN_PROGRESS', 'MEDIUM', 1, 1, '2024-01-18 11:00:00'),
(4, 'Configurar servidor', 'Preparar ambiente de producción en AWS', 'TODO', 'MEDIUM', 3, 1, '2024-01-19 14:00:00'),
(5, 'Pruebas de integración', 'Realizar pruebas completas del sistema', 'TODO', 'HIGH', NULL, 1, '2024-01-20 08:00:00'),
(6, 'Diseño de mockups', 'Crear diseños de la aplicación móvil', 'COMPLETED', 'MEDIUM', 2, 2, '2024-02-02 10:00:00'),
(7, 'Desarrollo iOS', 'Implementar aplicación para iOS', 'IN_PROGRESS', 'HIGH', 1, 2, '2024-02-05 09:00:00'),
(8, 'Desarrollo Android', 'Implementar aplicación para Android', 'TODO', 'HIGH', 2, 2, '2024-02-06 09:00:00'),
(9, 'Documentación técnica', 'Escribir documentación completa del proyecto', 'TODO', 'LOW', NULL, 3, '2024-01-21 15:00:00'),
(10, 'Optimizar rendimiento', 'Mejorar velocidad de carga del portal', 'IN_PROGRESS', 'MEDIUM', 3, 3, '2024-01-22 10:00:00'),
(11, 'Implementar autenticación', 'Sistema de login y registro de usuarios', 'TODO', 'HIGH', 1, 1, '2024-01-23 09:00:00'),
(12, 'Configurar CI/CD', 'Pipeline de integración y despliegue continuo', 'TODO', 'MEDIUM', 2, 1, '2024-01-24 11:00:00'),
(13, 'Testing unitario', 'Escribir tests unitarios para el backend', 'IN_PROGRESS', 'MEDIUM', 3, 1, '2024-01-25 10:00:00'),
(14, 'Diseño responsive', 'Adaptar interfaz para dispositivos móviles', 'TODO', 'LOW', 1, 3, '2024-01-26 14:00:00'),
(15, 'Integración con APIs', 'Conectar con servicios externos', 'TODO', 'MEDIUM', NULL, 4, '2024-03-02 09:00:00');

-- Reiniciar auto_increment
ALTER TABLE proyectos AUTO_INCREMENT = 6;
ALTER TABLE tareas AUTO_INCREMENT = 16;

SELECT 'Datos iniciales insertados correctamente!' as mensaje;
SELECT COUNT(*) as total_proyectos FROM proyectos;
SELECT COUNT(*) as total_tareas FROM tareas;
