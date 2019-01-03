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
public class SignosVitales {
   private int idSignosVitales, idVitalSigns;
   private double pulso, frecuenciaRespiratoria, presionSistolica, presionDiastolica,
                  saturacionOxigeno, temperatura, talla, peso;

    public int getIdSignosVitales() {
        return idSignosVitales;
    }

    public void setIdSignosVitales(int idSignosVitales) {
        this.idSignosVitales = idSignosVitales;
    }

    public int getIdVitalSigns() {
        return idVitalSigns;
    }

    public void setIdVitalSigns(int idVitalSigns) {
        this.idVitalSigns = idVitalSigns;
    }

    public double getPulso() {
        return pulso;
    }

    public void setPulso(double pulso) {
        this.pulso = pulso;
    }

    public double getFrecuenciaRespiratoria() {
        return frecuenciaRespiratoria;
    }

    public void setFrecuenciaRespiratoria(double frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public double getPresionSistolica() {
        return presionSistolica;
    }

    public void setPresionSistolica(double presionSistolica) {
        this.presionSistolica = presionSistolica;
    }

    public double getPresionDiastolica() {
        return presionDiastolica;
    }

    public void setPresionDiastolica(double presionDiastolica) {
        this.presionDiastolica = presionDiastolica;
    }

    public double getSaturacionOxigeno() {
        return saturacionOxigeno;
    }

    public void setSaturacionOxigeno(double saturacionOxigeno) {
        this.saturacionOxigeno = saturacionOxigeno;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "SignosVitales{" + "idSignosVitales=" + idSignosVitales + ", idVitalSigns=" + idVitalSigns + ", pulso=" + pulso + ", frecuenciaRespiratoria=" + frecuenciaRespiratoria + ", presionSistolica=" + presionSistolica + ", presionDiastolica=" + presionDiastolica + ", saturacionOxigeno=" + saturacionOxigeno + ", temperatura=" + temperatura + ", talla=" + talla + ", peso=" + peso + '}';
    }
   
   

}
