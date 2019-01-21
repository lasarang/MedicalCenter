package Controladores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static Controladores.ConsultasP3FXMLController.Descripcion;
import static Controladores.ConsultasP3FXMLController.Lugar;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanjimenez
 */
public class ConsultaP4FXMLController implements Initializable {

    @FXML
    private Button BtContinuar;
    @FXML
    private Label titulo;
    @FXML
    private ImageView imgaeBack;
    @FXML
    private Label LblTitulo2;
    @FXML
    private Label LblTitulo3;
    @FXML
    private Label lblFechaInicio;
    @FXML
    private Label lblFechaFin;
    @FXML
    private DatePicker Calendario1;
    @FXML
    private DatePicker Calendario2;
    @FXML
    private Label lblMed;
    @FXML
    private Label lblIndic;
    @FXML
    private TextArea txtMed;
    @FXML
    private TextArea txtInd;
    @FXML
    private Label lblFaltanDatos;
    
    ////DATOS QUE VAN A LA SIGUIENTE PAGINA ///// 

    public static String medicacion;
    
    public static String indicaciones;
    
    public static LocalDate Finicio;
    
    public static LocalDate Ffin;
    
   
            
    ////DATOS QUE VAN A LA SIGUIENTE PAGINA ///// 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //txtMed.setText(medicacion);
        //txtInd.setText(indicaciones);
        
    }    
    
    public void setTextos(String med, String ind){
        txtMed.setText(med);
        txtInd.setText(ind);
        
        
    }
    

   
    
    

    
    @FXML
    private void BackPaso3(Event event) throws IOException{
       
        
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Pantallas/ConsultasP3FXML.fxml"));
    Parent BackPaso3 = (Parent)loader.load();
    
    ConsultasP3FXMLController p3 = loader.getController();
    
    p3.SetTextos(Lugar, Descripcion);
   
   
    Scene  BackPaso3Scene = new Scene(BackPaso3);
    
    
    
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackPaso3Scene );
        window.show();
    }
    
    @FXML
    private void ConsultaP5(ActionEvent event) throws IOException{
        
        ///
            
    if(txtMed.getText().equals("") || txtInd.getText().equals("")|| Calendario1.getValue() == null 
            || Calendario2.getValue() == null  ){
        
            lblFaltanDatos.setVisible(true);
            
        }else{
        
        medicacion =txtMed.getText();
        indicaciones = txtInd.getText();
        
        Finicio = Calendario1.getValue();
        Ffin = Calendario2.getValue();
        
        
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/Pantallas/ActividadFXML.fxml"));
        
        Parent ConsultaP5Parent = (Parent)loader.load();
        
        ActividadFXMLController p5 = loader.getController();
        
       // p5.setDatos(datos);
        //System.out.println(datos.toString() + " pantalla4");
        //p5.setExamenes(examenes);  
        
        Scene  ConsultaP5Scene = new Scene(ConsultaP5Parent);
    
        
        //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(ConsultaP5Scene  );
        window.show();
        
        
    }
     
 
    }

   
    
     
    
}
