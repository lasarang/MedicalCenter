/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FamiliaOperaciones;

import BaseDeDatos.Conexion;
import ClasesAuxiliares.Horario;
import ClasesAuxiliares.HorarioAccion;
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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class ConsultaDAOImpl implements IConsultaDAO {

    private final Conexion conexion = Conexion.getInstancia();
    private final Connection connection = conexion.getConnection();
    private final Validate validate = new Validate();
    private CallableStatement cs, cs2, cs3, cs4, cs5, cs6;
    private PreparedStatement ps;
    private ResultSet rs, rs2, rs3, rs4, rs5, rs6;
    private Horario horario;
    private HorarioAccion horarioAccion;
    private Accion accion;
    private MedicionGlucosa mg;
    private MedicionPA mpa;
    private AdmiMedicina am;
    private Producto medicamento;
    private ArrayList<String> parametros;
    private ArrayList<Horario> horarios;
    private ArrayList<HorarioAccion> horariosAcciones;
    private ArrayList<Producto> medicamentos;
    private ArrayList<ConsultaMedica> consultasObtenidas;
    private ArrayList<String[]> proximasConsultas;
    private HashMap<String, ArrayList<String[]>> diagnosticos;
    private ConsultaMedica cm;
    private SignosVitales signos;
    private Tratamiento tm, tm2;
    private Orden ord;

    @Override
    public void create(ConsultaMedica consulta) throws Exception {//Si no funciona, considerar no utilizar el mismo cs
        Tratamiento tratamiento = consulta.getTratamiento();
        Orden orden = consulta.getOrden();

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
        cs.setDouble(4, signos.getPresionSistolica());
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

    private void createHorarios(Tratamiento tratamiento) throws Exception {

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
            for (LocalDate fecha : tratamiento.getFechas()) {
                for (Accion accion : horario.getAcciones()) {
                    // System.out.println("Accion nro: " + c);
                    cs2 = connection.prepareCall("{CALL createAccion(?)}");
                    cs2.setString(1, accion.getTipo());//Accion
                    cs2.executeQuery();
                    cs2.close();

                    int idAccion = Integer.parseInt(validate.ultimoId("Acciones"));

                    //Accion: AdmiMedicina
                    if (accion instanceof AdmiMedicina) {
                        AdmiMedicina am = (AdmiMedicina) accion;

                        cs2 = connection.prepareCall("{CALL createAdmiMedicina(?)}");
                        cs2.setInt(1, idAccion);//idAdmiMedicine               
                        cs2.executeQuery();
                        cs2.close();

                        int idAdmiMedicina = Integer.parseInt(validate.ultimoId("AdmiMedicinas"));

                        //AdmiMedicina: TomaMedicina
                        for (Producto medicamento : am.getMedicamentos()) {

                            cs3 = connection.prepareCall("{CALL createTomaMedicina(?, ?, ?)}");
                            cs3.setInt(1, idAdmiMedicina);//idTake
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

                }

            }

        }

    }

    @Override
    public ArrayList<ConsultaMedica> readAllConsultasPaciente(String cedulaNombre) throws Exception {

        consultasObtenidas = new ArrayList<>();

        int nroHistoria;
        if (Validate.isNumeric(cedulaNombre)) {
            nroHistoria = validate.obtenerNroHistoriaCedula(cedulaNombre);
        } else {
            nroHistoria = validate.obtenerNroHistoriaNombre(cedulaNombre);
        }

        cs = connection.prepareCall("{CALL readNroHistoriaConsulta(?)}");
        cs.setInt(1, nroHistoria);
        rs = cs.executeQuery();

        //Operacion-ConsultaMedica
        while (rs.next()) {
            cm = new ConsultaMedica();
            ord = new Orden();
            tm = new Tratamiento();
            int idOperacion = rs.getInt("idOperacion"),
                    idPersona1 = rs.getInt("idPerson1"),
                    idPersona2 = rs.getInt("idPerson2"),
                    idConsulta = rs.getInt("idConsulta");

            String tipo = rs.getString("Tipo"),
                    motivos = rs.getString("Motivos"),
                    examenFisico = rs.getString("ExamenFisico"),
                    procedimiento = rs.getString("Procedimiento"),
                    acompañante = rs.getString("Acompañante"),
                    relacion = rs.getString("Relacion");
            Timestamp fechaHoraInicio = (Timestamp) rs.getObject("FechaHoraInicio"),
                    fechaHoraFin = (Timestamp) rs.getObject("FechaHoraFin");
            boolean emergencia = rs.getBoolean("esEmergencia");

            cm.setIdOperacion(idOperacion);
            cm.setTipo(tipo);
            cm.setIdMedicalVisit(idOperacion);
            cm.setIdConsulta(idConsulta);
            cm.setIdPersona1(idPersona1);
            cm.setIdPersona2(idPersona2);
            cm.setFechaHoraInicio(fechaHoraInicio.toLocalDateTime());
            cm.setFechaHoraFin(fechaHoraFin.toLocalDateTime());
            cm.setMotivos(motivos);
            cm.setExamenFisico(examenFisico);
            cm.setProcedimiento(procedimiento);
            cm.setEmergency(emergencia);
            cm.setAcompañante(acompañante);
            cm.setRelacion(relacion);

            cm.setSignosVitales(readSignosVitales(idConsulta));
            cm.setProximasConsultas(readCitas(idConsulta));
            cm.setDiagnosticos(readDiagnosticos(idConsulta));

            cm.setOrden(readOrden(idConsulta));

            tm = readTratamientoSinHorarios(idConsulta);//tratamiento sin horarios
            int idTratamiento = tm.getIdTratamiento();
            tm.setHorarios(readTratamientoHorarios(idTratamiento));
            cm.setTratamiento(tm);
            consultasObtenidas.add(cm);
        }

        conexion.desconectar();
        return consultasObtenidas;

    }

    private SignosVitales readSignosVitales(int idConsulta) throws Exception {

        signos = new SignosVitales();
        cs2 = connection.prepareCall("{CALL readConsultaSignosVitales(?)}");
        cs2.setInt(1, idConsulta);
        rs2 = cs2.executeQuery();
        rs2.next();
        int idSignosVitales = rs2.getInt("idSignosVitales");
        double pulso = rs2.getDouble("Pulso"),
                frecuenciaRespiratoria = rs2.getDouble("FrecuenciaRespiratoria"),
                presionSistolica = rs2.getDouble("PresionSistolica"),
                presionDiastolica = rs2.getDouble("PresionDiastolica"),
                saturacionOxigeno = rs2.getDouble("SaturacionOxigeno"),
                temperatura = rs2.getDouble("Temperatura"),
                talla = rs2.getDouble("Talla"),
                peso = rs2.getDouble("Peso");

        signos.setIdSignosVitales(idSignosVitales);
        signos.setIdVitalSigns(idConsulta);
        signos.setPulso(pulso);
        signos.setFrecuenciaRespiratoria(frecuenciaRespiratoria);
        signos.setPresionSistolica(presionSistolica);
        signos.setPresionDiastolica(presionDiastolica);
        signos.setSaturacionOxigeno(saturacionOxigeno);
        signos.setTemperatura(temperatura);
        signos.setTalla(talla);
        signos.setPeso(peso);

        return signos;
    }

    private ArrayList<String[]> readCitas(int idConsulta) throws Exception {
        proximasConsultas = new ArrayList<>();
        cs2 = connection.prepareCall("{CALL readConsultaCitas(?)}");
        cs2.setInt(1, idConsulta);
        rs2 = cs2.executeQuery();
        while (rs2.next()) {
            Date fecha = (Date) rs2.getObject("Fecha");
            String descripcion = rs2.getString("Descripcion");

            String[] pc = {String.valueOf(fecha), descripcion};
            proximasConsultas.add(pc);
        }

        return proximasConsultas;

    }

    private Orden readOrden(int idConsulta) throws Exception {
        ord = new Orden();

        cs2 = connection.prepareCall("{CALL readConsultaOrden(?)}");
        cs2.setInt(1, idConsulta);
        rs2 = cs2.executeQuery();

        rs2.next();
        int idOrden = rs2.getInt("idOrden");
        Timestamp fechaHoraAsistencia = (Timestamp) rs2.getObject("FechaHoraAsistencia");
        String descripcion = rs2.getString("Descripcion");
        ord.setIdOrden(idOrden);
        ord.setIdOrder(idConsulta);
        ord.setFechaHoraAsistencia(fechaHoraAsistencia.toLocalDateTime());
        ord.setDescripcion(descripcion);
        ord.setParametros(readOrdenParametros(idOrden));
        return ord;

    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    private ArrayList<String> readOrdenParametros(int idOrden) throws Exception {
        parametros = new ArrayList<>();
        cs3 = connection.prepareCall("{CALL readOrdenParametros(?)}");
        cs3.setInt(1, idOrden);
        rs3 = cs3.executeQuery();

        while (rs3.next()) {
            parametros.add(rs3.getString("Parametro"));
        }

        return parametros;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////

    private HashMap<String, ArrayList<String[]>> readDiagnosticos(int idConsulta) throws Exception {
        diagnosticos = new HashMap<>();
        diagnosticos.put("Personales", new ArrayList<>());
        diagnosticos.put("Familiares", new ArrayList<>());

        cs2 = connection.prepareCall("{CALL readConsultaDiagnosticos(?)}");
        cs2.setInt(1, idConsulta);
        rs2 = cs2.executeQuery();

        while (rs2.next()) {
            String diagnostico = rs2.getString("Diagnostico"),
                    cie10 = rs2.getString("CIE10"),
                    tipoAntecedente = rs2.getString("TipoAntecedente");
            String[] d = {diagnostico, cie10};
            if (tipoAntecedente.equals("Personal")) {
                (diagnosticos.get("Personales")).add(d);
            } else if (tipoAntecedente.equals("Familiar")) {
                (diagnosticos.get("Familiares")).add(d);
            }

        }

        return diagnosticos;
    }

    private Tratamiento readTratamientoSinHorarios(int idConsulta) throws Exception {
        tm2 = new Tratamiento();
        cs2 = connection.prepareCall("{CALL readConsultaTratamiento(?)}");
        cs2.setInt(1, idConsulta);
        rs2 = cs2.executeQuery();

        rs2.next();

        int idTratamiento = rs2.getInt("idTratamiento"),
                idSufferer = rs2.getInt("idSufferer");

        Date fechaInicio = (Date) rs2.getObject("FechaInicio"),
                fechaFin = (Date) rs2.getObject("FechaFin");
        String medicacion = rs2.getString("Medicacion"),
                indicaciones = rs2.getString("Indicaciones");

        tm2.setIdTratamiento(idTratamiento);
        tm2.setIdTreatment(idConsulta);
        tm2.setIdSufferer(idSufferer);
        tm2.setFechaInicio(fechaInicio.toLocalDate());
        tm2.setFechaFin(fechaFin.toLocalDate());
        tm2.setFechas();
        tm2.setMedicacion(medicacion);
        tm2.setIndicaciones(indicaciones);

        return tm2;
    }

    private ArrayList<Horario> readTratamientoHorarios(int idTratamiento) throws Exception {

        horarios = new ArrayList<>();
        cs2 = connection.prepareCall("{CALL readTratamientoHorarios(?)}");
        cs2.setInt(1, idTratamiento);
        rs2 = cs2.executeQuery();

        while (rs2.next()) {

            int idHorario = rs2.getInt("idHorario");
            Time hora = (Time) rs2.getObject("Hora");
            String condicionComida = rs2.getString("CondicionComida");
            horario = new Horario();
            horario.setIdHorario(idHorario);
            horario.setHora(hora.toLocalTime());
            horario.setCondicionComida(condicionComida);

            horario.setAccionesHorarios(readHorarioAcciones(idHorario));
            System.out.println("Horario: "+horario);
            horarios.add(horario);
        }

        return horarios;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    private ArrayList<HorarioAccion> readHorarioAcciones(int idHorario) throws Exception {
        horariosAcciones = new ArrayList<>();
        cs3 = connection.prepareCall("{CALL readHorariosAcciones(?)}");
        cs3.setInt(1, idHorario);
        rs3 = cs3.executeQuery();

        while (rs3.next()) {

            int idHorarioAccion = rs3.getInt("idHorarioAccion"),
                    idActivity = rs3.getInt("idActivity");
            Date fecha = (Date) rs3.getObject("Fecha");
            Time horaEjecucion = (Time) rs3.getObject("HoraEjecucion");
            horarioAccion = new HorarioAccion();
            horarioAccion.setIdHorarioAccion(idHorarioAccion);
            horarioAccion.setFecha(fecha.toLocalDate());
            horarioAccion.setIdActivity(idActivity);
            if (horaEjecucion != null) {
                horarioAccion.setHoraEjecucion(horaEjecucion.toLocalTime());
            }
            horarioAccion.setAccion(readAccion(idActivity));
            horariosAcciones.add(horarioAccion);
        }

        return horariosAcciones;
    }

    private Accion readAccion(int idActivity) throws Exception {
        cs4 = connection.prepareCall("{CALL readAccion(?)}");
        cs4.setInt(1, idActivity);
        rs4 = cs4.executeQuery();
        rs4.next();
        int idAccion = rs4.getInt("idAccion");
        String tipo = rs4.getString("Tipo");

        if (tipo.equals("MedicionPA")) {
            cs5 = connection.prepareCall("{CALL readAccionMedicionPA(?)}");
            cs5.setInt(1, idAccion);
            rs5 = cs5.executeQuery();
            rs5.next();

            int idMedicionPA = rs5.getInt("idMedicionPA");
            double presionSistolica = rs5.getDouble("PresionSistolica"),
                    presionDiastolica = rs5.getDouble("PresionDiastolica"),
                    pulso = rs5.getDouble("Pulso");
            mpa = new MedicionPA();
            mpa.setIdAccion(idAccion);
            mpa.setIdMeasurePA(idAccion);
            mpa.setIdMedicionPA(idMedicionPA);
            mpa.setPresionSistolica(presionSistolica);
            mpa.setPresionDiastolica(presionDiastolica);
            mpa.setPulso(pulso);
            accion = mpa;

        } else if (tipo.equals("MedicionGlucosa")) {
            cs5 = connection.prepareCall("{CALL readAccionMedicionGlucosa(?)}");
            cs5.setInt(1, idAccion);
            rs5 = cs5.executeQuery();
            rs5.next();

            int idMedicionGlucosa = rs5.getInt("idMedicionGlucosa");
            double glucosa = rs5.getDouble("Glucosa");
            mg = new MedicionGlucosa();
            mg.setIdAccion(idAccion);
            mg.setIdMeasureGlucose(idMedicionGlucosa);
            mg.setIdMedicionGlucosa(idMedicionGlucosa);
            mg.setGlucosa(glucosa);
            accion = mg;

        } else if (tipo.equals("AdmiMedicina")) {
            cs5 = connection.prepareCall("{CALL readAccionAdmiMedicina(?)}");
            cs5.setInt(1, idAccion);
            rs5 = cs5.executeQuery();
            rs5.next();

            int idAdmiMedicina = rs5.getInt("idAdmiMedicina");
            am = new AdmiMedicina();
            am.setIdAccion(idAccion);
            am.setIdAdmiMedicine(idAccion);
            am.setIdAdmiMedicina(idAdmiMedicina);
            am.setMedicamentos(readTomaMedicinaProductos(idAdmiMedicina));
            accion = am;

        }

        return accion;
    }

    private ArrayList<Producto> readTomaMedicinaProductos(int idTake) throws Exception {
        medicamentos = new ArrayList<>();
        cs6 = connection.prepareCall("{CALL readTomaMedicinaProductos(?)}");
        cs6.setInt(1, idTake);
        rs6 = cs6.executeQuery();
        while (rs6.next()) {
            int idProducto = rs6.getInt("idProducto"),
                    cantidad = rs6.getInt("Cantidad");
            String nombreComercial = rs6.getString("NombreComercial"),
                    presentacion = rs6.getString("Presentacion"),
                    laboratorio = rs6.getString("Laboratorio");

            medicamento = new Producto();
            medicamento.setIdProducto(idProducto);
            medicamento.setCantidad(cantidad);
            medicamento.setNombreComercial(nombreComercial);
            medicamento.setPresentacion(presentacion);
            medicamento.setLaboratorio(laboratorio);

            medicamentos.add(medicamento);

        }

        return medicamentos;

    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public ArrayList<ConsultaMedica> readAllConsultasFecha(LocalDate fecha) throws Exception {

        consultasObtenidas = new ArrayList<>();
        cs = connection.prepareCall("{CALL readFechaConsulta(?)}");
        cs.setObject(1, fecha);
        rs = cs.executeQuery();

        //Operacion-ConsultaMedica
        while (rs.next()) {
            cm = new ConsultaMedica();
            ord = new Orden();
            tm = new Tratamiento();
            int idOperacion = rs.getInt("idOperacion"),
                    idPersona1 = rs.getInt("idPerson1"),
                    idPersona2 = rs.getInt("idPerson2"),
                    idConsulta = rs.getInt("idConsulta");
            System.out.println("idOperacion: " + idOperacion);

            String tipo = rs.getString("Tipo"),
                    motivos = rs.getString("Motivos"),
                    examenFisico = rs.getString("ExamenFisico"),
                    procedimiento = rs.getString("Procedimiento"),
                    acompañante = rs.getString("Acompañante"),
                    relacion = rs.getString("Relacion");
            Timestamp fechaHoraInicio = (Timestamp) rs.getObject("FechaHoraInicio"),
                    fechaHoraFin = (Timestamp) rs.getObject("FechaHoraFin");
            boolean emergencia = rs.getBoolean("esEmergencia");

            cm.setIdOperacion(idOperacion);
            cm.setTipo(tipo);
            cm.setIdMedicalVisit(idOperacion);
            cm.setIdConsulta(idConsulta);
            cm.setIdPersona1(idPersona1);
            cm.setIdPersona2(idPersona2);
            cm.setFechaHoraInicio(fechaHoraInicio.toLocalDateTime());
            cm.setFechaHoraFin(fechaHoraFin.toLocalDateTime());
            cm.setMotivos(motivos);
            cm.setExamenFisico(examenFisico);
            cm.setProcedimiento(procedimiento);
            cm.setEmergency(emergencia);
            cm.setAcompañante(acompañante);
            cm.setRelacion(relacion);

            cm.setSignosVitales(readSignosVitales(idConsulta));
            cm.setProximasConsultas(readCitas(idConsulta));
            cm.setDiagnosticos(readDiagnosticos(idConsulta));

            cm.setOrden(readOrden(idConsulta));

            tm = readTratamientoSinHorarios(idConsulta);//tratamiento sin horarios
            int idTratamiento = tm.getIdTratamiento();
            tm.setHorarios(readTratamientoHorarios(idTratamiento));
            cm.setTratamiento(tm);
            consultasObtenidas.add(cm);
        }

        conexion.desconectar();
        return consultasObtenidas;

    }

}
