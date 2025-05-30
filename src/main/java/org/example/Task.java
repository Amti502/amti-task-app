package org.example; // O tu paquete renombrado

public class Task {
    private static int proximoId = 1;
    private int id;
    private String descripcion;
    private boolean completada;

    public Task(String descripcionDeLaNuevaTarea) {
        this.id = proximoId;
        proximoId++;
        this.descripcion = descripcionDeLaNuevaTarea;
        this.completada = false;
    }

    public int getId() {
        return this.id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public boolean isCompletada() {
        return this.completada;
    }

    public void setDescripcion(String nuevaDescripcion) {
        this.descripcion = nuevaDescripcion;
    }

    public void setCompletada(boolean estado) {
        this.completada = estado;
    }

    @Override
    public String toString() {
        String estadoTexto;
        if (this.completada) {
            estadoTexto = "Sí";
        } else {
            estadoTexto = "No";
        }
        return "ID: " + this.id + ", Descripción: '" + this.descripcion + "', Completada: " + estadoTexto;
    }
}