/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAuxiliares;

import BaseDeDatos.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class ProductoDAOImpl implements IProductoDAO {

    private final Conexion conexion = Conexion.getInstancia();
    private final Connection connection = conexion.getConnection();
    private ArrayList<Producto> medicamentos;
    private Producto medicamento;
    private CallableStatement cs;
    private ResultSet rs;

    @Override
    public ArrayList<Producto> readNombre(String nombre) throws Exception {
        medicamentos = new ArrayList<>();
       
        //conexion.conectar();
        cs = connection.prepareCall("{CALL readProductoNombre(?)}");
        cs.setString(1, nombre);
        rs = cs.executeQuery();
        
        while (rs.next()) {
             medicamento=new Producto();
            String nombreComercial = rs.getString("NombreComercial"),
                    presentacion = rs.getString("Presentacion"),
                    lab = rs.getString("Laboratorio");
            int idProducto=rs.getInt("idProducto"),
                cantidadDisponible = rs.getInt("CantidadDisponible");
            double precioUnitario = rs.getDouble("PrecioUnitarioVenta");
            medicamento.setIdProducto(idProducto);
            medicamento.setNombreComercial(nombreComercial);
            medicamento.setCantidad(cantidadDisponible);
            medicamento.setLaboratorio(lab);
            medicamento.setPrecioUnitarioVenta(precioUnitario);
            medicamento.setPresentacion(presentacion);
           
            medicamentos.add(medicamento);
            
        }
        cs.close();
        //conexion.desconectar();
        return medicamentos;
    }

    @Override
    public ArrayList<Producto> readNombreLab(String nombre, String laboratorio) throws Exception {
        medicamentos = new ArrayList<>();
        
        //conexion.conectar();
        cs = connection.prepareCall("{CALL readProductoNombreLab(?, ?)}");
        cs.setString(1, nombre);
        cs.setString(2, laboratorio);
        rs = cs.executeQuery();

        while (rs.next()) {
            medicamento=new Producto();
            String nombreComercial = rs.getString("NombreComercial"),
                    presentacion = rs.getString("Presentacion"),
                    lab = rs.getString("Laboratorio");
            int idProducto=rs.getInt("idProducto"),
                cantidadDisponible = rs.getInt("CantidadDisponible");
            double precioUnitario = rs.getDouble("PrecioUnitarioVenta");
            medicamento.setIdProducto(idProducto);
            medicamento.setNombreComercial(nombreComercial);
            medicamento.setCantidad(cantidadDisponible);
            medicamento.setLaboratorio(lab);
            medicamento.setPrecioUnitarioVenta(precioUnitario);
            medicamento.setPresentacion(presentacion);

            medicamentos.add(medicamento);
        }

        cs.close();
       // conexion.desconectar();

        return medicamentos;

    }

}
