/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAuxiliares;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class Cita {

    private String fecha, descripcion;

    public Cita(String fecha, String descripcion) {
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFechaHora(String fechaHora) {
        this.fecha = fechaHora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Cita{" + "fecha=" + fecha + ", descripcion=" + descripcion + '}';
    }

}
