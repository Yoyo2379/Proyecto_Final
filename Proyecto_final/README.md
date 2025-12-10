# Sistema de Gestión de Tareas y Proyectos

API REST desarrollada con Spring Boot para gestionar proyectos y tareas con funcionalidades de filtrado y dashboard.

## Características

- CRUD completo de Proyectos
- CRUD completo de Tareas
- Asignación de tareas a usuarios
- Filtros por estado, prioridad y proyecto
- Dashboard con estadísticas
- Documentación Swagger/OpenAPI
- Validación de datos
- Manejo global de excepciones

## Tecnologías

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- MySQL
- Swagger/OpenAPI
- Lombok
- Maven

## Estructura del Proyecto

```
src/main/java/com/proyecto/
├── controller/          # Controladores REST
├── service/            # Interfaces de servicios
├── service/impl/       # Implementaciones de servicios
├── repository/         # Repositorios JPA
├── entity/            # Entidades JPA
├── dto/               # Data Transfer Objects
└── config/            # Configuraciones
```

## Configuración

### Base de Datos

1. Crear base de datos MySQL:
```sql
CREATE DATABASE gestion_tareas;
```

2. Configurar credenciales en `src/main/resources/application.properties`:
```properties
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

## Ejecución

```bash
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

## Documentación API

Swagger UI: `http://localhost:8080/swagger-ui.html`

API Docs: `http://localhost:8080/api-docs`

## Endpoints Principales

### Proyectos
- `POST /api/proyectos` - Crear proyecto
- `GET /api/proyectos` - Listar proyectos
- `GET /api/proyectos/{id}` - Obtener proyecto
- `PUT /api/proyectos/{id}` - Actualizar proyecto
- `DELETE /api/proyectos/{id}` - Eliminar proyecto

### Tareas
- `POST /api/tareas` - Crear tarea
- `GET /api/tareas` - Listar tareas (con filtros opcionales)
- `GET /api/tareas/{id}` - Obtener tarea
- `PUT /api/tareas/{id}` - Actualizar tarea
- `DELETE /api/tareas/{id}` - Eliminar tarea

#### Filtros disponibles:
- `?estado=PENDIENTE` - Filtrar por estado
- `?prioridad=ALTA` - Filtrar por prioridad
- `?proyectoId=1` - Filtrar por proyecto
- Combinaciones: `?estado=EN_PROGRESO&prioridad=URGENTE`

### Dashboard
- `GET /api/dashboard` - Obtener estadísticas

## Enumeraciones

### Estados de Tarea
- `PENDIENTE`
- `EN_PROGRESO`
- `COMPLETADA`
- `CANCELADA`

### Prioridades
- `BAJA`
- `MEDIA`
- `ALTA`
- `URGENTE`

## Ejemplos de Uso

### Crear Proyecto
```json
POST /api/proyectos
{
  "nombre": "Proyecto Web",
  "descripcion": "Desarrollo de aplicación web"
}
```

### Crear Tarea
```json
POST /api/tareas
{
  "titulo": "Implementar login",
  "descripcion": "Crear sistema de autenticación",
  "estado": "PENDIENTE",
  "prioridad": "ALTA",
  "usuarioAsignado": "Juan Pérez",
  "proyectoId": 1
}
```

### Dashboard Response
```json
{
  "totalTareas": 15,
  "totalProyectos": 3,
  "tareasPorEstado": {
    "PENDIENTE": 5,
    "EN_PROGRESO": 7,
    "COMPLETADA": 3,
    "CANCELADA": 0
  },
  "tareasPorPrioridad": {
    "BAJA": 2,
    "MEDIA": 6,
    "ALTA": 5,
    "URGENTE": 2
  }
}
```

## Arquitectura

El proyecto sigue una arquitectura en capas:

1. **Controller**: Maneja las peticiones HTTP
2. **Service**: Lógica de negocio
3. **Repository**: Acceso a datos
4. **Entity**: Modelos de base de datos
5. **DTO**: Objetos de transferencia de datos
6. **Config**: Configuraciones de la aplicación
