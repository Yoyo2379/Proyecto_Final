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

    class ProyectoDTO {
        -Long id
        -String name
        -String description
        -LocalDate startDate
        -LocalDate endDate
        -String status
        +toEntity()
        +fromEntity()
    }

    class TareaDTO {
        -Long id
        -String title
        -String description
        -String status
        -String priority
        -LocalDate dueDate
        -Long proyectoId
        -String proyectoName
        +toEntity()
        +fromEntity()
    }

    class ProyectoService {
        <<interface>>
        +findAll(Pageable) Page~Proyecto~
        +findById(Long) Optional~Proyecto~
        +save(Proyecto) Proyecto
        +deleteById(Long) void
    }

    class TareaService {
        <<interface>>
        +findAll(Pageable) Page~Tarea~
        +findById(Long) Optional~Tarea~
        +save(Tarea) Tarea
        +deleteById(Long) void
        +findByProyectoId(Long, Pageable) Page~Tarea~
        +countByStatus() Map~String, Long~
    }

    class ProyectoServiceImpl {
        -ProyectoRepository repository
        +findAll(Pageable) Page~Proyecto~
        +findById(Long) Optional~Proyecto~
        +save(Proyecto) Proyecto
        +deleteById(Long) void
    }

    class TareaServiceImpl {
        -TareaRepository repository
        +findAll(Pageable) Page~Tarea~
        +findById(Long) Optional~Tarea~
        +save(Tarea) Tarea
        +deleteById(Long) void
        +findByProyectoId(Long, Pageable) Page~Tarea~
        +countByStatus() Map~String, Long~
    }

    class ProyectoRepository {
        <<interface>>
        +findAll(Pageable) Page~Proyecto~
        +findById(Long) Optional~Proyecto~
        +save(Proyecto) Proyecto
        +deleteById(Long) void
    }

    class TareaRepository {
        <<interface>>
        +findAll(Pageable) Page~Tarea~
        +findById(Long) Optional~Tarea~
        +save(Tarea) Tarea
        +deleteById(Long) void
        +findByProyectoId(Long, Pageable) Page~Tarea~
        +countByStatus(EstadoTarea) Long
    }

    class ProyectoController {
        -ProyectoService service
        +getAllProyectos(Pageable) ResponseEntity
        +getProyectoById(Long) ResponseEntity
        +createProyecto(ProyectoDTO) ResponseEntity
        +updateProyecto(Long, ProyectoDTO) ResponseEntity
        +deleteProyecto(Long) ResponseEntity
    }

    class TareaController {
        -TareaService service
        +getAllTareas(Pageable) ResponseEntity
        +getTareaById(Long) ResponseEntity
        +createTarea(TareaDTO) ResponseEntity
        +updateTarea(Long, TareaDTO) ResponseEntity
        +deleteTarea(Long) ResponseEntity
        +getTareasByProyecto(Long, Pageable) ResponseEntity
    }

    class DashboardController {
        -TareaService tareaService
        +getTaskStatsByStatus() ResponseEntity
    }

    Proyecto "1" --> "*" Tarea : contiene
    Proyecto --> EstadoProyecto : tiene
    Tarea --> EstadoTarea : tiene
    Tarea --> PrioridadTarea : tiene
    Tarea "*" --> "1" Proyecto : pertenece a

    ProyectoController --> ProyectoService : usa
    TareaController --> TareaService : usa
    DashboardController --> TareaService : usa

    ProyectoService <|.. ProyectoServiceImpl : implementa
    TareaService <|.. TareaServiceImpl : implementa

    ProyectoServiceImpl --> ProyectoRepository : usa
    TareaServiceImpl --> TareaRepository : usa

    ProyectoController --> ProyectoDTO : usa
    TareaController --> TareaDTO : usa

    ProyectoDTO --> Proyecto : convierte
    TareaDTO --> Tarea : convierte
