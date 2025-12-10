# Diagramas UML - Sistema de Gestión de Tareas y Proyectos

## 1. Diagrama de Clases - Backend

```mermaid
classDiagram
    class Proyecto {
        -Long id
        -String name
        -String description
        -LocalDate startDate
        -LocalDate endDate
        -EstadoProyecto status
        -List~Tarea~ tareas
        +getId()
        +setId()
        +getName()
        +setName()
        +getDescription()
        +setDescription()
        +getStartDate()
        +setStartDate()
        +getEndDate()
        +setEndDate()
        +getStatus()
        +setStatus()
        +getTareas()
        +setTareas()
    }

    class Tarea {
        -Long id
        -String title
        -String description
        -EstadoTarea status
        -PrioridadTarea priority
        -LocalDate dueDate
        -Proyecto proyecto
        +getId()
        +setId()
        +getTitle()
        +setTitle()
        +getDescription()
        +setDescription()
        +getStatus()
        +setStatus()
        +getPriority()
        +setPriority()
        +getDueDate()
        +setDueDate()
        +getProyecto()
        +setProyecto()
    }

    class EstadoProyecto {
        <<enumeration>>
        ACTIVE
        COMPLETED
        ON_HOLD
    }

    class EstadoTarea {
        <<enumeration>>
        TODO
        IN_PROGRESS
        COMPLETED
    }

    class PrioridadTarea {
        <<enumeration>>
        LOW
        MEDIUM
        HIGH
    }

    Proyecto "1" --> "*" Tarea : contiene
    Proyecto --> EstadoProyecto : tiene
    Tarea --> EstadoTarea : tiene
    Tarea --> PrioridadTarea : tiene
    Tarea "*" --> "1" Proyecto : pertenece a
```

## 2. Diagrama de Arquitectura del Sistema

```mermaid
graph TB
    subgraph "Cliente - Navegador"
        Browser[Navegador Web]
    end

    subgraph "Frontend - Angular 21"
        AngularApp[Angular Application]
        Components[Components]
        Services[Services]
        Models[Models]
        Interceptors[HTTP Interceptors]
    end

    subgraph "Backend - Spring Boot 3.2"
        SpringApp[Spring Boot Application]
        Controllers[REST Controllers]
        ServicesLayer[Service Layer]
        Repositories[Repository Layer]
        Entities[JPA Entities]
    end

    subgraph "Base de Datos"
        MySQL[(MySQL Database<br/>gestion_tareas)]
    end

    Browser -->|HTTP :4200| AngularApp
    AngularApp --> Components
    Components --> Services
    Services --> Models
    Services --> Interceptors

    Services -->|HTTP REST API :8080| Controllers
    Controllers --> ServicesLayer
    ServicesLayer --> Repositories
    Repositories --> Entities
    Entities -->|JPA/Hibernate| MySQL

    style Browser fill:#e3f2fd
    style AngularApp fill:#fff3e0
    style SpringApp fill:#e8f5e9
    style MySQL fill:#fce4ec
```

## 3. Diagrama de Base de Datos (Modelo Entidad-Relación)

```mermaid
erDiagram
    PROYECTOS ||--o{ TAREAS : contiene
    
    PROYECTOS {
        bigint id PK
        varchar name
        text description
        date start_date
        date end_date
        varchar status
    }
    
    TAREAS {
        bigint id PK
        varchar title
        text description
        varchar status
        varchar priority
        date due_date
        bigint proyecto_id FK
    }
```

## Tecnologías Utilizadas

- **Frontend**: Angular 21, Angular Material, TypeScript, RxJS
- **Backend**: Spring Boot 3.2, Spring Data JPA, Hibernate
- **Base de Datos**: MySQL 8.0
- **Documentación**: Swagger/OpenAPI 3.0
- **Herramientas**: Maven, npm, Git

## URLs del Sistema

- **Frontend**: http://localhost:4200
- **Backend API**: http://localhost:8080/api
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **Base de Datos**: localhost:3306/gestion_tareas