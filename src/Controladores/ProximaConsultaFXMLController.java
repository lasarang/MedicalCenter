/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesAuxiliares.Cita;
import ClasesAuxiliares.Horario;
import ClasesAuxiliares.Orden;
import ClasesAuxiliares.SignosVitales;
import ClasesAuxiliares.Tratamiento;
import static Controladores.ActividadFXMLController.horarios;
import static Controladores.ConsultaP4FXMLController.Ffin;
import static Controladores.ConsultaP4FXMLController.Finicio;
import static Controladores.ConsultaP4FXMLController.indicaciones;
import static Controladores.ConsultaP4FXMLController.medicacion;
import static Controladores.ConsultaPaso1FXMLController.datos;
import static Controladores.ConsultasFXMLController.CedulaPC;
import static Controladores.ConsultasFXMLController.Nhistoria;
import static Controladores.ConsultasP3FXMLController.Descripcion;
import static Controladores.ConsultasP3FXMLController.Lugar;
import static Controladores.ConsultasP3FXMLController.examenes;
import static Controladores.ConsultasPaso2FXMLController.ExFísico;
import static Controladores.ConsultasPaso2FXMLController.Procedimiento;
import static Controladores.ConsultasPaso2FXMLController.diagnosticos;
import static Controladores.LoginFXMLController.idMedico;
import static Controladores.LoginFXMLController.inicio;
import Extra.Validate;
import FamiliaOperaciones.ConsultaDAOImpl;
import FamiliaOperaciones.ConsultaMedica;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author juanjimenez
 */
public class ProximaConsultaFXMLController implements Initializable {

    @FXML
    private Button btSalir;
    @FXML
    private Label LblTtiuloCentral;
    @FXML
    private Label LblTitulo3;
    @FXML
    private Label LblTitulo2;
    @FXML
    private Label LblTitulo1;
    @FXML
    private ImageView ImgBack;
    
  
    
    ////Datos de Horarios / Horas y tomas de presion o glucosa///// 
    private String Hora1;
    private String Hora2;
    private String Hora3;
    
    private String presion1;
    private String presion2;
    private String presion3;
    
    private String glucosa1;
    private String glucosa2;
    private String glucosa3;
    /////////////////////////////////////////////////
    
    
    
    
//////////////////////
     private HBox horizonte;
     private VBox centro;
     private Scene NuevaEscena;
     private Stage stage ;
     private BorderPane contenedorP;
     private ComboBox<String> combo_categ ;
     private TextField nbIn;
     
     private  TextArea DesIn;
    @FXML
    private ImageView imageMas;
    @FXML
    private Label AgregarConsulta;
    
    private ArrayList<Cita> citas = new ArrayList<>();
    
    
    @FXML
    private ScrollPane Scroll;
    @FXML
    private VBox ContenedorScroll;
    
    private ConsultaDAOImpl Consulta = new ConsultaDAOImpl();
    
    private Validate validar = new Validate();
    
    private DatePicker Fecha;
    
    /**
     * Initializes the controller class.
     */
    
     ////DATOS QUE VAN A LA SIGUIENTE PAGINA ///// 
    
   

   
    /////////////////////////////////
    
