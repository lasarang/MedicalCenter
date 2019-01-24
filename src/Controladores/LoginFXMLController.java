/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Extra.Validate;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanjimenez
 */
public class LoginFXMLController implements Initializable {

    //Stage primaryStage;
    // desktopapp.DesktopApp.
    // Scene escena;
    @FXML
    private ImageView ImageFondo;
    @FXML
    private Label LbTitulo;
    @FXML
    private Button BtSignIn;
    @FXML
    private TextField TxCedula;
    @FXML
    private AnchorPane root;
    @FXML
    private PasswordField Contrasena;

    @FXML
    private Label lblIncorrectas;

    public static LocalDateTime inicio;

    public static int idMedico;
    @FXML
    private Label lblFaltanCampos;

    private Validate validar = new Validate();

    //private Validate valido;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /*    public void CargarPantalla() throws IOException{
    
    }*/
//    @FXML
//    public void handleButtonAction(ActionEvent event) throws IOException {
//        // lblIncorrectas.setVisible(true);
//
//       // if (!TxCedula.getText().equals("") && !Contrasena.getText().equals("")) {
//       
//       
//       FXMLLoader loader = new FXMLLoader(getClass().getResource("/Pantallas/MenuFXML.fxml"));
//
//                    Parent Menu = (Parent) loader.load();
//                    //System.out.println(inicio.toString());
//
//                    Scene MenuScene = new Scene(Menu);
//
//                    inicio = LocalDateTime.now();
//                    //idMedico = validar.esUsuarioMedico(TxCedula.getText(), Contrasena.getText());
//                    System.out.println(idMedico);
//                    MenuFXMLController p = loader.getController();
//
//
//
//                    //aqui nos da la infomarcion del stage
//                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                    window.setScene(MenuScene);
//                    window.show();
//            try {
//                System.out.println(TxCedula.getText() + Contrasena.getText());
//
//        //if (!TxCedula.getText().equals(" ") && !Contrasena.getText().equals(" ") ) {
////&& validar.esUsuarioMedico(TxCedula.getText(), Contrasena.getText()) > 0
//                    // System.out.println(idmed);
//                    
//
//                    /* } else {
//                    
//                    lblIncorrectas.setVisible(false);
//                    lblFaltanCampos.setVisible(true);
//                    
//                    }*/
//
//            } catch (Exception ex) {
//
//                lblIncorrectas.setVisible(true);
//                lblFaltanCampos.setVisible(false);
//            }
///////hasta aqui la validacion de la contrasena/////   
//
///*  } else {
//lblIncorrectas.setVisible(false);
//lblFaltanCampos.setVisible(true);
//
//}*/
//
//    }
    
    
    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException, Exception {
        // lblIncorrectas.setVisible(true);

        if (!TxCedula.getText().equals("") && !Contrasena.getText().equals("")) {

            System.out.println(TxCedula.getText() + Contrasena.getText());
            System.out.println("validacion "+validar.esUsuarioMedico(TxCedula.getText(), Contrasena.getText()));
            if (validar.esUsuarioMedico(TxCedula.getText(), Contrasena.getText())) {

                // System.out.println(idmed);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Pantallas/MenuFXML.fxml"));

                Parent Menu = (Parent) loader.load();
                //System.out.println(inicio.toString());

                Scene MenuScene = new Scene(Menu);

                inicio = LocalDateTime.now();

                MenuFXMLController p = loader.getController();

                p.Imprimir(TxCedula.getText());

                //aqui nos da la infomarcion del stage
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(MenuScene);
                window.show();

            } else {

                lblIncorrectas.setVisible(false);
                lblFaltanCampos.setVisible(true);

            }

/////hasta aqui la validacion de la contrasena/////   
        } else {
            lblIncorrectas.setVisible(false);
            lblFaltanCampos.setVisible(true);

        }

    }

}
