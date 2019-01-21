/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAuxiliares;

import javafx.scene.control.CheckBox;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class Producto {

    private int idProducto, cantidad;
    private String nombreComercial, presentacion, laboratorio;
    private double precioUnitarioVenta;
    private CheckBox seleccion;

    public Producto() {
    }

    public Producto(int idProducto, int cantidad, String nombreComercial, String presentacion, String laboratorio, double precioUnitarioVenta, CheckBox seleccion) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.nombreComercial = nombreComercial;
        this.presentacion = presentacion;
        this.laboratorio = laboratorio;
        this.precioUnitarioVenta = precioUnitarioVenta;
        this.seleccion = seleccion;
    }

    public CheckBox getSeleccion() {
        return seleccion;
    }

    public Producto(int idProducto, int cantidad, String nombreComercial, String presentacion, String laboratorio, double precioUnitarioVenta) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.nombreComercial = nombreComercial;
        this.presentacion = presentacion;
        this.laboratorio = laboratorio;
        this.precioUnitarioVenta = precioUnitarioVenta;
    }

    public void setSeleccion(CheckBox seleccion) {
        this.seleccion = seleccion;
    }

   
    
    
    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitarioVenta() {
        return precioUnitarioVenta;
    }

    public void setPrecioUnitarioVenta(double precioUnitarioVenta) {
        this.precioUnitarioVenta = precioUnitarioVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombreComercial=" + nombreComercial + ", presentacion=" + presentacion + ", laboratorio=" + laboratorio + ", cantidad=" + cantidad + ", precioUnitarioVenta=" + precioUnitarioVenta + '}';
    }

}
