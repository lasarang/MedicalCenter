/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FamiliaOperaciones;

import ClasesAuxiliares.FabricaAbstracta;
import FamiliaAcciones.Accion;
import FamiliaPersonas.Persona;

/**
 * 
 * @author Luis A. Sarango-Parrales
 */
public class OperacionFabrica implements FabricaAbstracta{
    @Override
    public Operacion getOperacion(String tipo){
        if(tipo.equalsIgnoreCase("Consulta Medica"))
            return new ConsultaMedica();
        return null;
    }

    @Override
    public Accion getAccion(String tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Persona getPersona(String tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
