/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesAuxiliares.Orden;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
public class ExamanesLabFXMLController implements Initializable {

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
    private Button btTratamientos;
    @FXML
    private VBox ContenedroGeneral;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        CargarDatos(consultaMed);
        
    }    
    
    @FXML
       private void VerTratamiento(ActionEvent event) throws IOException{
       
        
    Parent ExamanesParent = FXMLLoader.load(getClass().getResource("/Pantallas/PacienteTratamientoFXML.fxml"));
    Scene  ExamenestesScene = new Scene(ExamanesParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(ExamenestesScene);
        window.show();
    }
       
       
       
      private void CargarDatos(ArrayList<ConsultaMedica> consulta){
          
          for(ConsultaMedica med : consulta){
              HBox subcontenedor = new HBox(); 
              
              ///subcontenedore de datos
              VBox info = new VBox();
              HBox deninf = new HBox();
              HBox fechainf = new HBox();
              //////////////
              
              VBox exam = new VBox();
              
              Label descrip = new Label("Descripcion");
              descrip.setFont(Font.font(15));
              Label descripOrd= new Label(med.getOrden().getDescripcion());
              descripOrd.setFont(Font.font(15));
              //descripOrd.setLayoutX(30);
              deninf.getChildren().addAll(descrip,descripOrd);
              deninf.setSpacing(20);

              Label fecha = new Label("Fecha y Hora de Asistencia");
              fecha.setFont(Font.font(15));
              Label fechaOrd = new Label(med.getOrden().getFechaHoraAsistencia());
              fechaOrd.setFont(Font.font(15));
              fechainf.getChildren().addAll(fecha, fechaOrd);
              fechainf.setSpacing(20);
              
              info.getChildren().addAll(deninf,fechainf);
              info.setSpacing(20);
              Label examenes = new Label("Examenes");
              examenes.setFont(Font.font(15));
              exam.getChildren().add(examenes);
              
              for(String ex : med.getOrden().getExamenes()){
              Label nombreEx = new Label("*  "+ ex);
              nombreEx.setFont(Font.font(15));
              exam.getChildren().addAll(nombreEx);
              
              
              }
              exam.setSpacing(10);
              
              subcontenedor.getChildren().addAll(info,exam);
              subcontenedor.setSpacing(50);
              subcontenedor.setAlignment(Pos.CENTER);
              subcontenedor.setLayoutX(60);
              ContenedroGeneral.getChildren().add(subcontenedor);
              ContenedroGeneral.setSpacing(20);

          }
      } 
       
    
    
    @FXML
    private void BackAntecedentes(Event event) throws IOException{
       
        
    Parent BackMenuParent = FXMLLoader.load(getClass().getResource("/Pantallas/PacienteAntecedentesFXML.fxml"));
    Scene  BackMenuScene = new Scene(BackMenuParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackMenuScene);
        window.show();
    }

    
    
}
