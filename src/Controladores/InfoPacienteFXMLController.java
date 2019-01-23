/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import FamiliaOperaciones.ConsultaDAOImpl;
import FamiliaPersonas.Paciente;
import FamiliaPersonas.PacienteDAOImpl;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanjimenez
 */
public class InfoPacienteFXMLController implements Initializable {

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
    private Label LblNombre;
    @FXML
    private Label lblFecha;
    @FXML
    private Label LblTelefono;
    @FXML
    private Label lblEmail;
    @FXML
    private Label LblGenero;
    @FXML
    private Label LblCiudad;
    @FXML
    private Label LblCi;
    @FXML
    private Label LblEstadoCivi;
    @FXML
    private Label LblDireccion;
    
    @FXML
    private Label LblName;
    @FXML
    private Label LblFechaNac;
    @FXML
    private Label LblGeneL;
    @FXML
    private Label LblCIiuL;
    @FXML
    private Label lblDirecL;
    @FXML
    private Label LblCedula;
    @FXML
    private Label LblEstadoL;
    @FXML
    private Button ButtonAntecedentes;
    @FXML
    private Label lblTele1;
    @FXML
    private Label lblTele2;
    @FXML
    private Label lblEmail1;
    @FXML
    private Label lblEmail2;
 
     private PacienteDAOImpl pdao = new PacienteDAOImpl();
     
     private ConsultaDAOImpl pdaoConsulta = new ConsultaDAOImpl();
     
     public  static String cedula = "";
    @FXML
    private ImageView imgaUser;
    @FXML
    private ImageView ImageHouse;
    @FXML
    private Label lblDatosPer;
    @FXML
    private Label lblDomicilio;
    @FXML
    private ImageView ImgaeContact;
    @FXML
    private Label lblContacto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
 
        
    }    
      @FXML
    private void VerAntecedentes(ActionEvent event) throws IOException, Exception {
       
        
        
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("/Pantallas/PacienteAntecedentesFXML.fxml"));
                
               

                Parent Menu = (Parent)loader.load();

                Scene  MenuScene = new Scene(Menu);

              
                Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
                window.setScene(MenuScene);
                window.show();
        
    }
    
    
    public void GetPaciente(String texto) throws Exception{
        
        cedula = texto;
        
        Paciente p =  pdao.read(texto);
        LblName.setText(p.getNombre());
        LblFechaNac.setText(p.getFechaNacimiento().toString());
        LblGeneL.setText(p.getGenero());
        
        LblCIiuL.setText(p.getDomicilios().get(0)[0]);
        lblDirecL.setText(p.getDomicilios().get(0)[1]);
        
        LblCedula.setText(p.getIdPersona());
        LblEstadoL.setText(p.getEstadoCivil());
        
        
                
        lblTele1.setText(p.getTelefonos().get(0));
        lblTele2.setText(p.getTelefonos().get(1));
        lblEmail1.setText(p.getCorreos().get(0));
        lblEmail2.setText(p.getCorreos().get(1));
       // LblTelefono.setText(p.get);
        
    }
    
    
    
    
    
     @FXML
    private void BackPaciente(Event event) throws IOException{
       
        
    Parent BackMenuParent = FXMLLoader.load(getClass().getResource("/Pantallas/PacientesFXML.fxml"));
    Scene  BackMenuScene = new Scene(BackMenuParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackMenuScene);
        window.show();
    }
}
