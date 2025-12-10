# 🚀 Sistema Unificado - Gestión de Tareas y Proyectos

## ✨ Una sola aplicación, un solo comando

El sistema ahora está completamente unificado. Frontend y Backend en un solo archivo JAR ejecutable.

## 📋 Requisitos

- ✅ **Java 17 o superior**
- ✅ **MySQL Server 8.0** corriendo en puerto 3306
- ✅ **Base de datos:** `gestion_tareas`
- ✅ **Usuario MySQL:** `Oswaldo1` / `clave123`

## 🚀 Inicio Rápido

### 1. Verificar MySQL
```bash
netstat -an | findstr :3306
# Debe mostrar: TCP 0.0.0.0:3306 LISTENING
```

### 2. Ejecutar la aplicación
```bash
cd Proyecto_final
java -jar target/gestion-tareas-1.0.0.jar
```

### 3. Acceder al sistema
- **🌐 Aplicación Web:** http://localhost:8080
- **📚 API Swagger:** http://localhost:8080/swagger-ui.html
- **🔧 API REST:** http://localhost:8080/api

## ✅ Verificación

La aplicación está funcionando cuando veas:
```
Started GestionTareasApplication in X.XXX seconds
Tomcat started on port(s): 8080
```

## 🎯 Funcionalidades

- ✅ **Frontend Angular 21** integrado
- ✅ **Backend Spring Boot 3.2** 
- ✅ **API REST** completa
- ✅ **Documentación Swagger**
- ✅ **Base de datos MySQL**
- ✅ **CRUD completo** de Proyectos y Tareas
- ✅ **Dashboard** con estadísticas
- ✅ **Filtros y paginación**

## 🛠️ Troubleshooting

### Si MySQL no está corriendo:
```bash
net start MySQL80
```

### Si hay error de puerto ocupado:
```bash
netstat -ano | findstr :8080
# Cerrar proceso que use puerto 8080
```

### Recompilar si hay cambios:
```bash
# En fronted/
npm run build

# Copiar archivos
xcopy "fronted\dist\task-management\*" "Proyecto_final\src\main\resources\static\" /E /Y

# En Proyecto_final/
mvn clean package -DskipTests
```

---

**¡Sistema listo con un solo comando!** 🎉