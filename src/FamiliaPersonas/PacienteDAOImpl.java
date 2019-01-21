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
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class PacienteDAOImpl implements IPacienteDAO {

    private final Conexion conexion = Conexion.getInstancia();
    private final Connection connection = conexion.getConnection();
    private final Validate validate = new Validate();
    private CallableStatement cs;
    private ResultSet rs;
    private Paciente paciente;
    private ArrayList<String> telefonos, correos, ocupaciones;
    private ArrayList<String[]> domicilios;

    @Override
    public void create(Paciente paciente) throws Exception {

       // conexion.conectar();
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

        //conexion.desconectar();
        System.out.println("Creación de paciente exitoso!");

    }

    @Override
    public Paciente read(String cedulaNombre) throws Exception {
        paciente = new Paciente();
        String idPersona = "";
        if (!Validate.isNumeric(cedulaNombre)) {
            idPersona = validate.obtenerIdPersonaNombre(cedulaNombre);
        } else {
            idPersona = cedulaNombre;
        }

        cs = connection.prepareCall("{CALL readPersona(?)}");
        cs.setString(1, idPersona);
        rs = cs.executeQuery();
        rs.next();
        int nroHistoria=rs.getInt("nroHistoria");
        String nombre = rs.getString("Nombre"),
                genero = rs.getString("Genero"),
                estadoCivil = rs.getString("EstadoCivil"),
                grupoSanguineo = rs.getString("GrupoSanguineo");
        Date fechaNacimiento=(Date)rs.getObject("FechaNacimiento");
        
        paciente.setIdPersona(idPersona);
        paciente.setNroHistoria(nroHistoria);
        paciente.setIdPatient(idPersona);
        paciente.setNombre(nombre);
        paciente.setFechaNacimiento(fechaNacimiento.toLocalDate());
        paciente.setTipo("Paciente");
        paciente.setGenero(genero);
        paciente.setEstadoCivil(estadoCivil);
        paciente.setGrupoSanguineo(grupoSanguineo);
        paciente.setTelefonos(readTelefonos(idPersona));
        paciente.setCorreos(readCorreos(idPersona));
        paciente.setDomicilios(readDomicilios(idPersona));
        paciente.setCorreos(readCorreos(idPersona));
        paciente.setOcupaciones(readPacienteOcupaciones(idPersona));
        

        cs.close();

        //conexion.desconectar();

        return paciente;
    }

    private ArrayList<String> readTelefonos(String idPersona) throws Exception {
        telefonos = new ArrayList<>();
        cs = connection.prepareCall("{CALL readPersonaTelefonos(?)}");
        cs.setString(1, idPersona);
        rs = cs.executeQuery();
        while (rs.next()) {
            String telefono = rs.getString("Telefono");
            telefonos.add(telefono);

        }

        return telefonos;
    }

    private ArrayList<String> readCorreos(String idPersona) throws Exception {
        correos = new ArrayList<>();
        cs = connection.prepareCall("{CALL readPersonaCorreos(?)}");
        cs.setString(1, idPersona);
        rs = cs.executeQuery();
        while (rs.next()) {
            String correo = rs.getString("Correo");
            correos.add(correo);
        }

        return correos;
    }

    private ArrayList<String[]> readDomicilios(String idPersona) throws Exception {
        domicilios = new ArrayList<>();
        cs = connection.prepareCall("{CALL readPersonaDomicilios(?)}");
        cs.setString(1, idPersona);
        rs = cs.executeQuery();
        while (rs.next()) {
            String ciudad = rs.getString("Ciudad"),
                    direccion = rs.getString("Direccion");
            String[] domicilio = {ciudad, direccion};

            domicilios.add(domicilio);
        }

        return domicilios;
    }

    private ArrayList<String> readPacienteOcupaciones(String idPersona) throws Exception {
        ocupaciones = new ArrayList<>();
        cs = connection.prepareCall("{CALL readPacienteOcupaciones(?)}");
        cs.setString(1, idPersona);
        rs = cs.executeQuery();
        while (rs.next()) {
            ocupaciones.add(rs.getString("Ocupacion"));
        }
        return ocupaciones;

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