```

## 2. Diagrama de Componentes - Frontend Angular

```mermaid
graph TB
    subgraph "App Module"
        AppComponent[App Component]
        AppRoutes[App Routes]
        AppConfig[App Config]
    end

    subgraph "Core Module"
        ProjectService[Project Service]
        TaskService[Task Service]
        UserService[User Service]
        ErrorInterceptor[Error Interceptor]
        ProjectModel[Project Model]
        TaskModel[Task Model]
        UserModel[User Model]
    end

    subgraph "Shared Module"
        ConfirmDialog[Confirm Dialog]
        LoadingComponent[Loading Component]
    end

    subgraph "Features - Projects"
        ProjectRoutes[Project Routes]
        ProjectList[Project List Component]
        ProjectForm[Project Form Component]
    end

    subgraph "Features - Tasks"
        TaskRoutes[Task Routes]
        TaskList[Task List Component]
        TaskForm[Task Form Component]
    end

    subgraph "Features - Dashboard"
        DashboardRoutes[Dashboard Routes]
        DashboardComponent[Dashboard Component]
    end

    AppComponent --> AppRoutes
    AppComponent --> AppConfig
    AppRoutes --> ProjectRoutes
    AppRoutes --> TaskRoutes
    AppRoutes --> DashboardRoutes

    ProjectList --> ProjectService
    ProjectForm --> ProjectService
    TaskList --> TaskService
    TaskForm --> TaskService
    DashboardComponent --> TaskService

    ProjectService --> ProjectModel
    TaskService --> TaskModel
    UserService --> UserModel

    ProjectService --> ErrorInterceptor
    TaskService --> ErrorInterceptor

    ProjectList --> ConfirmDialog
    TaskList --> ConfirmDialog
    ProjectList --> LoadingComponent
    TaskList --> LoadingComponent
    DashboardComponent --> LoadingComponent

    style AppComponent fill:#e1f5ff
    style ProjectService fill:#fff4e1
    style TaskService fill:#fff4e1
    style ProjectList fill:#e8f5e9
    style TaskList fill:#e8f5e9
    style DashboardComponent fill:#f3e5f5
```

## 3. Diagrama de Secuencia - Crear Tarea

```mermaid
sequenceDiagram
    actor Usuario
    participant TaskForm as Task Form Component
    participant TaskService as Task Service
    participant HttpClient as HTTP Client
    participant Backend as Backend API
    participant TareaController as Tarea Controller
    participant TareaService as Tarea Service
    participant TareaRepository as Tarea Repository
    participant Database as MySQL Database

    Usuario->>TaskForm: Completa formulario y hace clic en "Guardar"
    TaskForm->>TaskForm: Valida formulario
    TaskForm->>TaskService: createTask(taskData)
    TaskService->>HttpClient: POST /api/tasks
    HttpClient->>Backend: HTTP POST Request
    Backend->>TareaController: createTarea(TareaDTO)
    TareaController->>TareaController: Valida datos
    TareaController->>TareaService: save(tarea)
    TareaService->>TareaRepository: save(tarea)
    TareaRepository->>Database: INSERT INTO tareas
    Database-->>TareaRepository: Tarea guardada
    TareaRepository-->>TareaService: Tarea entity
    TareaService-->>TareaController: Tarea entity
    TareaController-->>Backend: ResponseEntity(TareaDTO)
    Backend-->>HttpClient: HTTP 201 Created
    HttpClient-->>TaskService: Observable<Task>
    TaskService-->>TaskForm: Task creada
    TaskForm->>TaskForm: Muestra mensaje de éxito
    TaskForm->>Usuario: Redirige a lista de tareas
