/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesAuxiliares.Diagnostico;
import static Controladores.InfoPacienteFXMLController.cedula;
import FamiliaOperaciones.ConsultaDAOImpl;
import FamiliaOperaciones.ConsultaMedica;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author juanjimenez
 */
public class PacienteAntecedentesFXMLController implements Initializable {

    @FXML
    private Label LblTitulo1;
    @FXML
    private ImageView ImgBack;
    @FXML
    private Label LblTitulo2;
    @FXML
    private Label LblTitulo3;
    @FXML
    private Label LblTtiuloCentral;
    @FXML
    private Button btExamenes;
    
    @FXML
    private TableColumn<Diagnostico, String> ColumPatologia;
    @FXML
    private TableColumn<Diagnostico, String> ColumnAnte;
    @FXML
    private TableColumn<Diagnostico, String> Cie10;
    @FXML
    private TableView<Diagnostico> tableDatos;
    
    
    
    //private String CedulaPaciente = "";
    
  private ObservableList<Diagnostico> lista;
    
   private ConsultaDAOImpl pdaoConsulta = new ConsultaDAOImpl();
   
    private ArrayList<Diagnostico> diag = new ArrayList<>();
    
    public static  ArrayList<ConsultaMedica> consultaMed = new ArrayList<>();
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     * @throws java.io.IOException
     * @throws java.lang.Exception
     * @throws net.thegreshams.firebase4j.error.FirebaseException
     * @throws org.codehaus.jackson.JsonParseException
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        // TODO
        lista = FXCollections.observableArrayList();
        //Patoligia.setCellValueFactory(new PropertyValueFactory("Patologia") );
        ColumPatologia.setCellValueFactory(new PropertyValueFactory<>("Patologia")); 
        ColumnAnte.setCellValueFactory(new PropertyValueFactory<>("Antecedente")); 
        Cie10.setCellValueFactory(new PropertyValueFactory<>("Cie10"));
        
        CrearTable(lista);
        tableDatos.setItems(lista);
    }
    

    
    
    
     private  void CrearTable(ObservableList<Diagnostico> lista) {
           
        try {
            System.out.println(cedula.toString());
            consultaMed = pdaoConsulta.readAllConsultasPaciente(cedula);
            System.out.println(consultaMed.toString());
            //System.out.println(pdaoConsulta.readAllConsultasPaciente("1782723923").toString() + " AAAAAAAAAAAA" );
             for(ConsultaMedica c : consultaMed){
                 for(Diagnostico dg : c.getDiagnosticos()){
                 lista.add(dg);
                 }
            }

        } catch (Exception ex) {
            System.out.println("No vale la lectura de diagnoticos");
        } 
        

    }
    
    
    
    
    @FXML
     private void VerExamenes(ActionEvent event) throws IOException{
       
        
    Parent ExamanesParent = FXMLLoader.load(getClass().getResource("/Pantallas/ExamanesLabFXML.fxml"));
    Scene  ExamenestesScene = new Scene(ExamanesParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(ExamenestesScene);
        window.show();
    }

   
    
    
    
    @FXML
    private void BackInfo(Event event) throws IOException, Exception{
       
        
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Pantallas/InfoPacienteFXML.fxml"));
    

    
    
    Parent BackMenuParent = (Parent)loader.load();
    Scene  BackMenuScene = new Scene(BackMenuParent);
          InfoPacienteFXMLController p = loader.getController();
                
                p.GetPaciente(cedula);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackMenuScene);
        window.show();
    }
    
    
    
    
    /* public ObservableList<String>  SetDatosTable(ArrayList<ConsultaMedica> consultas){
    
    ObservableList<String> Temporal = FXCollections.observableArrayList();
    
    System.out.println(consultas);
    for(ConsultaMedica cm : consultas){
    System.out.println("8");
    ArrayList<String[]> aFamiliares = cm.getDiagnosticos().get("Familiares");
    System.out.println(aFamiliares.toString());
    System.out.println("9");
    for(String[] antFa : aFamiliares ){
    System.out.println("10");
    Temporal.add(antFa[0]);
    Temporal.add("Familiar");
    Temporal.add(antFa[1]);
    
    }
    ArrayList<String[]> apersonales = cm.getDiagnosticos().get("Personales");
    for(String[] antPer : apersonales ){
    Temporal.add(antPer[0]);
    Temporal.add("Personal");
    Temporal.add(antPer[1]);
    
    }
    
    }
    
    return Temporal;
    
    
    
    
    }*/
    
}
