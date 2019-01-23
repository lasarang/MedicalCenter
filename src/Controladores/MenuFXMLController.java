/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanjimenez
 */
public class MenuFXMLController implements Initializable {

    //@FXML
    //private AnchorPane root;
    @FXML
    private Label LbTitulo;
    @FXML
    private Label LbSubT;
    @FXML
    private ImageView ImageIz;
    @FXML
    private ImageView ImageCentro;
    @FXML
    private Button BtFinalizar;
    @FXML
    private HBox HBFondo;
    @FXML
    private Label lblConsulta;
    @FXML
    private Label lblPacientes;
   
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     * @param url
     * @param 
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }   
    
    
    //Cambio de escenas
    
    @FXML
    private void ChangeScene(Event event) throws IOException{
       
        
    Parent PacienteParent = FXMLLoader.load(getClass().getResource("/Pantallas/PacientesFXML.fxml"));
    Scene  PacienteScene = new Scene(PacienteParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(PacienteScene);
        window.show();
    }
    
   
    
    
    public void Imprimir(String texto){
       // System.out.println(texto);
        //lblConsulta.setText(texto);
    }
    
    
    
    @FXML
    private void IrConsultas(Event event) throws IOException{
       
        
    Parent ConsultaParent = FXMLLoader.load(getClass().getResource("/Pantallas/ConsultasFXML.fxml"));
    Scene  ConsultaScene = new Scene(ConsultaParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(ConsultaScene);
        window.show();
    }    
    
       
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
       
       System.exit(0);
  
    }

    
    
}
