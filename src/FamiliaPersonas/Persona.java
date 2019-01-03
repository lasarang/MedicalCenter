/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FamiliaPersonas;

import java.util.ArrayList;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public abstract class Persona {

    protected String idPersona, nombre, tipo;
    protected ArrayList<String> telefonos, correos;
    protected ArrayList<String[]> domicilios;

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(ArrayList<String> telefonos) {
        this.telefonos = telefonos;
    }

    public ArrayList<String> getCorreos() {
        return correos;
    }

    public ArrayList<String[]> getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(ArrayList<String[]> domicilios) {
        this.domicilios = domicilios;
    }

    public void setCorreos(ArrayList<String> correos) {
        this.correos = correos;
    }

}
