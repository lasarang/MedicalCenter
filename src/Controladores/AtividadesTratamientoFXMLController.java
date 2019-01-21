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
public class AtividadesTratamientoFXMLController implements Initializable {

    @FXML
    private ImageView ImgBack;
    @FXML
    private Label LblTitulo1;
    @FXML
    private Label LblTitulo2;
    @FXML
    private Label LblTitulo3;
    @FXML
    private Button btConsultas;
    @FXML
    private Label LblTtiuloCentral;
    @FXML
    private Label lblMedicion1;
    @FXML
    private Label lblMedicion2;
    @FXML
    private Label lblMedicon;
    @FXML
    private ImageView imgaMed1;
    @FXML
    private ImageView imgeMed2;
    @FXML
    private Label lblEstadoHC;
    @FXML
    private ScrollPane ScrollGlucosa;
    @FXML
    private VBox ContentGlucosa;
    @FXML
    private ScrollPane ScrollPresion;
    @FXML
    private VBox ContentPresion;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
      @FXML
       private void VerConsultas(ActionEvent event) throws IOException{
       
        
    Parent ActividadesParent = FXMLLoader.load(getClass().getResource("/Pantallas/PConsultasFXML.fxml"));
    Scene  ActividadesScene = new Scene(ActividadesParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(ActividadesScene);
        window.show();
    }
    
       
  
       
       
    
    @FXML
    private void BackTratamiento(Event event) throws IOException{
       
        
    Parent BackMenuParent = FXMLLoader.load(getClass().getResource("/Pantallas/PacienteTratamientoFXML.fxml"));
    Scene  BackMenuScene = new Scene(BackMenuParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackMenuScene);
        window.show();
    }
    
}
