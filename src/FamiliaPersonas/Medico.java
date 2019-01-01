/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FamiliaPersonas;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * 
 * @author Luis A. Sarango-Parrales
 */
public class Medico extends Empleado{
    private int IdMedico;
    private String especialidad;
    private LocalTime horaAtencionInicio, horaAtencionFin;
   
    public int getIdMedico() {
        return IdMedico;
    }

    public void setIdMedico(int IdMedico) {
        this.IdMedico = IdMedico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public LocalTime getHoraAtencionInicio() {
        return horaAtencionInicio;
    }

    public void setHoraAtencionInicio(LocalTime horaAtencionInicio) {
        this.horaAtencionInicio = horaAtencionInicio;
    }

    public LocalTime getHoraAtencionFin() {
        return horaAtencionFin;
    }

    public void setHoraAtencionFin(LocalTime horaAtencionFin) {
        this.horaAtencionFin = horaAtencionFin;
    }

}
