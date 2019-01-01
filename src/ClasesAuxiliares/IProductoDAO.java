/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAuxiliares;

import java.util.ArrayList;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public interface IProductoDAO {
    public ArrayList<Producto> readNombre(String nombre) throws Exception;
    public ArrayList<Producto> readNombreLab(String nombre, String laboratorio) throws Exception;
    
}
