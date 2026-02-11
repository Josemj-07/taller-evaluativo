# Proyecto Spring Boot con CI/CD

## 1. Instrucciones de ejecución

### Requisitos

* Java JDK 25 (Temurin u otro compatible)
* Git

### Ejecución en local

```bash
# Clonar el repositorio
git clone <url-del-repositorio>
cd <nombre-del-proyecto>

# Dar permisos al wrapper de Gradle
chmod +x gradlew

# Compilar y ejecutar pruebas
./gradlew clean build

# Ejecutar la aplicación
./gradlew bootRun
```

La aplicación se levanta por defecto en:

```
http://localhost:8089
```
esto porque tenía corriendo postgreSQL por el puerto 8080 que es el por defecto de Spring Boot, pero 
si se quiere modificar no hay ningun problema, solo es cambiar el atribut port del archivo application.yaml
para correrlo por el que se desee

---

## 2. Estrategia de Git utilizada

Se utilizó **GitFlow simplificado**, con las siguientes ramas principales:

* **`development`**: rama de desarrollo activo. Aquí se integran las ramas de funcionalidades.
* **`main`**: rama estable. Solo recibe código probado y validado desde `development`.

### Justificación

* Permite aislar el desarrollo de nuevas funcionalidades sin afectar la versión estable.
* Facilita la integración continua antes de pasar a producción.
* Es adecuada para proyectos académicos y equipos pequeños donde se requiere control y claridad del flujo.

El pipeline de CI/CD se introduce en `development` y se activa automáticamente al hacer merge a `main`.

---

## 3. Pipeline de CI/CD (GitHub Actions)

El proyecto cuenta con un pipeline de **Integración Continua** usando **GitHub Actions**, que se ejecuta en cada `push` o `pull request` hacia la rama `main`.

### Herramientas usadas

* **GitHub Actions**: Automatización del proceso de CI/CD.
* **Gradle Wrapper (`gradlew`)**: Gestión de dependencias, compilación y ejecución de pruebas.
* **Java 25 (Temurin)**: Entorno de ejecución del proyecto.

### Flujo del pipeline

1. Preparación del entorno (checkout del repositorio y configuración de Java).
2. Descarga automática de dependencias mediante Gradle.
3. Compilación del proyecto y ejecución de pruebas.
4. Generación del artefacto `.jar`.
5. Creación automática de un **GitHub Release** al hacer merge exitoso a `main`, adjuntando el `.jar` generado.

El directorio `build/` no se versiona; el ejecutable se conserva únicamente como artefacto o release del pipeline.

---

## 4. Notas finales

Este flujo garantiza que el proyecto puede compilar y ejecutarse correctamente en un entorno limpio, validando la estabilidad del código antes de considerarse una versión liberada.
