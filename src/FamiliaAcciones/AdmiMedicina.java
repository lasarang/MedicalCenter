/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FamiliaAcciones;

import ClasesAuxiliares.Producto;
import java.util.ArrayList;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class AdmiMedicina extends Accion {

    private int idAdmiMedicina, idAdmiMedicine = getIdAccion();
    private ArrayList<Producto> medicamentos;
    
    public AdmiMedicina(){
        setTipo("AdmiMedicina");
    }

    public int getIdAdmiMedicine() {
        return idAdmiMedicine;
    }

    public void setIdAdmiMedicine(int idAdmiMedicine) {
        this.idAdmiMedicine = idAdmiMedicine;
    }

    public int getIdAdmiMedicina() {
        return idAdmiMedicina;
    }

    public void setIdAdmiMedicina(int idAdmiMedicina) {
        this.idAdmiMedicina = idAdmiMedicina;
    }

    public ArrayList<Producto> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(ArrayList<Producto> medicamentos) {
        this.medicamentos = medicamentos;
    }

    @Override
    public String toString() {
        return "AdmiMedicina{" + "idAdmiMedicina=" + idAdmiMedicina + ", idAdmiMedicine=" + idAdmiMedicine + ", medicamentos=" + medicamentos + '}';
    }

}
