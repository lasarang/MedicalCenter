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
public interface IPacienteDAO {
    public void create(Paciente paciente) throws Exception;
    public Paciente read(String cedulaNombre) throws Exception;
    public void update(String cedulaNombre, ArrayList<String[]> nuevosValores) throws Exception;
    public void delete(String cedulaNombre) throws Exception;
}
