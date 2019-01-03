/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FamiliaOperaciones;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public interface IConsultaDAO {
    
    public void create(ConsultaMedica consulta) throws Exception;
    public ArrayList<ConsultaMedica>  readAllConsultasPaciente(String cedulaNombre) throws Exception;
    public ArrayList<ConsultaMedica> readAllConsultasFecha(LocalDate fecha) throws Exception;
    
}
