/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesAuxiliares.Producto;
import ClasesAuxiliares.ProductoDAOImpl;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanjimenez
 */
public class MedicamentosFXMLController implements Initializable {

    @FXML
    private ImageView ImAtras;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lblSubT;
    @FXML
    private ImageView ImgPil;
    @FXML
    private Label lblSearch;
    @FXML
    private ComboBox<String> Combo;
    @FXML
    private Label lblError;
    @FXML
    private AnchorPane root;
    @FXML
    private Button btnDetalles;
    
    private ObservableList<String> items = FXCollections.observableArrayList();
    @FXML
    private TextField txfNmed;
    @FXML
    private TextField txfNlab;
    
    public static ArrayList<Producto> medicinas = new ArrayList<>();
    
    private ProductoDAOImpl producto = new ProductoDAOImpl();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        items.add("Nombre");
        items.add("Nombre y Lab");
        
        Combo.setItems(items);
        Combo.getSelectionModel().selectFirst();
        txfNmed.setVisible(true);
        
        
        
        // TODO
    }    
    
      @FXML
    private void MoreInfo(ActionEvent event) throws IOException, Exception{

    if(!txfNmed.getText().equals("") || !txfNlab.getText().endsWith(""))  {  
         
         if(txfNmed.isVisible() && !txfNlab.isVisible()){
             System.out.println("beto");
             
             medicinas = producto.readNombre(txfNmed.getText());
             
         }else if(txfNmed.isVisible() && txfNlab.isVisible()){
             System.out.println("betox");
             medicinas = producto.readNombreLab(txfNmed.getText(), txfNlab.getText());
            
            // System.out.println(medicinas.toString());
         }
         
       FXMLLoader loader = new  FXMLLoader(getClass().getResource("/Pantallas/MedicamentosInfoFXML.fxml"));
        
        Parent MoreInfo = (Parent)loader.load();
        Scene  MoreInfoScene = new Scene(MoreInfo);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(MoreInfoScene);
        window.show();
    }
    
    }
    
    @FXML
    private void Regresar(Event event) throws IOException{
       
        
    Parent BackMenuParent = FXMLLoader.load(getClass().getResource("/Pantallas/ActividadFXML.fxml"));
    Scene  BackMenuScene = new Scene(BackMenuParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackMenuScene);
        window.show();
    }

    @FXML
    private void Seleccion(ActionEvent event){
        //ComboBox<String> combo
        
        if(Combo.getValue().equals("Nombre")){
            
            txfNmed.setVisible(true);
            txfNlab.setVisible(false);
        }if(Combo.getValue().equals("Nombre y Lab")){
            txfNmed.setVisible(true);
            txfNlab.setVisible(true);
        }
        
    }
    

    
}
