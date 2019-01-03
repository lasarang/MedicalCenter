/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAuxiliares;

import Extra.Validate;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class Tratamiento {

    private int idTratamiento, idTreatment, idSufferer;
    private LocalDate fechaInicio, fechaFin;
    private String medicacion, indicaciones;
    private ArrayList<LocalDate> fechas = new ArrayList<>();
    private ArrayList<Horario> horarios = new ArrayList<>();

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public int getIdTreatment() {
        return idTreatment;
    }

    public void setIdTreatment(int idTreatment) {
        this.idTreatment = idTreatment;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getMedicacion() {
        return medicacion;
    }

    public void setMedicacion(String medicacion) {
        this.medicacion = medicacion;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public ArrayList<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<Horario> horarios) {
        this.horarios = horarios;
    }

    public ArrayList<LocalDate> getFechas() {
        return fechas;
    }

    public void setFechas() {

        this.fechas = Validate.getDates(fechaInicio, fechaFin);

    }

    public int getIdSufferer() {
        return idSufferer;
    }

    public void setIdSufferer(int idSufferer) {
        this.idSufferer = idSufferer;
    }

    @Override
    public String toString() {
        return "Tratamiento{" + "idTratamiento=" + idTratamiento + ", idTreatment=" + idTreatment + ", idSufferer=" + idSufferer + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", medicacion=" + medicacion + ", indicaciones=" + indicaciones + ", fechas=" + fechas + ", horarios=" + horarios + '}';
    }

}
