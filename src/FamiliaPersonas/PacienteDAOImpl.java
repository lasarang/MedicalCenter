/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FamiliaPersonas;

import BaseDeDatos.Conexion;
import Extra.Validate;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class PacienteDAOImpl implements IPacienteDAO {

    private final Conexion conexion = Conexion.getInstancia();
    private final Connection connection = conexion.getConnection();
    private CallableStatement cs;
    private ResultSet rs;
    private HashMap<Paciente, Object> mapaPaciente;

    @Override
    public void create(Paciente paciente) throws Exception {

        conexion.conectar();
        cs = connection.prepareCall("{CALL createPersona(?, ?, ?)}");
        cs.setString(1, paciente.getIdPersona());
        cs.setString(2, paciente.getNombre());
        cs.setString(3, "Paciente");
        cs.executeQuery();
        cs.close();

        for (String telefono : paciente.getTelefonos()) {
            cs = connection.prepareCall("{CALL createPersonaTelefono(?, ?)}");
            cs.setString(1, paciente.getIdPersona());
            cs.setString(2, telefono);
            cs.executeQuery();
            cs.close();
        }

        for (String[] domicilio : paciente.getDomicilios()) {
            String[] casa = domicilio;
            cs = connection.prepareCall("{CALL createPersonaDomicilio(?, ?, ?)}");
            cs.setString(1, paciente.getIdPersona());
            cs.setString(2, casa[0]);
            cs.setString(3, casa[1]);
            cs.executeQuery();
            cs.close();
        }

        for (String correo : paciente.getCorreos()) {
            cs = connection.prepareCall("{CALL createPersonaCorreo(?, ?)}");
            cs.setString(1, paciente.getIdPersona());
            cs.setString(2, correo);
            cs.executeQuery();
            cs.close();
        }

        cs = connection.prepareCall("{CALL createPaciente(?, ?, ?, ?, ?)}");
        cs.setString(1, paciente.getIdPersona());
        cs.setObject(2, paciente.getFechaNacimiento());
        cs.setString(3, paciente.getGenero());
        cs.setString(4, paciente.getEstadoCivil());
        cs.setString(5, paciente.getGrupoSanguineo());
        cs.executeQuery();
        cs.close();

        for (String ocupacion : paciente.getOcupaciones()) {
            cs = connection.prepareCall("{CALL createPacienteOcupacion(?, ?)}");
            cs.setInt(1, paciente.getNroHistoria());
            cs.setString(2, ocupacion);
            cs.executeQuery();
            cs.close();
        }
        
        conexion.desconectar();
        System.out.println("Creación de paciente exitoso!");

    }

    @Override
    public HashMap<Paciente, Object> read(String cedulaNombre) throws Exception {
        mapaPaciente = new HashMap<>();
        if (Validate.isNumeric(cedulaNombre)) {

            conexion.conectar();
            cs = connection.prepareCall("{CALL readPacienteCedula_OrdenesParametros(?)}");
            cs.setString(1, cedulaNombre);
            rs = cs.executeQuery();
            while (rs.next()) {
                int idOrden = rs.getInt("idOrden");
                String fechaHora = String.valueOf(rs.getDate("FechaHora"));
                String descripcion = rs.getString("Descripcion");

                HashMap<String, Object> mapaInterno1 = new HashMap<>();
                HashMap<String, ArrayList<String>> mapaInterno2 = new HashMap<>();

                mapaInterno1.put("FechaHora", fechaHora);
                mapaInterno1.put("Descripcion", descripcion);
                mapaInterno1.put("Examenes", mapaInterno2);

                //mapaPaciente.put(idOrden,mapaInterno1);
            }
            cs.close();

            conexion.desconectar();

        }
        return null;
    }

    @Override
    public void update(String cedulaNombre, ArrayList<String[]> nuevosValores) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String cedulaNombre) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
