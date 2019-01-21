/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Extra.Validate;
import FamiliaPersonas.PacienteDAOImpl;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanjimenez
 */
public class PacientesFXMLController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblSubtitulo;
    @FXML
    private ImageView Ima1;
    @FXML
    private Label lblPaciente;
    @FXML
    private TextField texfieldCedula;
    @FXML
    private ImageView imgaBack;
    @FXML
    private Button btDetalles;
    
   
    
    private Validate valido = new Validate();
    @FXML
    private Label lblExiste;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
  
    
    
     @FXML
    public void Regresar(Event event) throws IOException{
       
        
    Parent BackMenuParent = FXMLLoader.load(getClass().getResource("/Pantallas/MenuFXML.fxml"));
    Scene  BackMenuScene = new Scene(BackMenuParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackMenuScene);
        window.show();
    }
    
    
    @FXML
    public void BusquedaPaciente(ActionEvent event){
        
        try {
            //System.out.println(valido.obtenerNroHistoriaCedula("0913499742"));
            
            //System.out.println(texfieldCedula.getText());
            while(valido.obtenerNroHistoriaCedula(texfieldCedula.getText()) >=1 ){
                
                FXMLLoader loader = new  FXMLLoader(getClass().getResource("/Pantallas/InfoPacienteFXML.fxml"));
                
                

                Parent BusquedaParent = (Parent)loader.load();

                Scene  BusquedaScene = new Scene(BusquedaParent);
                
                InfoPacienteFXMLController p =loader.getController();
                //MenuFXMLController p = loader.getController();
                p.GetPaciente(texfieldCedula.getText());
               
               
                    Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
                    window.setScene(BusquedaScene);
                    window.show();
                
                
                
            }
            
        } catch (Exception ex) {
            
            lblExiste.setText("Paciente No encontrado");
            texfieldCedula.clear();
            lblExiste.setVisible(true);
            System.out.println(ex.getMessage());
        }
        
    }

    
}
