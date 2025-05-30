# Mini Gestor de Tareas en Java con API Simple

Una aplicación de consola simple escrita en Java para gestionar una lista de tareas pendientes. También expone una API HTTP básica para listar todas las tareas en formato JSON.

Este proyecto fue desarrollado como parte de un ejercicio de preparación.

## Tecnologías Usadas

*   Java (JDK 21)
*   Maven (para gestión de dependencias y construcción del proyecto)
*   `com.sun.net.httpserver` (para el servidor HTTP incorporado en Java)
*   Gson (para la serialización/deserialización de JSON)

## Requisitos Previos

*   Java Development Kit (JDK) versión 21 o superior instalado y configurado en el PATH del sistema.
*   Apache Maven instalado y configurado (o puedes importar el proyecto directamente en un IDE compatible con Maven como IntelliJ IDEA o Eclipse).

## Instrucciones para Ejecutar

1.  **Clonar el Repositorio:**
    ```bash
    git clone https://github.com/Amti502/amti-task-app.git
    ```

2.  **Navegar a la Carpeta del Proyecto:**
    ```bash
    cd amti-task-app
    ```

3.  **Opción A: Construir y Ejecutar con Maven (Recomendado para un JAR ejecutable):**
    *   Construir el proyecto (esto generará un archivo `.jar` en la carpeta `target/`):
        ```bash
        mvn clean package
        ```
    *   Ejecutar el archivo JAR generado (asegúrate de que el nombre del JAR coincida con el de tu carpeta `target` después de construir):
        ```bash
        java -jar target/amti-1.0-SNAPSHOT.jar
        ```
        (El nombre exacto del JAR es `amti-1.0-SNAPSHOT.jar` según tu `pom.xml`. Si cambias la versión o el `artifactId` en el `pom.xml`, este nombre cambiará).

4.  **Opción B: Ejecutar desde un IDE (como IntelliJ IDEA):**
    *   Importa el proyecto como un proyecto Maven.
    *   Localiza la clase `org.example.Main` (o `com.matias.taskmanager.Main` si renombraste tu paquete `groupId` a `com.matias.taskmanager` en el `pom.xml` y en tus archivos Java).
    *   Ejecuta el método `main` de esa clase.

## Cómo Usar la Aplicación de Consola

Una vez que la aplicación esté en ejecución, se presentará un menú en la consola con las siguientes opciones:

1.  **Agregar Tarea:** Pide una descripción y añade una nueva tarea a la lista.
2.  **Ver Tareas:** Muestra todas las tareas actuales con su ID, descripción y estado de completitud.
3.  **Marcar Tarea como Completada:** Pide el ID de una tarea para marcarla como completada.
4.  **Eliminar Tarea:** Pide el ID de una tarea para eliminarla de la lista.
5.  **Salir del Menú de Consola (y arrancar API si está implementada):** Termina la interacción con la consola e inicia el servidor API HTTP.

## Cómo Usar la API

Después de seleccionar la opción "5" en el menú de la consola, el servidor API se iniciará (por defecto en el puerto 8080).

*   **Endpoint:** `GET http://localhost:8080/api/tasks`
*   **Descripción:** Devuelve una lista de todas las tareas actualmente en memoria (las que hayas agregado/modificado por consola) en formato JSON.

## Autor

Matias Guzman

---

## (Opcional) Decisiones de Diseño o Limitaciones

*   Las tareas se guardan solo en memoria; se pierden cuando la aplicación se cierra. No hay persistencia en base de datos.
*   La API solo implementa el método GET para listar tareas. No hay endpoints para crear/modificar/eliminar tareas vía API.
*   Manejo de errores básico en la entrada de consola."# amti-task-app" 
