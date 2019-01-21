/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAuxiliares;

import FamiliaAcciones.Accion;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class HorarioAccion {

    private int idHorarioAccion, idAgenda, idActivity;
    private LocalDate fecha;
    private LocalTime horaEjecucion;
    private Accion accion;

    public int getIdHorarioAccion() {
        return idHorarioAccion;
    }

    public void setIdHorarioAccion(int idHorarioAccion) {
        this.idHorarioAccion = idHorarioAccion;
    }

    public int getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }

    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraEjecucion() {
        return horaEjecucion;
    }

    public void setHoraEjecucion(LocalTime horaEjecucion) {
        this.horaEjecucion = horaEjecucion;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }
    /*
    @Override
    public String toString() {
    return "HorarioAccion{" + "idHorarioAccion=" + idHorarioAccion + ", idAgenda=" + idAgenda + ", idActivity=" + idActivity + ", fecha=" + fecha + ", horaEjecucion=" + horaEjecucion + ", accion=" + accion + '}';
    }*/
}
