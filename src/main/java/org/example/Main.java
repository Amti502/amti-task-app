package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException; // Importa IOException para el manejo de errores del servidor

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            // ... (todo tu menú de consola existente va aquí, sin cambios) ...
            // ... (switch case para las opciones 1 a 5) ...
            System.out.println("\n--- Mini Gestor de Tareas ---");
            System.out.println("1. Agregar Tarea");
            System.out.println("2. Ver Tareas");
            System.out.println("3. Marcar Tarea como Completada");
            System.out.println("4. Eliminar Tarea");
            System.out.println("5. Salir del Menú de Consola (y arrancar API si está implementada)");
            System.out.print("Seleccione una opción: ");

            int opcion = -1;

            try {
                if (scanner.hasNextInt()) {
                    opcion = scanner.nextInt();
                } else {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    scanner.next();
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next();
                continue;
            } finally {
                if (scanner.hasNextLine()){
                    scanner.nextLine();
                }
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la descripción de la tarea: ");
                    String descripcion = scanner.nextLine();
                    taskManager.agregarTarea(descripcion);
                    break;
                case 2:
                    taskManager.verTareas();
                    break;
                case 3:
                    System.out.print("Ingrese el ID de la tarea a marcar como completada: ");
                    try {
                        if (scanner.hasNextInt()) {
                            int idCompletar = scanner.nextInt();
                            if (scanner.hasNextLine()){scanner.nextLine();}
                            taskManager.marcarTareaComoCompletada(idCompletar);
                        } else {
                            System.out.println("ID inválido.");
                            if (scanner.hasNextLine()){scanner.nextLine();}
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ID inválido. Debe ser un número.");
                        if (scanner.hasNextLine()){scanner.nextLine();}
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el ID de la tarea a eliminar: ");
                    try {
                        if (scanner.hasNextInt()) {
                            int idEliminar = scanner.nextInt();
                            if (scanner.hasNextLine()){scanner.nextLine();}
                            taskManager.eliminarTarea(idEliminar);
                        } else {
                            System.out.println("ID inválido.");
                            if (scanner.hasNextLine()){scanner.nextLine();}
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ID inválido. Debe ser un número.");
                        if (scanner.hasNextLine()){scanner.nextLine();}
                    }
                    break;
                case 5:
                    salir = true;
                    System.out.println("Saliendo del menú de consola...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();

        // --- INICIAR EL SERVIDOR API DESPUÉS DE SALIR DEL MENÚ ---
        System.out.println("\nIniciando servidor API...");
        SimpleApiServer apiServer = new SimpleApiServer(taskManager); // Le pasamos nuestro taskManager
        try {
            apiServer.startServer(8080); // Elige un puerto, ej: 8080
            // El programa se quedará "escuchando" aquí hasta que lo detengas manualmente (Ctrl+C en la consola de IntelliJ)
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor API: " + e.getMessage());
            e.printStackTrace();
        }
    }
}