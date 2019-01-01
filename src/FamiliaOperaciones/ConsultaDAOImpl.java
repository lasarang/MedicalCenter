/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FamiliaOperaciones;

import BaseDeDatos.Conexion;
import ClasesAuxiliares.Horario;
import ClasesAuxiliares.Orden;
import ClasesAuxiliares.Producto;
import ClasesAuxiliares.SignosVitales;
import ClasesAuxiliares.Tratamiento;
import Extra.Validate;
import FamiliaAcciones.Accion;
import FamiliaAcciones.AdmiMedicina;
import FamiliaAcciones.MedicionGlucosa;
import FamiliaAcciones.MedicionPA;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class ConsultaDAOImpl implements IConsultaDAO {

    private final Conexion conexion = Conexion.getInstancia();
    private final Connection connection = conexion.getConnection();
    private final Validate validate = new Validate();
    private CallableStatement cs, cs2, cs3;
    private PreparedStatement ps;
    private ResultSet rs, rs2, rs3;
    private ArrayList<ConsultaMedica> consultasObtenidas;
    private ConsultaMedica cm;
    private SignosVitales signos;
    private Tratamiento tm;
    private Orden ord;

    @Override
    public void create(ConsultaMedica consulta) throws Exception {//Si no funciona, considerar no utilizar el mismo cs
        Tratamiento tratamiento = consulta.getTratamiento();
        Orden orden = consulta.getOrden();
        //Documento rx = tratamiento.getDocReceta();
        System.out.println("dentro de createConsulta");
        conexion.conectar();
        System.out.println();
        //Consulta: Operacion
        cs = connection.prepareCall("{CALL createOperacion(?, ?, ?, ?)}");
        cs.setInt(1, consulta.getIdPersona1());
        cs.setInt(2, consulta.getIdPersona2());
        cs.setObject(3, consulta.getFechaHoraInicio());
        cs.setString(4, consulta.getTipo());
        cs.executeQuery();
        cs.close();

        //Consulta: ConsultaMedica
        cs = connection.prepareCall("{CALL createConsulta(?, ?, ?, ?, ?, ?, ?, ?)}");
        cs.setInt(1, consulta.getIdOperacion());
        cs.setObject(2, consulta.getFechaHoraFin());
        cs.setString(3, consulta.getMotivos());
        cs.setString(4, consulta.getExamenFisico());
        cs.setString(5, consulta.getProcedimiento());
        cs.setBoolean(6, consulta.isEmergency());
        cs.setString(7, consulta.getAcompañante());
        cs.setString(8, consulta.getRelacion());
        cs.executeQuery();
        cs.close();

        //Consulta: SignosVitales
        signos = consulta.getSignosVitales();
        cs = connection.prepareCall("{CALL createSignosVitales(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        cs.setInt(1, consulta.getIdConsulta());
        cs.setDouble(2, signos.getPulso());
        cs.setDouble(3, signos.getFrecuenciaRespiratoria());
        cs.setDouble(4, signos.getPresionSitolica());
        cs.setDouble(5, signos.getPresionDiastolica());
        cs.setDouble(6, signos.getSaturacionOxigeno());
        cs.setDouble(7, signos.getTemperatura());
        cs.setDouble(8, signos.getTalla());
        cs.setDouble(9, signos.getPeso());

        cs.executeQuery();
        cs.close();

        //Consulta: Diagnosticos
        //Antecedentes personales
        for (String[] personales : consulta.getDiagnosticos().get("Personales")) {
            cs = connection.prepareCall("{CALL createDiagnostico(?, ?, ?, ?)}");
            cs.setInt(1, consulta.getIdConsulta());
            cs.setString(4, "Personal");//TipoAntecedente
            cs.setString(2, personales[0]);//Diagnostico
            cs.setString(3, personales[1]);//CIE10
            cs.executeQuery();
            cs.close();
        }
        //Antecedentes familiares
        for (String[] familiares : consulta.getDiagnosticos().get("Familiares")) {
            cs = connection.prepareCall("{CALL createDiagnostico(?, ?, ?, ?)}");
            cs.setInt(1, consulta.getIdConsulta());
            cs.setString(4, "Familiar");//TipoAntecedente
            cs.setString(2, familiares[0]);//Diagnostico
            cs.setString(3, familiares[1]);//CIE10
            cs.executeQuery();
            cs.close();
        }
        //Consulta: citas medicas
        for (String[] proxima : consulta.getProximasConsultas()) {
            cs = connection.prepareCall("{CALL createCita(?, ?, ?)}");
            cs.setInt(1, consulta.getIdConsulta());
            cs.setObject(2, LocalDate.parse(proxima[0]));//fecha
            cs.setString(3, proxima[1]);//descripcion
            cs.executeQuery();
            cs.close();
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////
        createOrden(orden, consulta.getIdConsulta());

        createTratamiento(tratamiento, consulta.getIdConsulta(), consulta.getIdPersona2());

        createHorarios(tratamiento);

        ///////////////////////////////////////////////////////////////////////////////////////////////////
        conexion.desconectar();

        System.out.println("Creación exitosa de la consulta!");

    }

    private void createTratamiento(Tratamiento t, int idConsulta, int nroHistoria) throws Exception {
        //Consulta: Tratamiento
        cs = connection.prepareCall("{CALL createTratamiento(?, ?, ?, ?, ?, ?)}");
        cs.setInt(1, idConsulta);
        cs.setInt(2, nroHistoria);
        cs.setObject(3, t.getFechaInicio());
        cs.setObject(4, t.getFechaFin());
        cs.setString(5, t.getMedicacion());
        cs.setString(6, t.getIndicaciones());
        cs.executeQuery();
        cs.close();
    }

    private void createOrden(Orden orden, int idConsulta) throws Exception {
        //Consulta: Orden
        String query = "SELECT idParametro FROM Parametros WHERE ?=Parametro";

        //Orden
        cs = connection.prepareCall("{CALL createOrden(?, ?, ?)}");
        cs.setInt(1, idConsulta);
        cs.setObject(2, orden.getFechaHoraAsistencia());
        cs.setString(3, orden.getDescripcion());
        cs.executeQuery();
        cs.close();

        int idParametro;
        for (String parametro : orden.getParametros()) {//Obteniendo el idParametro apartir del parametro

            ps = connection.prepareStatement(query);
            ps.setString(1, parametro);
            rs = ps.executeQuery();
            rs.next();
            idParametro = rs.getInt("idParametro");
            ps.close();

            //Orden: OrdenParametro
            cs2 = connection.prepareCall("{CALL createOrdenParametro(?, ?)}");
            cs2.setInt(1, orden.getIdOrden());
            cs2.setInt(2, idParametro);
            cs2.executeQuery();
            cs2.close();

        }

    }

    private ArrayList<Integer> readHorariosTratamiento(int idTratamiento) throws Exception {
        ArrayList<Integer> horarios = new ArrayList<>();
        ps = connection.prepareStatement("SELECT idHorario FROM Horarios WHERE ?=idSchedule");
        ps.setInt(1, idTratamiento);
        rs = ps.executeQuery();
        while (rs.next()) {
            horarios.add(rs.getInt("idHorario"));
        }

        ps.close();
        return horarios;
    }

    private void createHorarios(Tratamiento tratamiento) throws Exception {
        for (LocalDate fecha : tratamiento.getFechas()) {

            //Horarios
            for (Horario horario : tratamiento.getHorarios()) {
                LocalTime hora = horario.getHora();
                String condicionComida = horario.getCondicionComida();

                cs = connection.prepareCall("{CALL createHorario(?, ?, ?)}");
                cs.setInt(1, tratamiento.getIdTratamiento());//idTratamiento
                cs.setObject(2, hora);
                cs.setString(3, condicionComida);

                cs.executeQuery();
                cs.close();
                int idAgenda = Integer.parseInt(validate.ultimoId("Horarios"));
                System.out.println("Horario #: " + idAgenda);

                int c = 1;
                for (Accion accion : horario.getAcciones()) {
                    System.out.println("Accion nro: " + c);
                    cs2 = connection.prepareCall("{CALL createAccion(?)}");
                    cs2.setString(1, accion.getTipo());//Accion
                    cs2.executeQuery();
                    cs2.close();

                    int idAccion = Integer.parseInt(validate.ultimoId("Acciones"));

                    //Accion: AdmiMedicina
                    if (accion instanceof AdmiMedicina) {
                        AdmiMedicina am = (AdmiMedicina) accion;
                        System.out.println("dentro de admimedicina");

                        cs2 = connection.prepareCall("{CALL createAdmiMedicina(?)}");
                        cs2.setInt(1, idAccion);//idAdmiMedicine               
                        cs2.executeQuery();
                        cs2.close();

                        int idMedicionGlucosa = Integer.parseInt(validate.ultimoId("MedicionesGlucosa"));

                        //AdmiMedicina: TomaMedicina
                        for (Producto medicamento : am.getMedicamentos()) {

                            cs3 = connection.prepareCall("{CALL createTomaMedicina(?, ?, ?)}");
                            cs3.setInt(1, idMedicionGlucosa);//idTake
                            cs3.setInt(2, medicamento.getIdProducto());//idMedicamento 
                            cs3.setInt(3, medicamento.getCantidad());//cantidad
                            cs3.executeQuery();
                            cs3.close();

                        }

                        //AdmiMedicina: HorarioAccion
                        cs2 = connection.prepareCall("{CALL createHorarioAccion(?, ?, ?, ?)}");
                        cs2.setInt(1, idAgenda);//idAgenda
                        cs2.setInt(2, idAccion);//idActivity
                        cs2.setObject(3, fecha);//Fecha 
                        cs2.setObject(4, null);//HoraEjecucion no realizada
                        cs2.executeQuery();
                        cs2.close();

                        //Accion: MedicionPA
                    } else if (accion instanceof MedicionPA) {
                        MedicionPA mpa = (MedicionPA) accion;

                        cs2 = connection.prepareCall("{CALL createAccion(?)}");
                        cs2.setString(1, "MedicionPA");//Accion
                        cs2.executeQuery();
                        cs2.close();

                        cs2 = connection.prepareCall("{CALL createMedicionPA(?, ?, ?, ?)}");
                        cs2.setInt(1, idAccion);//idMeasurePA
                        cs2.setObject(2, null);//PresionSistolica
                        cs2.setObject(3, null);//PresionDiastolica
                        cs2.setObject(4, null);//Pulso
                        cs2.executeQuery();
                        cs2.close();

                        //MedicionPA: HorarioAccion
                        cs2 = connection.prepareCall("{CALL createHorarioAccion(?, ?, ?, ?)}");
                        cs2.setInt(1, idAgenda);//idAgenda
                        cs2.setInt(2, idAccion);//idActivity
                        cs2.setObject(3, fecha);//Fecha 
                        cs2.setObject(4, null);//HoraEjecucion no realizada
                        cs2.executeQuery();
                        cs2.close();

                        //Accion: MedicionGlucosa
                    } else if (accion instanceof MedicionGlucosa) {

                        MedicionGlucosa mg = (MedicionGlucosa) accion;

                        cs2 = connection.prepareCall("{CALL createAccion(?)}");
                        cs2.setString(1, "MedicionGlucosa");//Accion
                        cs2.executeQuery();
                        cs2.close();

                        cs2 = connection.prepareCall("{CALL createMedicionGlucosa(?, ?)}");
                        cs2.setInt(1, idAccion);//idMeasureGlucose 
                        cs2.setObject(2, null);//Glucosa
                        cs2.executeQuery();
                        cs2.close();

                        //MedicionGlucosa:HorarioAccion
                        cs2 = connection.prepareCall("{CALL createHorarioAccion(?, ?, ?, ?)}");
                        cs2.setInt(1, idAgenda);//idAgenda
                        cs2.setInt(2, idAccion);//idActivity
                        cs2.setObject(3, fecha);//Fecha 
                        cs2.setObject(4, null);//HoraEjecucion no realizada
                        cs2.executeQuery();
                        cs2.close();

                    }
                    c++;
                }

            }

        }

    }

    @Override
    public ArrayList<ConsultaMedica> readAll() throws Exception {
        cm = new ConsultaMedica();
        ord = new Orden();
        tm = new Tratamiento();
        consultasObtenidas = new ArrayList<>();

        //Consultas
        conexion.conectar();
        cs = connection.prepareCall("{CALL readAllConsultas()}");
        rs = cs.executeQuery();

        while (rs.next()) {
            int idConsulta = rs.getInt("idConsulta"),
                    idPersona1 = rs.getInt("idPersona1"),
                    idPersona2 = rs.getInt("idPersona2");
            String fechaHoraInicio = String.valueOf(rs.getObject("FechaHoraInicio")),
                    fechaHoraFin = String.valueOf(rs.getObject("FechaHoraFin")),
                    motivos = rs.getString("Motivos"),
                    examenFisico = rs.getString("ExamenFisico"),
                    procedimiento = rs.getString("Procedimiento"),
                    acompañante = rs.getString("Acompañante"),
                    relacion = rs.getString("Relacion");
            boolean emergencia = rs.getBoolean("esEmergencia");

            cm.setIdConsulta(idConsulta);
            cm.setIdPersona1(idPersona1);
            cm.setIdPersona2(idPersona2);
            // cm.setFechaHoraInicio(LocalTime.parse(fechaHoraInicio));
            // cm.setFechaHoraFin(LocalTime.parse(fechaHoraFin));
            cm.setMotivos(motivos);
            cm.setExamenFisico(examenFisico);
            cm.setProcedimiento(procedimiento);
            cm.setEmergency(emergencia);
            cm.setAcompañante(acompañante);
            cm.setRelacion(relacion);

            //Consulta: Antecedentes o Diagnosticos
            conexion.conectar();
            cs2 = connection.prepareCall("{CALL readConsultaDiagnosticos(?)}");
            cs2.setInt(1, idConsulta);
            rs2 = cs2.executeQuery();

            while (rs2.next()) {
                String tipoAntecedente = rs2.getString("TipoAntecedente"),
                        diagnostico = rs2.getString("Diagnostico"),
                        cie10 = rs2.getString("CIE10");

                String[] antecedente = {diagnostico, cie10};
                if (tipoAntecedente.equals("Personal")) {
                    cm.getDiagnosticos().get("Personales").add(antecedente);

                } else if (tipoAntecedente.equals("Familiar")) {
                    cm.getDiagnosticos().get("Familiares").add(antecedente);

                }

            }
            cs2.close();

            //Consulta: Signos Vitales
            conexion.conectar();
            cs2 = connection.prepareCall("{CALL readConsultaSignosVitales(?)}");
            cs2.setInt(1, idConsulta);
            rs2 = cs2.executeQuery();
            rs2.next();
            signos = cm.getSignosVitales();
            signos.setPulso(rs2.getDouble("Pulso"));
            signos.setFrecuenciaRespiratoria(rs2.getDouble("FrecuenciaRespiratoria"));
            signos.setPresionSitolica(rs2.getDouble("PresionSistolica"));
            signos.setPresionDiastolica(rs2.getDouble("PresionDiastolica"));
            signos.setSaturacionOxigeno(rs2.getDouble("SaturacionOxigeno"));
            signos.setTemperatura(rs2.getDouble("Temperatura"));
            signos.setTalla(rs2.getDouble("Talla"));
            signos.setPeso(rs2.getDouble("Peso"));
            cs2.close();

            //Consulta: Proximas Citas
            conexion.conectar();
            cs2 = connection.prepareCall("{CALL readConsultaCitas(?)}");
            cs2.setInt(1, idConsulta);
            rs2 = cs2.executeQuery();

            while (rs2.next()) {
                String fecha = String.valueOf(rs2.getObject("Fecha")),
                        descripcion = rs2.getString("Descripcion");

                String[] proxima = {fecha, descripcion};
                cm.getProximasConsultas().add(proxima);
            }
            cs2.close();

            //Consulta: Orden
            conexion.conectar();
            cs2 = connection.prepareCall("{CALL readConsultaOrden(?)}");
            cs2.setInt(1, idConsulta);
            rs2 = cs2.executeQuery();

            rs2.next();
            int idOrden = rs2.getInt("idOrden");
            String fechaHora = String.valueOf(rs2.getObject("FechaHora")),
                    descripcion = rs2.getString("Descripcion");

            ord.setIdOrden(idOrden);
            ord.setFechaHoraAsistencia(LocalDateTime.parse(fechaHora));
            ord.setDescripcion(descripcion);

            cs2.close();

            //Considerar los parametros de los examnenes
            ArrayList<String> parametros = ord.getParametros();

            conexion.conectar();
            cs2 = connection.prepareCall("{CALL readOrdenParametros(?)}");
            cs2.setInt(1, idOrden);
            rs2 = cs2.executeQuery();

            while (rs2.next()) {
                String parametro = rs2.getString("Parametro");
                parametros.add(parametro);

            }
            cs2.close();

            //Tratamiento
            conexion.conectar();
            cs2 = connection.prepareCall("{CALL readConsultaTratamiento(?)}");
            cs2.setInt(1, idConsulta);
            rs2 = cs2.executeQuery();

            rs2.next();
            int idTratamiento = rs2.getInt("idTratamiento");
            String fechaInicio = String.valueOf(rs2.getObject("FechaInicio")),
                    fechaFin = String.valueOf(rs2.getObject("FechaFin")),
                    medicacion = rs2.getString("Medicacion"),
                    indicaciones = rs2.getString("Indicaciones");
            tm.setIdTratamiento(idTratamiento);
            tm.setFechaInicio(LocalDate.parse(fechaInicio));
            tm.setFechaFin(LocalDate.parse(fechaFin));
            tm.setMedicacion(medicacion);
            tm.setIndicaciones(indicaciones);
            cs2.close();

            //Tratamiento: Horario
            conexion.conectar();
            cs2 = connection.prepareCall("{CALL readTratamientoHorariosAcciones(?)}");
            cs2.setInt(1, idTratamiento);
            rs2 = cs2.executeQuery();

            while (rs2.next()) {
                String HoraHorario = String.valueOf(rs2.getObject("FechaHora")),
                        HoraEjecucion = String.valueOf(rs2.getObject("FechaHoraEjeucion"));
                int idActivity = rs2.getInt("idActivity");
                Horario horario = new Horario();
                horario.setHora(LocalTime.parse(HoraHorario));

                //  ArrayList<Accion> horariosAcciones = horario.getHorariosAcciones();
                //Horario: AdmiMedicinas
                conexion.conectar();
                cs3 = connection.prepareCall("{CALL readAdmiMedicinaTomasMedicinasProductos(?)}");
                cs3.setInt(1, idActivity);
                rs3 = cs3.executeQuery();
                while (rs3.next()) {

                    String condicionComida = rs3.getString("CondicionComida"),
                            nombreComercial = rs3.getString("NombreComercial"),
                            presentacion = rs3.getString("Presentacion"),
                            laboratorio = rs3.getString("Laboratorio");
                    int cantidad = rs3.getInt("Cantidad");

                    if (nombreComercial != null) {//Validando que exista un AdmiMedicina en ese horario
                        AdmiMedicina am = new AdmiMedicina();
                        Producto p = new Producto();
                        p.setNombreComercial(nombreComercial);
                        p.setPresentacion(presentacion);
                        p.setLaboratorio(laboratorio);
                        p.setCantidad(cantidad);

                        //  am.setHoraEjecucion(LocalTime.parse(HoraEjecucion));
                        //  am.setCondicionComida(condicionComida);
                        am.getMedicamentos().add(p);
                        horario.setCondicionComida(condicionComida);

                        //  horariosAcciones.add(am);
                    } else {
                        break;
                    }

                }
                cs3.close();

                //Horario: MedicionGlucosa
                conexion.conectar();
                cs3 = connection.prepareCall("{CALL readHorarioAccionMedicionGlucosa(?)}");
                cs3.setInt(1, idActivity);
                rs3 = cs3.executeQuery();
                while (rs3.next()) {

                    String condicionComida = rs3.getString("CondicionComida");

                    double glucosa = rs3.getDouble("Glucosa");

                    if (condicionComida != null) {//Validando que exista una MedicionGlucosa en ese horario
                        MedicionGlucosa mg = new MedicionGlucosa();

                        //  mg.setHoraEjecucion(LocalTime.parse(HoraEjecucion));//puede ser nula sino se ha realizado
                        // mg.setCondicionComida(condicionComida);
                        mg.setGlucosa(glucosa);//puede ser nula sino se ha realizado
                        horario.setCondicionComida(condicionComida);

                        // horariosAcciones.add(mg);
                    } else {
                        break;
                    }

                }
                cs3.close();

                //Horario: MedicionPA
                conexion.conectar();
                cs3 = connection.prepareCall("{CALL readHorarioAccionMedicionPA(?)}");
                cs3.setInt(1, idActivity);
                rs3 = cs3.executeQuery();
                while (rs3.next()) {

                    String condicionComida = rs3.getString("CondicionComida");
                    double presionSistolica = rs3.getDouble("PresionSistolica"),
                            presionDiastolica = rs3.getDouble("PersionDiastolica"),
                            pulso = rs3.getDouble("Pulso");

                    if (condicionComida != null) {//Validando que exista una MedicionPA en ese horario
                        MedicionPA mpa = new MedicionPA();

                        // mpa.setHoraEjecucion(LocalTime.parse(HoraEjecucion));//puede ser nula sino se ha realizado
                        // mpa.setCondicionComida(condicionComida);
                        mpa.setPresionSistolica(presionSistolica);//puede ser nula sino se ha realizado
                        mpa.setPresionDiastolica(presionDiastolica);//puede ser nula sino se ha realizado
                        mpa.setPulso(pulso);//puede ser nula sino se ha realizado
                        horario.setCondicionComida(condicionComida);
                        // horariosAcciones.add(mpa);
                    } else {
                        break;
                    }

                }
                cs3.close();
                //tm.getActividades().add(horario);

            }
            cs2.close();

            cm.setTratamiento(tm);
            consultasObtenidas.add(cm);
        }
        cs.close();

        conexion.desconectar();
        return consultasObtenidas;
    }

    @Override
    public ArrayList<ConsultaMedica> readOne(String cedulaNombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
