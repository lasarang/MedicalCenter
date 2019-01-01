/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FamiliaOperaciones;

import java.time.LocalDateTime;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public abstract class Operacion {

    protected int idOperacion, idPersona1, idPersona2;
    protected LocalDateTime fechaHoraInicio;
    protected String tipo;

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public int getIdPersona1() {
        return idPersona1;
    }

    public void setIdPersona1(int idPersona1) {
        this.idPersona1 = idPersona1;
    }

    public int getIdPersona2() {
        return idPersona2;
    }

    public void setIdPersona2(int idPersona2) {
        this.idPersona2 = idPersona2;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
