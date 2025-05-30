package org.example; // O tu paquete renombrado

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List; // Importa List

// Importa Gson si lo vas a usar
import com.google.gson.Gson;

public class SimpleApiServer {
    private TaskManager taskManager; // Referencia a nuestro gestor de tareas

    // Constructor que recibe el TaskManager
    public SimpleApiServer(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void startServer(int port) throws IOException {
        // Creamos el servidor HTTP en el puerto especificado
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Creamos un "contexto" o "ruta" para nuestra API.
        // Todas las peticiones a "/api/tasks" serán manejadas por TasksHandler
        server.createContext("/api/tasks", new TasksHandler());

        server.setExecutor(null); // Usamos el executor por defecto (un pool de hilos simple)
        server.start(); // Iniciamos el servidor
        System.out.println("Servidor API escuchando en el puerto: " + port + " en la ruta /api/tasks");
    }

    // Clase interna que manejará las peticiones HTTP para las tareas
    class TasksHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String responseBody = "";
            int statusCode = 200; // Código de estado HTTP OK por defecto

            // Solo vamos a manejar peticiones GET por ahora
            if ("GET".equals(exchange.getRequestMethod())) {
                try {
                    // Obtenemos la lista de tareas de nuestro TaskManager
                    List<Task> tasks = taskManager.getTareas(); // Asegúrate que este método exista en TaskManager

                    // Convertimos la lista de tareas a formato JSON usando Gson
                    Gson gson = new Gson();
                    responseBody = gson.toJson(tasks);

                    // Establecemos el tipo de contenido de la respuesta como JSON
                    exchange.getResponseHeaders().set("Content-Type", "application/json");

                } catch (Exception e) {
                    // Si algo sale mal, enviamos un error de servidor
                    responseBody = "{\"error\":\"Error interno del servidor al procesar las tareas\"}";
                    statusCode = 500;
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    e.printStackTrace(); // Imprime el error en la consola del servidor para depuración
                }
            } else {
                // Si es otro método (POST, PUT, DELETE, etc.), devolvemos "Método no permitido"
                responseBody = "{\"error\":\"Metodo no permitido. Solo GET es soportado en /api/tasks\"}";
                statusCode = 405; // 405 Method Not Allowed
                exchange.getResponseHeaders().set("Content-Type", "application/json");
            }

            // Enviamos los encabezados de la respuesta (código de estado y longitud del cuerpo)
            exchange.sendResponseHeaders(statusCode, responseBody.getBytes("UTF-8").length);
            // Obtenemos el stream de salida para escribir el cuerpo de la respuesta
            OutputStream os = exchange.getResponseBody();
            os.write(responseBody.getBytes("UTF-8")); // Escribimos el cuerpo
            os.close(); // Cerramos el stream
        }
    }
}