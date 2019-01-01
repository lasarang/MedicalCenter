/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAuxiliares;

import FamiliaAcciones.Accion;
import FamiliaOperaciones.Operacion;
import FamiliaPersonas.Persona;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public interface FabricaAbstracta {
    public Accion getAccion(String tipo);
    public Operacion getOperacion(String tipo);
    public Persona getPersona(String tipo);
}