```

## 4. Diagrama de Secuencia - Listar Proyectos con Paginación

```mermaid
sequenceDiagram
    actor Usuario
    participant ProjectList as Project List Component
    participant ProjectService as Project Service
    participant HttpClient as HTTP Client
    participant Backend as Backend API
    participant ProyectoController as Proyecto Controller
    participant ProyectoService as Proyecto Service
    participant ProyectoRepository as Proyecto Repository
    participant Database as MySQL Database

    Usuario->>ProjectList: Accede a lista de proyectos
    ProjectList->>ProjectList: ngOnInit()
    ProjectList->>ProjectService: getProjects(page, size, sort)
    ProjectService->>HttpClient: GET /api/projects?page=0&size=10&sort=name,asc
    HttpClient->>Backend: HTTP GET Request
    Backend->>ProyectoController: getAllProyectos(Pageable)
    ProyectoController->>ProyectoService: findAll(pageable)
    ProyectoService->>ProyectoRepository: findAll(pageable)
    ProyectoRepository->>Database: SELECT * FROM proyectos LIMIT 10 OFFSET 0
    Database-->>ProyectoRepository: Lista de proyectos
    ProyectoRepository-->>ProyectoService: Page<Proyecto>
    ProyectoService-->>ProyectoController: Page<Proyecto>
    ProyectoController-->>Backend: ResponseEntity(Page<ProyectoDTO>)
    Backend-->>HttpClient: HTTP 200 OK + JSON
    HttpClient-->>ProjectService: Observable<PageResponse>
    ProjectService-->>ProjectList: PageResponse con proyectos
    ProjectList->>ProjectList: Actualiza dataSource
    ProjectList->>Usuario: Muestra tabla con proyectos

    Usuario->>ProjectList: Cambia de página
    ProjectList->>ProjectService: getProjects(page=1, size, sort)
    Note over ProjectList,Database: Se repite el flujo para la nueva página
```

## 5. Diagrama de Arquitectura del Sistema

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

## 6. Diagrama de Casos de Uso

```mermaid
graph LR
    Usuario((Usuario))

    subgraph "Sistema de Gestión"
        UC1[Listar Proyectos]
        UC2[Crear Proyecto]
        UC3[Editar Proyecto]
        UC4[Eliminar Proyecto]
        UC5[Listar Tareas]
        UC6[Crear Tarea]
        UC7[Editar Tarea]
        UC8[Eliminar Tarea]
        UC9[Filtrar Tareas]
        UC10[Ver Dashboard]
        UC11[Ver Estadísticas]
    end

    Usuario --> UC1
    Usuario --> UC2
    Usuario --> UC3
    Usuario --> UC4
    Usuario --> UC5
    Usuario --> UC6
    Usuario --> UC7
    Usuario --> UC8
    Usuario --> UC9
    Usuario --> UC10
    Usuario --> UC11

    UC6 -.->|requiere| UC1
    UC7 -.->|requiere| UC5
    UC9 -.->|extiende| UC5
    UC11 -.->|incluye| UC5

    style Usuario fill:#e1bee7
    style UC1 fill:#c5e1a5
    style UC2 fill:#c5e1a5
    style UC5 fill:#90caf9
    style UC6 fill:#90caf9
    style UC10 fill:#ffcc80
```

## 7. Diagrama de Base de Datos (Modelo Entidad-Relación)

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

## 8. Diagrama de Flujo - Proceso de Gestión de Tareas

```mermaid
flowchart TD
    Start([Inicio]) --> Dashboard[Ver Dashboard]
    Dashboard --> Decision1{¿Qué hacer?}
    
    Decision1 -->|Ver Proyectos| ListProjects[Listar Proyectos]
    Decision1 -->|Ver Tareas| ListTasks[Listar Tareas]
    Decision1 -->|Ver Estadísticas| ViewStats[Ver Estadísticas]
    
    ListProjects --> Decision2{¿Acción?}
    Decision2 -->|Crear| CreateProject[Crear Proyecto]
    Decision2 -->|Editar| EditProject[Editar Proyecto]
    Decision2 -->|Eliminar| DeleteProject[Confirmar y Eliminar]
    Decision2 -->|Volver| Dashboard
    
    CreateProject --> SaveProject[Guardar en BD]
    EditProject --> SaveProject
    SaveProject --> ListProjects
    DeleteProject --> ListProjects
    
    ListTasks --> Decision3{¿Acción?}
    Decision3 -->|Crear| CreateTask[Crear Tarea]
    Decision3 -->|Editar| EditTask[Editar Tarea]
    Decision3 -->|Eliminar| DeleteTask[Confirmar y Eliminar]
    Decision3 -->|Filtrar| FilterTasks[Aplicar Filtros]
    Decision3 -->|Volver| Dashboard
    
    CreateTask --> ValidateTask{¿Válida?}
    ValidateTask -->|Sí| SaveTask[Guardar en BD]
    ValidateTask -->|No| CreateTask
    
    EditTask --> ValidateTask
    SaveTask --> ListTasks
    DeleteTask --> ListTasks
    FilterTasks --> ListTasks
    
    ViewStats --> Dashboard
    
    style Start fill:#c8e6c9
    style Dashboard fill:#fff9c4
    style CreateProject fill:#bbdefb
    style CreateTask fill:#bbdefb
    style SaveProject fill:#c5e1a5
    style SaveTask fill:#c5e1a5
    style DeleteProject fill:#ffccbc
    style DeleteTask fill:#ffccbc
