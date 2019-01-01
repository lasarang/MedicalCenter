/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAuxiliares;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class Orden {

    private int idOrden, idOrder;
    private LocalDateTime fechaHoraAsistencia;
    private String descripcion;
    private ArrayList<String> parametros;//OrdenParametros

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public LocalDateTime getFechaHoraAsistencia() {
        return fechaHoraAsistencia;
    }

    public void setFechaHoraAsistencia(LocalDateTime fechaHoraAsistencia) {
        this.fechaHoraAsistencia = fechaHoraAsistencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<String> getParametros() {
        return parametros;
    }

    public void setParametros(ArrayList<String> parametros) {
        this.parametros = parametros;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    @Override
    public String toString() {
        return "Orden{" + "idOrden=" + idOrden + ", idOrder=" + idOrder + ", descripcion=" + descripcion + ", parametros=" + parametros + '}';
    }

}
