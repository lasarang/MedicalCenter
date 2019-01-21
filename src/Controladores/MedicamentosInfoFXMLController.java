package Controladores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import ClasesAuxiliares.Producto;
import ClasesAuxiliares.ProductoDAOImpl;
import static Controladores.MedicamentosFXMLController.medicinas;
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
import javafx.scene.control.CheckBox;
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
public class MedicamentosInfoFXMLController implements Initializable {

    @FXML
    private ImageView ImgATRAS;
    @FXML
    private Label lbltitulo1;
    @FXML
    private Label lblTtiulo2;
    //private TableView<> TABLEDatos;
    @FXML
    private Button btConsulta;
    @FXML
    private Label lblTituloCentral;
    @FXML
    private Label lblTitulo3;
    @FXML
    private TableColumn<Producto, String> ColmMed;
    @FXML
    private TableColumn<Producto, String> ColmPre;
    @FXML
    private TableColumn<Producto, Integer> ColmDis;
    @FXML
    private TableColumn<Producto, String> ColmLab;
    @FXML
    private TableColumn<Producto, CheckBox> ColmSelecionado;
    
    private ProductoDAOImpl producto;
    
    private ObservableList<Producto> lista  ;
    @FXML
    private Button btnAgregar;
    @FXML
    private TableView<Producto> TblDatos;
    
    
    
    //////////////   Recetas     //////////////
    
    public static ArrayList<Producto> Receta1 = new ArrayList<>() ;
    
    public static ArrayList<Producto> Receta2 = new ArrayList<>();
    
    public static ArrayList<Producto> Receta3 = new ArrayList<>();
    
    @FXML
    private Button btnAgregar1;
    @FXML
    private Button btnAgregar2;
    
    //////////////   Recetas     //////////////

    /**
     * Initializes the controller class.
     */
     
     @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println("beto EL perro");
        lista = FXCollections.observableArrayList();
        
        ColmMed.setCellValueFactory(new PropertyValueFactory("nombreComercial") );
        ColmPre.setCellValueFactory(  new PropertyValueFactory("presentacion") );
        ColmDis.setCellValueFactory( new PropertyValueFactory("cantidad") );
        ColmLab.setCellValueFactory( new PropertyValueFactory("laboratorio") );
        ColmSelecionado.setCellValueFactory( new PropertyValueFactory("seleccion") );
        

        
        CrearTable(lista);
        TblDatos.setItems(lista);

        
    }

     @FXML
    private void Regresar(Event event) throws IOException{
       
        
    Parent BackMenuParent = FXMLLoader.load(getClass().getResource("/Pantallas/MedicamentosFXML.fxml"));
    Scene  BackMenuScene = new Scene(BackMenuParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackMenuScene);
        window.show();
    }
    
    
     @FXML
    private void BackConsultaP5(ActionEvent event) throws IOException{
        
    Parent BackConsultaP5Parent = FXMLLoader.load(getClass().getResource("/Pantallas/ActividadFXML.fxml"));
    Scene  BackConsultaP5Scene = new Scene(BackConsultaP5Parent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackConsultaP5Scene);
        window.show();
    }

    private  void CrearTable(ObservableList<Producto> lista) {
         
         for(Producto p : medicinas){
             
             CheckBox selec = new  CheckBox();
             lista.add(new Producto(p.getIdProducto(),p.getCantidad(),p.getNombreComercial(),p.getPresentacion(),
                    p.getLaboratorio(),p.getPrecioUnitarioVenta(), selec ));
             
         }

    }
    

    @FXML
     private void Agregar(ActionEvent event){
         
          ObservableList<Producto> med  = FXCollections.observableArrayList();
         for(Producto m : lista){
             
             if( m.getSeleccion().isSelected()){
             med.add(m);
             Receta1.add(m);
             }   
             
         }
         
         lista.removeAll(med);

     }
     

      @FXML
     private void AgregarH2(ActionEvent event){
         
          ObservableList<Producto> med  = FXCollections.observableArrayList();
                    
         for(Producto m : lista){
             
             if(m.getSeleccion().isSelected()){
            
             med.add(m);
             Receta2.add(m);
             }    
             
         }
         
         lista.removeAll(med);
         
            
         
     }
      @FXML
     private void AgregarH3(ActionEvent event){
         
          ObservableList<Producto> med  = FXCollections.observableArrayList();
          
         for(Producto m : lista){
             
             if(m.getSeleccion().isSelected()){
             // lista.remove(m);
             med.add(m);
             Receta3.add(m);
             }      
             
         }
       
         lista.removeAll(med);
         
     }
    
   
    
    
    
    
    
    
}
