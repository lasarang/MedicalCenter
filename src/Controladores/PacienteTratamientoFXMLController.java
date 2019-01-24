/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import ClasesAuxiliares.Horario;
import ClasesAuxiliares.HorarioAccion;
import ClasesAuxiliares.Producto;
import static Controladores.PacienteAntecedentesFXMLController.consultaMed;
import FamiliaAcciones.Accion;
import FamiliaAcciones.AdmiMedicina;
import FamiliaAcciones.MedicionGlucosa;
import FamiliaAcciones.MedicionPA;
import FamiliaOperaciones.ConsultaMedica;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanjimenez
 */
public class PacienteTratamientoFXMLController implements Initializable {

    @FXML
    private ImageView ImgBack;
    @FXML
    private Label LblTitulo1;
    @FXML
    private Label LblTitulo2;
    @FXML
    private Label LblTitulo3;
    @FXML
    private Button btTratamientos;
    @FXML
    private Label LblTtiuloCentral;
    @FXML
    private ScrollPane Scroll;
    @FXML
    private VBox ScrolContent;
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        MotrarTratemiento(consultaMed);
        
    }   
    
    
    private void MotrarTratemiento(ArrayList<ConsultaMedica> consulta){
        
        for(ConsultaMedica cm : consulta){
            //////contendeor de fecha 
            HBox Fechas = new HBox();
            Label fechaIn = new Label("Fecha Incio:");
            Label fechInDato = new Label(cm.getTratamiento().getFechaInicio().toString());
            Label fechaOut = new Label("Fecha Fin:");
            Label fecOutDato = new Label(cm.getTratamiento().getFechaFin().toString());
            ///seteando espacios ////
           Fechas.getChildren().addAll(fechaIn,fechInDato,fechaOut,fecOutDato);
            Fechas.setSpacing(30);
            Fechas.setPadding(new Insets(15));
            ScrolContent.getChildren().add(Fechas);
            //System.out.println("COnsultas"+ cm.toString());
            /////Fin contenedor Fechas/////
            //System.out.println("Horarios: \n " +   cm.getTratamiento().getHorarios() );//.getHorarios().get(0).getCondicionComida() );
             for(Horario ho : cm.getTratamiento().getHorarios()){
                 System.out.println(" wb" + ho.getAcciones().toString());
            VBox ContenedorHorario =  new VBox();
            ////titulo//////
            Label tituloHorario = new Label("Horario   " + String.valueOf(cm.getTratamiento().getHorarios().indexOf(ho) +1));
            ContenedorHorario.getChildren().add(tituloHorario);
            ///////fin titulo//////
            ////contenedor de horas y condicones de los horarios////
            HBox ContendorHC = new HBox();
            Label hora = new Label("Hora:");
            Label horaInfo = new Label(ho.getHora().toString());
            Label Condicion = new Label("Condicion:");
            Label CondicionInfo = new Label(ho.getCondicionComida());
            
            ContendorHC.getChildren().addAll(hora,horaInfo,Condicion,CondicionInfo);
            ContendorHC.setSpacing(20);
            ContendorHC.setPadding(new Insets(15));
            ContenedorHorario.getChildren().add(ContendorHC);
            ScrolContent.getChildren().add(ContenedorHorario);
            ////fin de contenedor horas y condicion///
            
            
            
            for(HorarioAccion obj: ho.getAccionesHorarios()){
                    
                //System.out.println("beto1");
            HBox mediciones = new HBox();
            mediciones.setSpacing(20);
             
            if(obj.getAccion() instanceof MedicionGlucosa){

            Label medicion1 = new Label("Medicion:         Presion Arterial");
            mediciones.getChildren().add(medicion1);
            
            }else if(obj.getAccion() instanceof MedicionPA) {
                
                Label medicion2 = new Label("Medicion:         Glucosa");
                mediciones.getChildren().add(medicion2);
                
            } 
            else if(obj.getAccion() instanceof AdmiMedicina){
            
            AdmiMedicina medicacion = (AdmiMedicina)obj.getAccion();
           // System.out.println(medicacion.toString());
            for(Producto p :  medicacion.getMedicamentos() ){
               // System.out.println("---------------");
               // System.out.println(p.toString());
                //System.out.println("beto");
            Label nombre = new Label("Nombre:              " + p.getNombreComercial() );
            Label Presentacion = new Label("Presentacion:              " + p.getPresentacion() );
            Label lab = new Label("Laboratorio:                         " + p.getLaboratorio() );
            //System.out.println("beto");
            if(!ScrolContent.getChildren().contains(nombre)){
                
                ScrolContent.getChildren().addAll(nombre,Presentacion,lab);
            }
            
            
            }
            
            //System.out.println("ndndd");
            }
            
           
                 }
                
            }
 
        }
        ScrolContent.setAlignment(Pos.CENTER);
        ScrolContent.setSpacing(10);
        ScrolContent.setPadding(new Insets(20));
        
        
        
        
    }
    
    
    
      @FXML
       private void VerActividades(ActionEvent event) throws IOException{
       
        
    Parent ActividadesParent = FXMLLoader.load(getClass().getResource("/Pantallas/AtividadesTratamientoFXML.fxml"));
    Scene  ActividadesScene = new Scene(ActividadesParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(ActividadesScene);
        window.show();
    }
    
    
    @FXML
    private void BackExam(Event event) throws IOException{
       
        
    Parent BackMenuParent = FXMLLoader.load(getClass().getResource("/Pantallas/ExamanesLabFXML.fxml"));
    Scene  BackMenuScene = new Scene(BackMenuParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackMenuScene);
        window.show();
    }
    
}
