/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FamiliaPersonas;

import ClasesAuxiliares.FabricaAbstracta;
import FamiliaAcciones.Accion;
import FamiliaOperaciones.Operacion;

/**
 * 
 * @author Luis A. Sarango-Parrales
 */
public class PersonaFabrica implements FabricaAbstracta{
    
    @Override
    public Persona getPersona(String tipo){
        if (tipo.equalsIgnoreCase("Paciente"))
            return new Paciente();
        else if (tipo.equalsIgnoreCase("Medico"))
            return new Medico();
        else if(tipo == null){
            return null;
        }	
        return null;   
    }

    @Override
    public Accion getAccion(String tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Operacion getOperacion(String tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
