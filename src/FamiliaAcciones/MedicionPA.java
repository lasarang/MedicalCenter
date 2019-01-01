/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FamiliaAcciones;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class MedicionPA extends Accion {

    private int idMedicionPA, idMeasurePA = getIdAccion();
    private double presionSistolica, presionDiastolica, pulso;
    
    public MedicionPA(){
        setTipo("MedicionPA");
    }

    public int getIdMedicionPA() {
        return idMedicionPA;
    }

    public void setIdMedicionPA(int idMedicionPA) {
        this.idMedicionPA = idMedicionPA;
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

    public double getPulso() {
        return pulso;
    }

    public void setPulso(double pulso) {
        this.pulso = pulso;
    }

    public int getIdMeasurePA() {
        return idMeasurePA;
    }

    public void setIdMeasurePA(int idMeasurePA) {
        this.idMeasurePA = idMeasurePA;
    }

    @Override
    public String toString() {
        return "MedicionPA{" + "idMedicionPA=" + idMedicionPA + ", idMeasurePA=" + idMeasurePA + ", presionSistolica=" + presionSistolica + ", presionDiastolica=" + presionDiastolica + ", pulso=" + pulso + '}';
    }

}
