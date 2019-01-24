/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extra;

import BaseDeDatos.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class Validate {

    private final Conexion conexion = Conexion.getInstancia();
    private final Connection connection = conexion.getConnection();

    private PreparedStatement ps;
    private CallableStatement cs;
    private ResultSet rs;
    private HashMap<String, String> tablas = new HashMap<>();

    {
        tablas.put("Personas", "idPersona");
        tablas.put("PersonasTelefonos", "idPersonaTelefono");
        tablas.put("PersonasDomicilios", "idPersonaDomicilio");
        tablas.put("PersonasCorreos", "idPersonaCorreo");
        tablas.put("Pacientes", "nroHistoria");
        tablas.put("PacientesOcupaciones", "idPacienteOcupacion");
        tablas.put("Empleados", "idEmpleado");
        tablas.put("Medicos", "idMedico");
        tablas.put("Operaciones", "idOperacion");
        tablas.put("ConsultasMedicas", "idConsulta");
        tablas.put("Diagnosticos", "idDiagnostico");
        tablas.put("SignosVitales", "idSignosVitales");
        tablas.put("Tratamientos", "idTratamiento");
        tablas.put("Documentos", "idDocumento");
        tablas.put("Ordenes", "idOrden");
        tablas.put("Examenes", "idExamen");
        tablas.put("Parametros", "idParametro");
        tablas.put("OrdenesParametros", "idOrdenParametro");
        tablas.put("Productos", "idProducto");
        tablas.put("Horarios", "idHorario");
        tablas.put("Acciones", "idAccion");
        tablas.put("AdmiMedicinas", "idAdmiMedicina");
        tablas.put("MedicionesPA", "idMedicionPA");
        tablas.put("MedicionesGlucosa", "idMedicionGlucosa");
        tablas.put("TomasMedicinas", "idToma");
        tablas.put("HorariosAcciones", "idHorarioAccion");
        tablas.put("Citas", "idCita");
    }

    public static Boolean isNumeric(String cadena) {
        for (int i = 0; i < cadena.length(); i++) {
            if (!Character.isDigit(cadena.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String ultimoId(String tabla) throws Exception {
        String value = "";

        String query = "SELECT MAX(" + tablas.get(tabla) + ") AS ultimo FROM " + tabla;
        ps = connection.prepareStatement(query);
        rs = ps.executeQuery();
        rs.next();
        value = String.valueOf(rs.getObject("ultimo"));
        ps.close();

        if (value.equals("null")) {
            return "0";
        } else {
            return value;
        }

    }

   public boolean esUsuarioMedico(String cedula, String clave) throws Exception {
        String idMedico = "";

        cs = connection.prepareCall("{CALL verificarMedico(?, ?)}");
        cs.setString(1, cedula);
        cs.setString(2, clave);
        rs = cs.executeQuery();

        if (rs.next()) {
            if (rs.getInt("idEmpleado") >= 1) {
                //System.out.println("dentro del else: " + idMedico);

                return true;
            }
        }
        return false;

    }

    public  int obtenerNroHistoriaCedula(String cedula) throws Exception {

        cs = connection.prepareCall("{CALL obtenerNroHistoriaCedula(?)}");
        cs.setString(1, cedula);
        rs = cs.executeQuery();

        rs.next();
        int nroHistoria = rs.getInt("nroHistoria");
        cs.close();

        if (nroHistoria == 0) {
            throw new Exception("No existe persona con esa cedula");
        } else {
            return nroHistoria;
        }

    }

    public int obtenerNroHistoriaNombre(String nombre) throws Exception {

        cs = connection.prepareCall("{CALL obtenerNroHistoriaNombre(?)}");
        cs.setString(1, nombre);
        rs = cs.executeQuery();

        rs.next();
        int nroHistoria = rs.getInt("nroHistoria");
        cs.close();

        if (nroHistoria == 0) {
            throw new Exception("No existe persona con ese nombre");
        } else {
            return nroHistoria;
        }

    }

    public String obtenerIdPersonaNombre(String nombre) throws Exception {

        cs = connection.prepareCall("{CALL obtenerIdPersonaNombre(?)}");
        cs.setString(1, nombre);
        rs = cs.executeQuery();

        rs.next();
        String idPersona = rs.getString("idPersona");
        cs.close();

        if (idPersona == null) {
            throw new Exception("No existe persona con ese nombre");// manejar con Excepciones
        } else {
            return idPersona;
        }

    }

    public String obtenerIdPersonaNroHistoria(int nroHistoria) throws Exception {

        cs = connection.prepareCall("{CALL obtenerIdPersonaHistoria(?)}");
        cs.setInt(1, nroHistoria);
        rs = cs.executeQuery();

        rs.next();
        String idPersona = rs.getString("idPatient");
        cs.close();

        if (idPersona == null) {
            throw new Exception("No existe persona con ese nroHistoria");// manejar con Excepciones
        } else {
            return idPersona;
        }

    }

    public static String formatearFechaHoraFb(String fechaHora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm");
        String fechaHoraFormateada = String.valueOf((LocalDateTime.parse(fechaHora)).format(formatter));
        return fechaHoraFormateada;
    }

    public static ArrayList<LocalDate> getDates(
            LocalDate startDate, LocalDate endDate) {

        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return (ArrayList<LocalDate>) IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());
    }

    public HashMap<String, String> getTablas() {
        return tablas;
    }

    public void setTablas(HashMap<String, String> tablas) {
        this.tablas = tablas;
    }

}
