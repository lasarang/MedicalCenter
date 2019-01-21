/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesAuxiliares.Horario;
import static Controladores.PacienteAntecedentesFXMLController.consultaMed;
import FamiliaAcciones.Accion;
import FamiliaAcciones.MedicionGlucosa;
import FamiliaAcciones.MedicionPA;
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
        MostrarMediciones(consultaMed);
    }

    @FXML
    private void VerConsultas(ActionEvent event) throws IOException {

        Parent ActividadesParent = FXMLLoader.load(getClass().getResource("/Pantallas/PConsultasFXML.fxml"));
        Scene ActividadesScene = new Scene(ActividadesParent);

        //aqui nos da la infomarcion del stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ActividadesScene);
        window.show();
    }

    private void MostrarMediciones(ArrayList<ConsultaMedica> consulta) {

        for (ConsultaMedica cm : consulta) {

            for (Horario ho : (cm.getTratamiento()).getHorarios()) {
                System.out.println(ho.toString());
                for (Accion obj : ho.getAcciones()) {
                    System.out.println("holaa");
                    HBox mediciones = new HBox();
                    HBox medicionePa = new HBox();
                    mediciones.setSpacing(20);
                    medicionePa.setSpacing(20);
                    System.out.println(obj.toString());
                    if (obj instanceof MedicionGlucosa) {
                        System.out.println("ponce");
                        Label Hora = new Label(ho.getHora().toString());
                        Label Cond = new Label(ho.getCondicionComida());
                        mediciones.getChildren().addAll(Hora, Cond);
                        ContentGlucosa.getChildren().add(mediciones);
                    } else if (obj instanceof MedicionPA) {
                        Label Hora = new Label(ho.getHora().toString());
                        Label Cond = new Label(ho.getCondicionComida());

                        medicionePa.getChildren().addAll(Hora, Cond);
                        ContentPresion.getChildren().add(medicionePa);

                    }

                }

            }

        }

    }

    @FXML
    private void BackTratamiento(Event event) throws IOException {

        Parent BackMenuParent = FXMLLoader.load(getClass().getResource("/Pantallas/PacienteTratamientoFXML.fxml"));
        Scene BackMenuScene = new Scene(BackMenuParent);

        //aqui nos da la infomarcion del stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(BackMenuScene);
        window.show();
    }

}