```

## 9. Diagrama de Despliegue

```mermaid
graph TB
    subgraph "Servidor de Desarrollo"
        subgraph "Puerto 4200"
            AngularDev[Angular Dev Server<br/>ng serve]
        end
        
        subgraph "Puerto 8080"
            SpringBoot[Spring Boot Application<br/>Tomcat Embedded]
        end
        
        subgraph "Puerto 3306"
            MySQLServer[MySQL Server<br/>gestion_tareas]
        end
    end

    subgraph "Cliente"
        WebBrowser[Navegador Web]
    end

    WebBrowser -->|HTTP| AngularDev
    AngularDev -->|REST API| SpringBoot
    SpringBoot -->|JDBC| MySQLServer

    style WebBrowser fill:#e3f2fd
    style AngularDev fill:#fff3e0
    style SpringBoot fill:#e8f5e9
    style MySQLServer fill:#fce4ec
```

## 10. Diagrama de Estados - Ciclo de Vida de una Tarea

```mermaid
stateDiagram-v2
    [*] --> TODO: Tarea creada
    
    TODO --> IN_PROGRESS: Iniciar trabajo
    TODO --> COMPLETED: Completar directamente
    
    IN_PROGRESS --> COMPLETED: Finalizar tarea
    IN_PROGRESS --> TODO: Pausar trabajo
    
    COMPLETED --> IN_PROGRESS: Reabrir tarea
    COMPLETED --> [*]: Archivar
    
    note right of TODO
        Estado inicial
        Prioridad: LOW, MEDIUM, HIGH
    end note
    
    note right of IN_PROGRESS
        Tarea en progreso
        Usuario trabajando
    end note
    
    note right of COMPLETED
        Tarea finalizada
        Lista para revisión
    end note
```

## Descripción de los Diagramas

### 1. Diagrama de Clases
Muestra todas las entidades del backend, sus relaciones, DTOs, servicios, repositorios y controladores.

### 2. Diagrama de Componentes
Representa la estructura modular del frontend Angular con sus módulos, componentes y servicios.

### 3. Diagrama de Secuencia - Crear Tarea
Flujo completo desde que el usuario crea una tarea hasta que se guarda en la base de datos.

### 4. Diagrama de Secuencia - Listar Proyectos
Muestra cómo funciona la paginación y el flujo de datos desde la BD hasta la UI.

### 5. Diagrama de Arquitectura
Vista general de las capas del sistema y cómo se comunican.

### 6. Diagrama de Casos de Uso
Todas las funcionalidades disponibles para el usuario.

### 7. Diagrama de Base de Datos
Modelo entidad-relación de las tablas MySQL.

### 8. Diagrama de Flujo
Proceso completo de gestión de tareas y proyectos.

### 9. Diagrama de Despliegue
Configuración de servidores y puertos en desarrollo.

### 10. Diagrama de Estados
Ciclo de vida de una tarea con sus transiciones de estado.

---

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
