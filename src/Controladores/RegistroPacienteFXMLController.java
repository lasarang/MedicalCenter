/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Extra.Validate;
import FamiliaPersonas.Paciente;
import FamiliaPersonas.PacienteDAOImpl;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanjimenez
 */
public class RegistroPacienteFXMLController implements Initializable {
    
    final ToggleGroup group = new ToggleGroup();
    @FXML
    private Label titulo;
    @FXML
    private ImageView imgaeBack;
    @FXML
    private Label LblTitulo2;
    @FXML
    private Label LblTitulo3;
    @FXML
    private Label SubTitulo1;
    

    
    @FXML
    private Label LblNombre;
    @FXML
    private TextField txtNombre;
    @FXML
    private Label lblFechaNa;
    @FXML
    private ImageView imageCalendar;
    @FXML
    private Label LblTelefono;
   

    @FXML
    private Label lblGenero;
    @FXML
    private Label LblCity;
    @FXML
    private Label lblDireccion;
    @FXML
    private TextField txtCity;
    @FXML
    private TextArea TxtDireccion;
    @FXML
    private VBox vboxTel;
    @FXML
    private TextField txtTelef;
    @FXML
    private TextField txtTelef1;
    @FXML
    private RadioButton btnMasculino;
    @FXML
    private RadioButton btnFem;
    @FXML
    private DatePicker Fecha;
    @FXML
    private ScrollPane Scroll;
    @FXML
    private TextField txtEmail1;
    @FXML
    private TextField txtEmail;
    @FXML
    private Label lblCorreo;
    @FXML
    private AnchorPane inici;
    @FXML
    private Button BtRegistrar;
    @FXML
    private TextField txtOcupacion;
    @FXML
    private Label lblOcupacion;
    @FXML
    private ComboBox<String> comboCivil;
    @FXML
    private Label lblCivil;
    @FXML
    private ComboBox<String> comboSangre;
    @FXML
    private Label lblSangre;
    @FXML
    private TextField txtOcupacion2;
    @FXML
    private TextField txtOcupacion3;
    
    private PacienteDAOImpl paciente = new PacienteDAOImpl();
    
    private ObservableList<String> Tsangre = FXCollections.observableArrayList();
     
    private ObservableList<String> Ecivil = FXCollections.observableArrayList();
    
    final private Validate validar = new Validate();
    @FXML
    private Label lblFaltan;
    @FXML
    private Label lblCedula;
    @FXML
    private TextField txtCedula;
    
    private Paciente pa ;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Tsangre.add("A+");
        Tsangre.add("B+");
        Tsangre.add("B-");
        Tsangre.add("AB+");
        Tsangre.add("AB-");
        Tsangre.add("O+");
        Tsangre.add("O-");
        
        comboSangre.setItems(Tsangre);
        comboSangre.getSelectionModel().selectFirst();
        
        Ecivil.add("Casado");
        Ecivil.add("Soltero");
        Ecivil.add("Divorciado");
        Ecivil.add("Viudo");
        
        
        comboCivil.setItems(Ecivil);
        comboCivil.getSelectionModel().selectFirst();
                
                
        btnMasculino.setToggleGroup(group);
        btnFem.setToggleGroup(group);
      
       
    }    
    
     @FXML
    private void BackConsulta(Event event) throws IOException{
       
        
    Parent BackConsultaParent = FXMLLoader.load(getClass().getResource("/Pantallas/ConsultasFXML.fxml"));
    Scene  BackConsultaScene = new Scene(BackConsultaParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackConsultaScene );
        window.show();
    }
    
    
    
    private void Continuar (ActionEvent event) throws IOException{
    Parent ContinuarParent = FXMLLoader.load(getClass().getResource("/Pantallas/RegistroP2FXML.fxml"));
    Scene  ContinuarScene = new Scene(ContinuarParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(ContinuarScene );
        window.show();
    }
    
    @FXML
    private void Check(ActionEvent event){
 
    }
    
    
    @FXML
    private void Registrar (ActionEvent event) throws IOException{
        
        
        try {
            pa = new Paciente();
            String c1 = txtCedula.getText();
            String c2 = txtNombre.getText();
          
            String c5 = txtTelef1.getText();
            String c6 = txtEmail1.getText();
            String c7 = TxtDireccion.getText();
            String c8 = txtOcupacion.getText();
            
            if(c1.equals("")  || c2.equals("")  || c5.equals("") || c6.equals("") || c7.equals("") || c8.equals("")){
                lblFaltan.setVisible(true);
                System.out.println("beto1");
            }else{
                
                System.out.println("beto");
                     //creancion de arrys y llenado ////
            ArrayList<String> ocupaciones = new ArrayList<String>(), telefonos = new ArrayList<String>(), correos = new ArrayList<String>();
            ArrayList<String[]> domicilios = new ArrayList<String[]>();
            
            String[] s1 = {TxtDireccion.getText() ,txtCity.getText()};
            
            ocupaciones.add(txtOcupacion.getText());
            ocupaciones.add(txtOcupacion2.getText());
            ocupaciones.add(txtOcupacion3.getText());
            telefonos.add(txtTelef.getText());
            telefonos.add(txtTelef1.getText());
            correos.add(txtEmail1.getText());
            correos.add(txtEmail.getText());
            domicilios.add(s1);


          ////creacion de paciente con datos////  
           pa.setIdPersona(txtCedula.getText());
           pa.setNombre(txtNombre.getText());
           pa.setEstadoCivil(comboCivil.getValue());
           if(btnMasculino.isSelected()){
                pa.setGenero(btnMasculino.getText());
           }else{
               pa.setGenero( btnFem.getText());
           }
           
          
           pa.setFechaNacimiento(Fecha.getValue());
           pa.setGrupoSanguineo(comboSangre.getValue());
           pa.setCorreos(correos);
           pa.setDomicilios(domicilios);
           pa.setTelefonos(telefonos);
           pa.setOcupaciones(ocupaciones);
           pa.setTipo("Paciente");
           pa.setNroHistoria(Integer.parseInt(validar.ultimoId("Pacientes")) + 1); 
           paciente.create(pa);
           
          Parent RegistrarParent = FXMLLoader.load(getClass().getResource("/Pantallas/ConsultasFXML.fxml"));
          Scene  RegistrarScene = new Scene(RegistrarParent);

           //aqui nos da la infomarcion del stage
            Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
            window.setScene(RegistrarScene);
            window.show();
               
            }


        } catch (Exception ex) {
            
            
            Logger.getLogger(RegistroPacienteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    
        
        
    }
    
    
}
