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
public class MedicionGlucosa extends Accion {

    private int idMedicionGlucosa, idMeasureGlucose = getIdAccion();
    private double glucosa;

    public MedicionGlucosa(){
        setTipo("MedicionGlucosa");
    }
 
    public int getIdMedicionGlucosa() {
        return idMedicionGlucosa;
    }

    public void setIdMedicionGlucosa(int idMedicionGlucosa) {
        this.idMedicionGlucosa = idMedicionGlucosa;
    }

    public double getGlucosa() {
        return glucosa;
    }

    public void setGlucosa(double glucosa) {
        this.glucosa = glucosa;
    }

    public int getIdMeasureGlucose() {
        return idMeasureGlucose;
    }

    public void setIdMeasureGlucose(int idMeasureGlucose) {
        this.idMeasureGlucose = idMeasureGlucose;
    }

    @Override
    public String toString() {
        return "MedicionGlucosa{" + "idMedicionGlucosa=" + idMedicionGlucosa + ", idMeasureGlucose=" + idMeasureGlucose + ", glucosa=" + glucosa + '}';
    }

}
