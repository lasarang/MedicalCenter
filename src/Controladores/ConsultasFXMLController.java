/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Extra.Validate;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanjimenez
 */
public class ConsultasFXMLController implements Initializable {

    //@FXML
    //private AnchorPane AnchorPane;
    @FXML
    private ImageView Image1;
    @FXML
    private Label titulo;
    @FXML
    private Label subtitulo;
    @FXML
    private Label LbNConsulta;
    @FXML
    private Label LbBusqueda;
    @FXML
    private Label LbExisteP;
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView ImageMas;
    @FXML
    private ImageView imgaeBack;
    @FXML
    private ImageView ImagNewPaciente;
   
    @FXML
    private Button btnBuscar;
    
   private Validate validar = new Validate();
    
    private static LocalDate Fecha = LocalDate.parse("2018-12-12");
    
    
    @FXML
    private TextField TxFechas;
    @FXML
    private TextField Cedula;
    @FXML
    private Label PacienteError;
    
    
   ///datos para enviar a la base //////// 
    public  static int Nhistoria;
    
    public static String CedulaPC;  //cedula paciente consulta
    //////////////////////////////
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    } 

    
      @FXML
    private void BackMenu(Event event) throws IOException{
       
        
    Parent BackMenuParent = FXMLLoader.load(getClass().getResource("/Pantallas/MenuFXML.fxml"));
    Scene  BackMenuScene = new Scene(BackMenuParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackMenuScene );
        window.show();
    }
    
   
    
    
    
     @FXML
    private void verConsultasFechas(Event event) throws IOException, Exception{
                
       
            //Fecha = LocalDate.parse(TxFechas.getText()) ;
            
            
            
            Parent ConsultasFechas = FXMLLoader.load(getClass().getResource("/Pantallas/ConsultasFechaFXML.fxml"));
            Scene  ConsultasFechasScene = new Scene(ConsultasFechas);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(ConsultasFechasScene );
        window.show();
            
       
        
        
    }
    

    @FXML
    private void IrRegistroPa(Event event) throws IOException{
       
        
    Parent RegistroParent = FXMLLoader.load(getClass().getResource("/Pantallas/RegistroPacienteFXML.fxml"));
    Scene  RegistroScene = new Scene(RegistroParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(RegistroScene );
        window.show();
    }
    
    @FXML
    private void ConsultaP1(Event event) throws IOException{
        try {
            System.out.println(Cedula.getText());
            System.out.println(validar.obtenerNroHistoriaCedula(Cedula.getText()));
            if(!Cedula.getText().equals("") && validar.obtenerNroHistoriaCedula(Cedula.getText()) >=1){
                
                Nhistoria  = validar.obtenerNroHistoriaCedula(Cedula.getText()); ///nhistoria statica 
                CedulaPC = Cedula.getText(); /// valirable estatica de cedula del paciente
                
                Parent ConsultaP1Parent = FXMLLoader.load(getClass().getResource("/Pantallas/ConsultaPaso1FXML.fxml"));
                Scene  Consultap1Scene = new Scene(ConsultaP1Parent);
                
                //aqui nos da la infomarcion del stage
                Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
                window.setScene(Consultap1Scene );
                window.show();
                
                
            }
        } catch (Exception ex) {
            PacienteError.setVisible(true);
            
        }

    }
    
}
