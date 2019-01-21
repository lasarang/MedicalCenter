/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import FamiliaOperaciones.ConsultaDAOImpl;
import FamiliaOperaciones.ConsultaMedica;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author juanjimenez
 */
public class ConsultasFechaFXMLController implements Initializable {

    @FXML
    private Label LblTitulo3;
    @FXML
    private Label LblTitulo2;
    @FXML
    private ImageView imgaeBack;
    @FXML
    private Label titulo;
    @FXML
    private ScrollPane Scroll;
    @FXML
    private Label lblSubtitulo;
    @FXML
    private VBox ContenedorFechas;
    
    private ConsultaDAOImpl consulta ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        // TODO
    }    
    
    
    private void CargarCosultas(LocalDate fecha) {
        
        try {
            for(ConsultaMedica m : consulta.readAllConsultasFecha(fecha)){
                
                
                
                
                
                
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    
    @FXML
    private void BackConsulta(Event event) throws IOException{
       
        
    Parent BackConsultaParent = FXMLLoader.load(getClass().getResource("/Pantallas/ConsultasFXML.fxml"));
    Scene  BackConsultaScene = new Scene(BackConsultaParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackConsultaScene);
        window.show();
    }
    
    
    
    
    
    
    
    
    
    
    
}
