/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClasesAuxiliares;

import FamiliaAcciones.AccionFabrica;
import FamiliaOperaciones.OperacionFabrica;
import FamiliaPersonas.PersonaFabrica;

/**
 * 
 * @author Luis A. Sarango-Parrales
 */
public class FabricaProductor {
    public static FabricaAbstracta getFabrica(String tipoFabrica){
     	
      if(tipoFabrica.equalsIgnoreCase("PERSONA FABRICA")){
         return new PersonaFabrica();
      } else if(tipoFabrica.equalsIgnoreCase("OPERACION FABRICA")){
         return new OperacionFabrica();
      } else if(tipoFabrica.equalsIgnoreCase("ACCION FABRICA")){
         return new AccionFabrica();
      } else if(tipoFabrica == null){
         return null;
      }	
      return null;
  
        
    }


}
