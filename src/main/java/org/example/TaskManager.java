package org.example; // O tu paquete renombrado

import java.util.ArrayList; // Necesitamos importar ArrayList para usarlo
import java.util.List;      // Necesitamos importar List para usarlo

public class TaskManager {

    // Atributo: una lista para guardar todos nuestros objetos Task
    // "private" significa que esta lista solo puede ser accedida directamente
    // desde dentro de la clase TaskManager.
    private List<Task> tareas; // Declaramos una variable llamada "tareas" que será una Lista de Tasks

    // Constructor: se ejecuta cuando creamos un objeto TaskManager
    public TaskManager() {
        // Inicializamos nuestra lista de tareas.
        // "new ArrayList<>()" crea una nueva lista vacía.
        this.tareas = new ArrayList<>();
        System.out.println("TaskManager inicializado. Lista de tareas creada.");
    }

    // Método para AGREGAR una nueva tarea a la lista
    public void agregarTarea(String descripcion) {
        // 1. Creamos un nuevo objeto Task usando la descripción que nos dan
        Task nuevaTarea = new Task(descripcion);
        // 2. Añadimos esa nueva tarea a nuestra lista "tareas"
        this.tareas.add(nuevaTarea);
        System.out.println("Tarea agregada: '" + descripcion + "' con ID: " + nuevaTarea.getId());
    }

    // Método para VER TODAS las tareas en la lista
    public void verTareas() {
        if (this.tareas.isEmpty()) { // isEmpty() revisa si la lista no tiene elementos
            System.out.println("No hay tareas en la lista.");
            return; // Salimos del método porque no hay nada que mostrar
        }

        System.out.println("\n--- Lista de Tareas ---");
        // Usamos un bucle "for-each" para recorrer cada Task en nuestra lista "tareas"
        for (Task tareaActual : this.tareas) {
            // Para cada tarea, llamamos a su método toString() para imprimirla
            // (System.out.println llama automáticamente a toString() si le pasas un objeto)
            System.out.println(tareaActual);
        }
        System.out.println("-----------------------");
    }

    // Método para BUSCAR una tarea por su ID (será útil para marcar y eliminar)
    private Task buscarTareaPorId(int idBuscado) {
        // Recorremos cada tarea en la lista
        for (Task tareaActual : this.tareas) {
            // Si el ID de la tarea actual es igual al ID que estamos buscando...
            if (tareaActual.getId() == idBuscado) {
                return tareaActual; // ...encontramos la tarea, así que la devolvemos
            }
        }
        return null; // Si recorrimos toda la lista y no encontramos la tarea, devolvemos null
    }

    // Método para MARCAR una tarea como COMPLETADA
    public void marcarTareaComoCompletada(int idTarea) {
        // 1. Buscamos la tarea usando su ID
        Task tareaEncontrada = buscarTareaPorId(idTarea);

        // 2. Verificamos si la encontramos
        if (tareaEncontrada != null) { // Si tareaEncontrada NO es null, significa que la encontramos
            tareaEncontrada.setCompletada(true); // Usamos el método setCompletada de la clase Task
            System.out.println("Tarea ID " + idTarea + " marcada como completada.");
        } else {
            // Si tareaEncontrada ES null, significa que no existe una tarea con ese ID
            System.out.println("Error: No se encontró una tarea con ID " + idTarea);
        }
    }

    // Método para ELIMINAR una tarea de la lista
    public void eliminarTarea(int idTarea) {
        // 1. Buscamos la tarea usando su ID
        Task tareaAEliminar = buscarTareaPorId(idTarea);

        // 2. Verificamos si la encontramos
        if (tareaAEliminar != null) {
            this.tareas.remove(tareaAEliminar); // El método remove() de la lista quita el objeto especificado
            System.out.println("Tarea ID " + idTarea + " eliminada.");
        } else {
            System.out.println("Error: No se encontró una tarea con ID " + idTarea + " para eliminar.");
        }
    }

    // (Opcional, pero útil para la API si la implementas después)
    // Método para obtener todas las tareas (para que otras clases puedan acceder a la lista si es necesario)
    public List<Task> getTareas() {
        return this.tareas;
    }
}