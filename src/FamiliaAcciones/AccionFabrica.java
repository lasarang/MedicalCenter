/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FamiliaAcciones;

import ClasesAuxiliares.FabricaAbstracta;
import FamiliaOperaciones.Operacion;
import FamiliaPersonas.Persona;



/**
 * 
 * @author Luis A. Sarango-Parrales
 */
public class AccionFabrica implements FabricaAbstracta {
    @Override
    public Accion getAccion(String tipo){
        if (tipo.equalsIgnoreCase("AdmiMedicina"))
            return new AdmiMedicina();
        else if (tipo.equalsIgnoreCase("MedicionGlucosa"))
            return new MedicionGlucosa();
        else if (tipo.equalsIgnoreCase("MedicionPA"))
            return new MedicionPA();
        else if(tipo == null){
            return null;
        }	
        return null;   
    }

    @Override
    public Operacion getOperacion(String tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Persona getPersona(String tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
