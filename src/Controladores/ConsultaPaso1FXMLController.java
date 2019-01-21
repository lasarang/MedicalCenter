/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanjimenez
 */
public class ConsultaPaso1FXMLController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Label LblTitulo3;
    @FXML
    private Label LblTitulo2;
    @FXML
    private ImageView imgaeBack;
    @FXML
    private Label titulo;
    @FXML
    private Button BtContinuar;
    @FXML
    private RadioButton btnEmergia;
    @FXML
    private ImageView image1;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;
    @FXML
    private Label titulo1;
    @FXML
    private Label lbl4;
    @FXML
    private Label lbl5;
    @FXML
    private Label lbl7;
    @FXML
    private Label lbl8;
    @FXML
    private Label lbl9;
    @FXML
    private Label lbl10;
    @FXML
    private TextField txt1;
    @FXML
    private TextField txt2;
    @FXML
    private TextArea Txtfiel1;
    @FXML
    private TextField txtSistolica;
    @FXML
    private TextField txtDiastolica;
    @FXML
    private Label lbPulso;
    @FXML
    private Label lblSistolica;
    @FXML
    private Label lblDiastolica;
    @FXML
    private Label lblFaltanDtatos;
    @FXML
    private TextField TxtFrecuenciaCard;
    @FXML
    private TextField TxtSaturacionOxi;
    @FXML
    private TextField TxtFrecuenciaRes;
    @FXML
    private TextField TxtTemperatura;
    @FXML
    private TextField TxtTalla;
    @FXML
    private TextField TxtPeso;

    
    public static ArrayList<String> datos = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
        @FXML
    private void ConsultasP2(ActionEvent event) throws IOException{
      
    
    if(btnEmergia.getText().equals("")  || txt1.equals("")  || txt2.equals("") || Txtfiel1.equals("") || TxtFrecuenciaCard.equals("") 
            || TxtFrecuenciaRes.equals("") || 
            TxtSaturacionOxi.getText().equals("") ||  TxtTemperatura.getText().equals("") 
            ||txtSistolica.getText().equals("")||TxtTalla.getText().equals("")||TxtPeso.getText().equals("")||
            txtDiastolica.getText().equals("") ){
        
                lblFaltanDtatos.setVisible(true);
                
            }else{
        
                FXMLLoader loader =  new FXMLLoader(getClass().getResource("/Pantallas/ConsultasPaso2FXML.fxml"));
                
                Parent ConsultasP2Parent = (Parent)loader.load();

                 ConsultasPaso2FXMLController p = loader.getController();
             if(btnEmergia.isSelected()){
                 datos.add(btnEmergia.getText());datos.add(txt1.getText());datos.add(txt2.getText());
                 datos.add(Txtfiel1.getText());datos.add(TxtFrecuenciaCard.getText());datos.add(TxtFrecuenciaRes.getText());
                 datos.add(TxtSaturacionOxi.getText());datos.add(TxtTemperatura.getText());datos.add(txtSistolica.getText());
                 datos.add(TxtTalla.getText());datos.add(TxtPeso.getText());datos.add(txtDiastolica.getText());
                                         
                 
             }else{
                 
                 datos.add("No Emergencia");datos.add(txt1.getText());datos.add(txt2.getText());
                 datos.add(Txtfiel1.getText());datos.add(TxtFrecuenciaCard.getText());datos.add(TxtFrecuenciaRes.getText());
                 datos.add(TxtSaturacionOxi.getText());datos.add(TxtTemperatura.getText());datos.add(txtSistolica.getText());
                 datos.add(TxtTalla.getText());datos.add(TxtPeso.getText());datos.add(txtDiastolica.getText());
                
             }
             
             
             
              Scene  ConsultasP2Scene = new Scene(ConsultasP2Parent);

            //aqui nos da la infomarcion del stage
                Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
                window.setScene(ConsultasP2Scene );
                window.show();
    
    }
   
       
    }
    
}
