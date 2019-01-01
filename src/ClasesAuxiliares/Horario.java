/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAuxiliares;

import FamiliaAcciones.Accion;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class Horario {

    private int idHorario, idSchedule;
    private LocalTime Hora;
    private String condicionComida;
    private ArrayList<Accion> acciones = new ArrayList<>();

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    public LocalTime getHora() {
        return Hora;
    }

    public void setHora(LocalTime Hora) {
        this.Hora = Hora;
    }

    public String getCondicionComida() {
        return condicionComida;
    }

    public void setCondicionComida(String condicionComida) {
        this.condicionComida = condicionComida;
    }

    public ArrayList<Accion> getAcciones() {
        return acciones;
    }

    public void setAcciones(ArrayList<Accion> acciones) {
        this.acciones = acciones;
    }

    @Override
    public String toString() {
        return "Horario{" + "idHorario=" + idHorario + ", idSchedule=" + idSchedule + ", Hora=" + Hora + ", condicionComida=" + condicionComida + ", acciones=" + acciones + '}';
    }

    

}