    //variable de hora  fin de consulta
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // citas.add(new Cita("2019-01-02","Meciona Gneral"));
        //citas.add(new Cita("2019-01-04","Med Gneral"));
        //citas.add(new Cita("2019-01-06","caca General"));
    }    

    @FXML
    private void BackActividades(Event event) throws IOException {
            
    Parent BackActivParent = FXMLLoader.load(getClass().getResource("/Pantallas/ActividadFXML.fxml"));
    Scene  BackActiScene = new Scene(BackActivParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackActiScene);
        window.show();
    }

    @FXML
    private void RegresarMenu(ActionEvent event) throws IOException, Exception {
         Parent BackMenuParent = FXMLLoader.load(getClass().getResource("/Pantallas/MenuFXML.fxml"));
         Scene  BackMenuScene = new Scene(BackMenuParent);
    
         
          
        ConsultaMedica consulta =new ConsultaMedica();
         
        consulta.setIdOperacion(Integer.parseInt(validar.ultimoId("Operaciones")) + 1);
        
        //int idConsulta = Integer.parseInt(validar.ultimoId("ConsultasMedicas")) + 1;
        consulta.setIdPersona1(1);
        consulta.setIdPersona2(Nhistoria);
        //consulta.setCedulaPaciente(CedulaPC); 
           
           int idConsulta = Integer.parseInt(validar.ultimoId("ConsultasMedicas")) + 1;
           
           consulta.setIdConsulta(idConsulta);
           consulta.setTipo("ConsultaMedica");
           //consulta.setIdMedicalVisit(idConsulta);
           consulta.setIdOperacion(Integer.parseInt(validar.ultimoId("Operaciones")) + 1);
           ///fechas////
        consulta.setFechaHoraInicio(inicio);
        consulta.setFechaHoraFin(LocalDateTime.now());
           
 
         
        
        String  acompanante = "", relacion = "", motivo= "", Fcardica ="",Fresp ="",Oxig="", 
                temp="",sistolica="",talla="", peso="",diastolica="" ;
        
        if(!datos.get(0).equals("Emergencia")){
            consulta.setEmergency(false);
            acompanante = datos.get(1);
            relacion = datos.get(2);motivo = datos.get(3);
            Fcardica = datos.get(4);Fresp = datos.get(5); 
            Oxig = datos.get(6);temp = datos.get(7);
            sistolica = datos.get(8); talla = datos.get(9);
            peso =datos.get(10); diastolica= datos.get(11);
        }else{
            consulta.setEmergency(true);
            acompanante = datos.get(1);
            relacion = datos.get(2);motivo = datos.get(3);
            Fcardica = datos.get(4);Fresp = datos.get(5); 
            Oxig = datos.get(6);temp = datos.get(7);
            sistolica = datos.get(8); talla = datos.get(9);
            peso =datos.get(10); diastolica= datos.get(11);
            
        }
       
        consulta.setMotivos(motivo);
        consulta.setAcompañante(acompanante);
        consulta.setRelacion(relacion);
        consulta.setExamenFisico(ExFísico);
        consulta.setProcedimiento(Procedimiento);
        
        //SignosVitales
            SignosVitales signos = new SignosVitales();
          //  signos.setIdSignosVitales(Integer.parseInt(validar.ultimoId("SignosVitales"))+1);
            signos.setIdVitalSigns(idConsulta);
            signos.setPulso( Integer.parseInt(Fcardica) );//latidos/min
            signos.setFrecuenciaRespiratoria(Integer.parseInt(Fresp));//veces/min
            signos.setPresionSistolica(Integer.parseInt(sistolica));//mmHg
            signos.setPresionDiastolica(Integer.parseInt(diastolica));
            signos.setSaturacionOxigeno(Double.parseDouble(Oxig));//%
            signos.setTemperatura(Integer.parseInt(temp));//c
            signos.setTalla(Double.parseDouble(talla));//cm
            signos.setPeso(Double.parseDouble(peso));//kg
            System.out.println(diagnosticos.toString());
            consulta.setSignosVitales(signos);
            consulta.setDiagnosticos(diagnosticos);
            System.out.println( "Consulta hasta diagnostico "+consulta.toString());
            System.out.println("creacion de objetos\n");
            
            System.out.println( "Diagnostico:"+ diagnosticos.toString() + "\n");
            
            Orden ord = new Orden();
            ord.setIdOrden(Integer.parseInt(validar.ultimoId("Ordenes"))+1);
            ord.setFechaHoraAsistencia(Lugar);
            ord.setDescripcion(Descripcion);
            ord.setExamenes(examenes);
            consulta.setOrden(ord);
            System.out.println("ordenes pC" + ord.toString() + "\n");
            
            Tratamiento tratamiento = new Tratamiento();
            //crecacion de id tratamiento////
            int idTratamiento = Integer.parseInt(validar.ultimoId("Tratamientos")) + 1;
            tratamiento.setIdTratamiento(idTratamiento);
            ///////.///////
            
            tratamiento.setFechaFin(Ffin);
            tratamiento.setFechaInicio(Finicio);
            tratamiento.setFechas();
            for(Horario h : horarios){
                //h2.setIdSchedule(idTratamiento);
                h.setIdSchedule(idTratamiento);
            }

            tratamiento.setHorarios(horarios);
            tratamiento.setIndicaciones(indicaciones);
            tratamiento.setMedicacion(medicacion);
            
            consulta.setTratamiento(tratamiento);
           
            System.out.println("Antes del fire "+ citas.toString());

            consulta.setProximasConsultas(citas);
             
           System.out.println("Objeto consulta:" + consulta.toString());
            
           
        
            Consulta.create(consulta);
            
         
         
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackMenuScene);
        window.show();
        
    }
    
    @FXML
      private void NuevConsulta(Event evento) {
           // System.out.println(datos.toString());
       contenedorP = new BorderPane();
   
       contenedorP = new BorderPane();
       contenedorP.setTop(topBorder());
       contenedorP.setBottom(BottonBorder());
       contenedorP.setCenter(crearCentro());

        stage = new Stage();
       stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(contenedorP,450, 300);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Agregar Cita");
        stage.show();
    }
      
        private void Alerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cuadro de información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
      
      
     private HBox topBorder(){   // crea los elementos del top de la pagina 
        HBox  topPagina = new HBox();  
        Label  tituloP= new Label("Nueva Cita");
      // creando el borde superior
      
       tituloP.setPadding(new Insets(2,2,2,2));
       
       
        topPagina.setAlignment(Pos.CENTER);
        topPagina.getChildren().add(tituloP); 
        
        return topPagina;
    }
     
       public Pane crearCentro(){
       centro = new VBox();
       
       HBox texto1 = new HBox();
       
       HBox texto3 = new HBox();
       Fecha = new DatePicker();
       Label nb = new Label("Fecha: "); 
       nbIn = new TextField();
       texto1.getChildren().addAll(nb,Fecha);
       nbIn.setPromptText("yyyy-mm-dd  hh-mm");
     

       Label Des = new Label ("Descripción");
       
       DesIn = new TextArea();
       
       DesIn.setMaxSize(200,100);
       
       texto3.getChildren().addAll(Des,DesIn);
        texto1.setSpacing(80);
        
        texto3.setSpacing(100);
        
       
       centro.getChildren().addAll(texto1,texto3);
       centro.setSpacing(40);
       centro.setPadding(new Insets(20));
       centro.setAlignment(Pos.CENTER);
           
       return centro;
   }
       
       
       public HBox BottonBorder(){
         
         horizonte = new HBox();
         //         botones
       // creacion de botones y posicionamiento
       Button Guardar = new Button("Guardar");
       Guardar.setAlignment(Pos.BOTTOM_LEFT); 
       Guardar.setOnMouseClicked(EventoG ->{
           
           if(DesIn.getText().equals("") || Fecha.getValue() == null){
               
                 Alerta("No ha colocado la pregunta!");
               
           }else{  // formatearFechaHoraFb
               
               citas.add(new Cita(Fecha.getValue().toString(),DesIn.getText()));
               HBox contenedorH = new HBox();
               Label hora = new Label("Fecha:");
               Label citaH = new Label(nbIn.getText());
               Label descrip= new Label("Descripcion:");   
               Label citaD = new Label(DesIn.getText());
               hora.setFont(Font.font(20));
               citaH.setFont(Font.font(20));
               descrip.setFont(Font.font(20));
               citaD.setFont(Font.font(20));
               
               contenedorH.getChildren().addAll(hora,citaH,descrip,citaD);
               contenedorH.setSpacing(20);
               contenedorH.setAlignment(Pos.CENTER);
               ContenedorScroll.getChildren().add(contenedorH);
               ContenedorScroll.setSpacing(20);
               stage.close();
           }
           
      
                 });
               
       Button Cancelar = new Button("Cancelar");
       Cancelar.setCancelButton(true);
       Cancelar.setAlignment(Pos.BOTTOM_RIGHT);
       Cancelar.setOnMouseClicked(Evento2->{
           stage.close();
       });
       
       // anadir al HBox horizonte que sera la parte inferior del BorderPane
       horizonte.getChildren().addAll(Guardar,Cancelar);
       contenedorP.setPrefSize(400, 400);  // le das un tamano minimo al borderpane
       // aqui se setea el margen o distancia entre los botones cancelar y guardar  y se agrega el Hbox al borderPane
       HBox.setMargin(Guardar,new Insets(0,0,10,10) );
       HBox.setMargin(Cancelar,new Insets(0,0,10,10) );
       horizonte.setAlignment(Pos.CENTER);
       
         return horizonte;
     }

    
    
}
