/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesAuxiliares.Cita;
import static Controladores.PacienteAntecedentesFXMLController.consultaMed;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanjimenez
 */
public class PConsultasFXMLController implements Initializable {

    @FXML
    private ImageView ImgBack;
    @FXML
    private Label LblTitulo1;
    @FXML
    private Label LblTitulo2;
    @FXML
    private Label LblTitulo3;
    @FXML
    private Label LblTtiuloCentral;
    @FXML
    private Button btSalir;
    @FXML
    private ScrollPane Scroll;
    @FXML
    private VBox ScrollContent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        setCitas(consultaMed);
    }    
    
    @FXML
    private void BackActividades(Event event) throws IOException{
       
        
    Parent BackActivParent = FXMLLoader.load(getClass().getResource("/Pantallas/AtividadesTratamientoFXML.fxml"));
    Scene  BackActiScene = new Scene(BackActivParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackActiScene);
        window.show();
    }
    
    
    //consultaMed
    private void setCitas(ArrayList<ConsultaMedica> consultas){
        
        for(ConsultaMedica cm : consultas){
            VBox centro = new VBox();
            for(Cita cita :cm.getProximasConsultas()){
                
                int x = cm.getProximasConsultas().indexOf(cita) +1;
                Label idconsulta = new Label("Cita :     " +  x  );
                idconsulta.setFont(Font.font(30));
                Label hora = new Label("       Fecha:                                   " + cita.getFecha().toString());
                hora.setFont(Font.font(30));
                HBox subcentro = new HBox();
                
                Label Descrip = new Label("              Descripcion:           ");
                Label Descri = new Label(cita.getDescripcion());
                
                Descri.setMinSize(50, 50);
                Descrip.setFont(Font.font(25));
                Descri.setFont(Font.font(25));
                
                 subcentro.getChildren().addAll(Descrip,Descri);
                 subcentro.setMinSize(350, 80);
                centro.getChildren().addAll(idconsulta,hora,subcentro);
                centro.setPadding(new Insets(20));
                
                centro.setAlignment(Pos.CENTER);
                centro.setMinSize(850, 301);
                
            }
            ScrollContent.getChildren().add(centro);
        }
        
        
      
        ScrollContent.setPadding(new Insets(10));
        
    }
    
    
    
      @FXML
    private void RegresarMenu(ActionEvent event) throws IOException{
       
        
    Parent BackMenuParent = FXMLLoader.load(getClass().getResource("/Pantallas/MenuFXML.fxml"));
    Scene  BackMenuScene = new Scene(BackMenuParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackMenuScene);
        window.show();
    }
}
