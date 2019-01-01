/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Luis A. Sarango-Parrales
 */
public class Conexion {
    private static Conexion instancia;
    private Connection connection;
    private final String URL="jdbc:mysql://localhost:3306/MedicalCenterNarcisaDeJesus",
                         USER="root",
                         PWD="Programming0";

    private Conexion(){   
    }
    
    public static Conexion getInstancia(){
        if(instancia==null)
            instancia=new Conexion();
        return instancia;
    }

    public void conectar() throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Conexión DB exitosa");
        }catch(Exception e){
            throw e; 
        }
    }
    
    public void desconectar() throws SQLException{
        if (connection!=null)
            if(!connection.isClosed())
                connection.close();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    

}
