/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appmedicalcenter;

import BaseDeDatos.Conexion;

import ClasesAuxiliares.FabricaAbstracta;
import ClasesAuxiliares.FabricaProductor;
import ClasesAuxiliares.Horario;
import ClasesAuxiliares.HorarioAccion;
import ClasesAuxiliares.Orden;
import FamiliaPersonas.Paciente;
import FamiliaPersonas.PacienteDAOImpl;
import ClasesAuxiliares.ProductoDAOImpl;
import ClasesAuxiliares.SignosVitales;
import ClasesAuxiliares.Tratamiento;
import Extra.Validate;
import FamiliaAcciones.Accion;
import FamiliaAcciones.AdmiMedicina;
import FamiliaAcciones.MedicionGlucosa;
import FamiliaAcciones.MedicionPA;
import FamiliaOperaciones.ConsultaDAOImpl;
import FamiliaOperaciones.ConsultaMedica;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class AppMedicalCenter extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        try {
            (Conexion.getInstancia()).conectar();

            PacienteDAOImpl pdao = new PacienteDAOImpl();
            ProductoDAOImpl pdtdao = new ProductoDAOImpl();
            ConsultaDAOImpl cdao = new ConsultaDAOImpl();
            Validate validate = new Validate();

            ArrayList<String> ocupaciones = new ArrayList<>(), telefonos = new ArrayList<>(), correos = new ArrayList<>();
            ArrayList<String[]> domicilios = new ArrayList<>();
            ocupaciones.add("Actriz");
            ocupaciones.add("Azafata");

            telefonos.add("0925354677");
            telefonos.add("22766956");

            correos.add("correo1@gmail.com");
            correos.add("correo2@hotmail.es");

            String[] s1 = {"Guayaquil", "Cdla. Las Acacias"}, s2 = {"Quito", "Plaza Fosch"};
            domicilios.add(s1);
            domicilios.add(s2);

            FabricaAbstracta fa = FabricaProductor.getFabrica("PERSONA FABRICA");
            Paciente paciente = (Paciente) fa.getPersona("Paciente");

            paciente.setIdPersona("0913499742");
            paciente.setNombre("Diana Prince");
            paciente.setGenero("Femenino");
            paciente.setFechaNacimiento(LocalDate.parse("1995-08-06"));

            paciente.setNroHistoria(Integer.parseInt(validate.ultimoId("Pacientes")) + 1);
            paciente.setEmails(correos);
            paciente.setDomicilios(domicilios);
            paciente.setTelefonos(telefonos);
            paciente.setOcupaciones(ocupaciones);
            paciente.setEstadoCivil("Soltero");
            paciente.setTipo("Paciente");
            paciente.setGrupoSanguineo("B+");

            pdao.create(paciente);
            //Consulta de medicamentos
            System.out.println(pdtdao.readNombre("Gluco").toString());
            System.out.println(pdtdao.readNombreLab("A", "M"));

            String cedulaDoctor = "1102371802", psw = "mesigyna";
            int idMedico = validate.esUsuarioMedico(cedulaDoctor, psw),//Validando y obteniendo el idMedico del doctor
                    idConsulta = Integer.parseInt(validate.ultimoId("ConsultasMedicas")) + 1;
            // se debe guardar aparte para no aumentar el id en los demas campos

            //Creacion de una consulta medica
            ConsultaMedica consulta = new ConsultaMedica();//Si no existe el paciente no se puede iniciar sin registrarlo primero
            consulta.setIdOperacion(Integer.parseInt(validate.ultimoId("Operaciones")) + 1);
            consulta.setIdPersona1(idMedico);//idMedico
            consulta.setIdPersona2(validate.obtenerNroHistoria("0913499742"));//nroHistoria
            consulta.setIdConsulta(idConsulta);
            consulta.setFechaHoraInicio(LocalDateTime.parse("2018-12-29 19:44:44", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            consulta.setFechaHoraFin(LocalDateTime.parse("2018-12-29 20:30:07", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            consulta.setTipo("Consulta Medica");
            consulta.setMotivos("Dolor de cabeza y vomito");
            consulta.setAcompañante("‎Amber Heard");
            consulta.setRelacion("Compañera");
            consulta.setExamenFisico("Presenta contusiones y traumatismos");
            consulta.setProcedimiento("Se realizo una sutura profunda con microcirugia");
            consulta.setEmergency(true);

            //SignosVitales
            SignosVitales signos = new SignosVitales();
            signos.setIdSignosVitales(Integer.parseInt(validate.ultimoId("SignosVitales")));
            signos.setIdVitalSigns(idConsulta);
            signos.setPulso(80);//latidos/min
            signos.setFrecuenciaRespiratoria(14);//veces/min
            signos.setPresionSitolica(120);//mmHg
            signos.setPresionDiastolica(80);
            signos.setSaturacionOxigeno(0.95);//%
            signos.setTemperatura(37);//c
            signos.setTalla(150);//cm
            signos.setPeso(43.2);//kg

            consulta.setSignosVitales(signos);

            //Diagnosticos
            HashMap<String, ArrayList<String[]>> diagnosticos = consulta.getDiagnosticos();

            ArrayList<String[]> familiares = new ArrayList<>(),
                    personales = new ArrayList<>();
            String[] df1 = {"Diabetes Tipo II", "E10"}, //patologia, cie10
                    df2 = {"Hipertension Tipo I", "I10"},
                    df3 = {"Alzhaimer", "G30"},
                    df4 = {"Astigmatismo", "H52.2"},
                    df5 = {"Hemofilia", "D66"},
                    dp1 = {"Esquizofrenia", "E10"},
                    dp2 = {"Hepatitis B", "E10"},
                    dp3 = {"Gastritis", "E10"},
                    dp4 = {"Sinusitis", "E10"};

            familiares.add(df1);
            familiares.add(df2);
            familiares.add(df3);
            familiares.add(df4);
            familiares.add(df5);

            personales.add(dp1);
            personales.add(dp2);
            personales.add(dp3);
            personales.add(dp4);

            diagnosticos.replace("Personales", personales);
            diagnosticos.replace("Familiares", familiares);

            ArrayList<String[]> proximasConsultas = consulta.getProximasConsultas();
            String[] pc1 = {"2019-01-03", "Medicina General - MD Gregory House"}, //fecha,descripcion
                    pc2 = {"2019-01-20", "Endocrinologia - MD Gregory House"},
                    pc3 = {"2019-02-08", "Cardiologia - MD Gregory House"};

            proximasConsultas.add(pc1);
            proximasConsultas.add(pc2);
            proximasConsultas.add(pc3);

            //Orden examenes
            Orden orden = new Orden();
            orden.setIdOrden(Integer.parseInt(validate.ultimoId("Ordenes")) + 1);//Se debe de setear para que puedan escibirse los parametros
            orden.setIdOrder(idConsulta);
            orden.setFechaHoraAsistencia(LocalDateTime.parse("2018-12-30 07:15:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            orden.setDescripcion("Debe acudir en ayuna con un envase de orina");

            ArrayList<String> parametros = new ArrayList<>();
            parametros.add("Colesterol Total");
            parametros.add("Hematocritos");
            parametros.add("Leucocitos");
            parametros.add("Creatinina");
            parametros.add("Urea");
            parametros.add("Marihuana");
            parametros.add("Cocaina");

            orden.setParametros(parametros);
            consulta.setOrden(orden);
            
            
            
            
            

            //Tratamiento
            int idTratamiento = Integer.parseInt(validate.ultimoId("Tratamientos")) + 1;

            Tratamiento tratamiento = new Tratamiento();
            tratamiento.setIdTratamiento(idTratamiento);
            tratamiento.setIdTreatment(idConsulta);
            tratamiento.setFechaInicio(LocalDate.parse("2018-12-31"));
            tratamiento.setFechaFin(LocalDate.parse("2019-01-31"));
            System.out.println("Cantidad de dias tratamiento: "+tratamiento.getFechas().size());
            tratamiento.setMedicacion("Tiazolidinedionas, Sulfonilureas, Antialérgicos");
            tratamiento.setIndicaciones("Se deben tomar 2 tabletas cada doce horas una antes de la comida y otra después, así mismo debe de realizar una medición de glucosa presión arterial junto a cada toma.");
            consulta.setTratamiento(tratamiento);

            ArrayList<Horario> horarios = new ArrayList<>();
            
            /////////////////////////////////////////////////////////////////////////////////////////
            //1er Horario
            Horario h1 = new Horario();
            ArrayList<Accion> acciones1 = new ArrayList<>();
            int idHorario1 = Integer.parseInt(validate.ultimoId("Horarios")) + 1;

            h1.setIdHorario(idHorario1);
            h1.setIdSchedule(idTratamiento);
            h1.setHora(LocalTime.parse("07:15:00"));
            h1.setCondicionComida("AntesComida");
 
            /////////////////////////////////////////////////////////////////////////////////////////
            int idAccionMg1 = Integer.parseInt(validate.ultimoId("Acciones")) + 1;
            MedicionGlucosa mg1 = new MedicionGlucosa();
            mg1.setIdAccion(idAccionMg1);
            mg1.setIdMeasureGlucose(idAccionMg1);
            //mg1.setIdMedicionGlucosa(Integer.parseInt(validate.ultimoId("MedicionesGlucosa")) + 1);
            //mg1.setGlucosa(0);

            int idAccionMpa1 = Integer.parseInt(validate.ultimoId("Acciones")) + 1;
            MedicionPA mpa1 = new MedicionPA();
            mpa1.setIdAccion(idAccionMpa1);
            mpa1.setIdMeasurePA(idAccionMpa1);
            mpa1.setIdMedicionPA(Integer.parseInt(validate.ultimoId("MedicionesPA")) + 1);
            //mpa1.setPresionDiastolica(0);
            //mpa1.setPresionSistolica(0);
            //mpa1.setPulso(0);

          
            int idAccionAm1 = Integer.parseInt(validate.ultimoId("Acciones")) + 1;
            AdmiMedicina am1 = new AdmiMedicina();
            am1.setIdAccion(idAccionAm1);
            am1.setIdAdmiMedicina(Integer.parseInt(validate.ultimoId("AdmiMedicinas")) + 1);
            am1.setIdAdmiMedicine(idAccionAm1);
            am1.setMedicamentos(pdtdao.readNombre("M"));//medicamentos
            
            
            acciones1.add(mg1);
            acciones1.add(mpa1);
            acciones1.add(am1);
            h1.setAcciones(acciones1);
           
            
            horarios.add(h1);
            
            /////////////////////////////////////////////////////////////////////////////////////////
            
            //2do Horario
            Horario h2 = new Horario();
            ArrayList<Accion> acciones2 = new ArrayList<>();
            int idHorario2 = Integer.parseInt(validate.ultimoId("Horarios")) + 1;
            
            h2.setIdHorario(idHorario2);
            h2.setIdHorario(Integer.parseInt(validate.ultimoId("Horarios")) + 1);
            h2.setIdSchedule(idTratamiento);
            h2.setHora(LocalTime.parse("12:30:00"));
            h2.setCondicionComida("AntesComida");
            //////////////////////////////////////////////////////////////////////////////////////////

            int idAccionMg2 = Integer.parseInt(validate.ultimoId("Acciones")) + 1;
            MedicionGlucosa mg2 = new MedicionGlucosa();
            mg2.setIdAccion(idAccionMg2);
            mg2.setIdMedicionGlucosa(Integer.parseInt(validate.ultimoId("MedicionesGlucosa")) + 1);
            mg2.setIdMeasureGlucose(idAccionMg2);
            mg2.setGlucosa(0);

            
            int idAccionAm2 = Integer.parseInt(validate.ultimoId("Acciones")) + 1;
            AdmiMedicina am2 = new AdmiMedicina();
            am2.setIdAccion(idAccionAm2);
            am2.setIdAdmiMedicina(Integer.parseInt(validate.ultimoId("AdmiMedicinas")) + 1);
            am2.setIdAdmiMedicine(idAccionAm2);
            am2.setMedicamentos(pdtdao.readNombre("Gluco"));//medicamentos
            
            acciones2.add(mg2);
            acciones2.add(am2);
            
            h2.setAcciones(acciones2);
            
       
            horarios.add(h2);
            /////////////////////////////////////////////////////////////////////////////////////////
            //3er Horario
            Horario h3 = new Horario();
            ArrayList<Accion> acciones3 = new ArrayList<>();
            int idHorario3 = Integer.parseInt(validate.ultimoId("Horarios")) + 1;

            h3.setIdHorario(idHorario3);
            h3.setIdHorario(Integer.parseInt(validate.ultimoId("Horarios")) + 1);
            h3.setIdSchedule(idTratamiento);
            h3.setHora(LocalTime.parse("19:15:00"));
            h3.setCondicionComida("DespuesComida");
            
            /////////////////////////////////////////////////////////////////////////////////////////
            int idAccionMpa3 = Integer.parseInt(validate.ultimoId("Acciones")) + 1;
            MedicionPA mpa3 = new MedicionPA();
            
            mpa3.setIdAccion(idAccionMpa3);
            mpa3.setIdMedicionPA(idAccionMpa3);
            mpa3.setIdMeasurePA(idAccionMpa3);
            //mpa3.setPresionDiastolica(0);
            //mpa3.setPresionSistolica(0);
            //mpa3.setPulso(0);

            
            int idAccionAm3 = Integer.parseInt(validate.ultimoId("Acciones")) + 1;
            AdmiMedicina am3 = new AdmiMedicina();
           
            am3.setIdAccion(idAccionAm3);
            am3.setIdAdmiMedicina(Integer.parseInt(validate.ultimoId("AdmiMedicinas")) + 1);
            am3.setIdAccion(idAccionAm3);
            am3.setIdAdmiMedicine(idAccionAm3);
            am3.setMedicamentos(pdtdao.readNombreLab("A", "B"));//medicamentos
            
  
            acciones3.add(mpa3);
            acciones3.add(am3);
            
            h3.setAcciones(acciones3);
            
           
            horarios.add(h3);
            
            tratamiento.setHorarios(horarios);
            /////////////////////////////////////////////////////////////////////////////////////////
            
            cdao.create(consulta);

        } catch (Exception ex) {
            System.out.println("No se pudo conectar " + ex);
            ex.printStackTrace();
        }

    }

}
