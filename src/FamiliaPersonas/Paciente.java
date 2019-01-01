/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FamiliaPersonas;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class Paciente extends Persona {
    private int nroHistoria;
    private LocalDate fechaNacimiento;
    private String genero, estadoCivil, grupoSanguineo;
    private ArrayList<String> ocupaciones;

    public int getNroHistoria() {
        return nroHistoria;
    }

    public void setNroHistoria(int nroHistoria) {
        this.nroHistoria = nroHistoria;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public ArrayList<String> getOcupaciones() {
        return ocupaciones;
    }

    public void setOcupaciones(ArrayList<String> ocupaciones) {
        this.ocupaciones = ocupaciones;
    }

}
