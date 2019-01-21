/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import ClasesAuxiliares.Diagnostico;
import ClasesAuxiliares.Horario;
import ClasesAuxiliares.Producto;
import static Controladores.ConsultaP4FXMLController.indicaciones;
import static Controladores.ConsultaP4FXMLController.medicacion;
import static Controladores.MedicamentosFXMLController.medicinas;
import static Controladores.MedicamentosInfoFXMLController.Receta1;
import static Controladores.MedicamentosInfoFXMLController.Receta2;
import static Controladores.MedicamentosInfoFXMLController.Receta3;
import Extra.Validate;
import FamiliaAcciones.AdmiMedicina;
import FamiliaAcciones.MedicionGlucosa;
import FamiliaAcciones.MedicionPA;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanjimenez
 */
public class ActividadFXMLController implements Initializable {

    @FXML
    private Tab tab1;
    @FXML
    private TextField txtHora;
    @FXML
    private Label lblHora;
    @FXML
    private Label lblCondicion;
    @FXML
    private ComboBox<String> txtCombo;
    @FXML
    private RadioButton btnPresion;
    @FXML
    private RadioButton BtnGlucosa;
    @FXML
    private Label lblMediciones;
    @FXML
    private Label lblMed;
    @FXML
    private ImageView imageGlucosa;
    @FXML
    private ImageView ImagecARDIACO;
    @FXML
    private TableView<Producto> tableReceta;
    @FXML
    private TableColumn<Producto, String> ColumMed;
    @FXML
    private TableColumn<Producto, String> ColumPres;
    @FXML
    private TableColumn<Producto, Integer> ColumCantidad;
    @FXML
    private ImageView imageMas;
    @FXML
    private Tab tab2;
    @FXML
    private TableView<Producto> tableReceta1;
    @FXML
    private TableColumn<Producto, String> ColumMed1;
    @FXML
    private TableColumn<Producto, String> ColumPres1;
    @FXML
    private TableColumn<Producto, Integer> ColumCantidad1;
    @FXML
    private Tab tab3;
    @FXML
    private TableView<Producto> tableReceta2;
    @FXML
    private TableColumn<Producto, String> ColumMed2;
    @FXML
    private TableColumn<Producto, String> ColumPres2;
    @FXML
    private TableColumn<Producto, Integer> ColumCantidad2;
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
    private AnchorPane imageGlucosa2;
    @FXML
    private TextField txtHora2;
    @FXML
    private Label lblHora2;
    @FXML
    private Label lblCondicion2;
    @FXML
    private ComboBox<String> combo2;
    @FXML
    private RadioButton radio2Presion;
    @FXML
    private RadioButton radio2Glucosa;
    @FXML
    private Label lblMedciones2;
    @FXML
    private Label subtitulo2;
    @FXML
    private ImageView imagePresion2;
    @FXML
    private ImageView imageMas2;
    @FXML
    private TextField txtHora3;
    @FXML
    private Label lblhora3;
    @FXML
    private Label lblCond3;
    @FXML
    private ComboBox<String> combo3;
    @FXML
    private RadioButton radioPresio3;
    @FXML
    private RadioButton radioGlucosa3;
    @FXML
    private Label lblMedicacion3;
    @FXML
    private Label subtituloMed3;
    @FXML
    private ImageView imageMas3;

    
    private ObservableList<Producto> lista  ;
    
    private ObservableList<Producto> lista2  ;
    
    private ObservableList<Producto> lista3  ;
    
    
    @FXML
    private TableColumn<Producto, Double> Precio;
    @FXML
    private TableColumn<Producto, Double> Precio1;
    @FXML
    private TableColumn<Producto, Double> Precio2;
    
    private ObservableList<String> items = FXCollections.observableArrayList();
    ///////////////////horarios 
    
    
    public static ArrayList<Horario> horarios = new ArrayList<>();
    
    private  Horario horario1 = new Horario();
    private  Horario horario2 = new Horario();
    private Horario horario3 = new Horario();

    
    @FXML
    private Label lblFaltanDatos;
   
    
    ////DATOS QUE VAN A LA SIGUIENTE PAGINA ///// 
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // listas de table 
        lista = FXCollections.observableArrayList();
        lista2 = FXCollections.observableArrayList();
        lista3 = FXCollections.observableArrayList();
        
        
        
        ///combo 1///
        items.add("AntesComida");
        items.add("DespuesComida");
        
        txtCombo.setItems(items);
        txtCombo.getSelectionModel().selectFirst();
        ////combo 2////
        
        combo2.setItems(items);
        combo2.getSelectionModel().selectFirst();
        
        ////combo 3////
        
        combo3.setItems(items);
        combo3.getSelectionModel().selectFirst();      
        
        
        ColumMed.setCellValueFactory(new PropertyValueFactory("nombreComercial") );
        ColumPres.setCellValueFactory(  new PropertyValueFactory("presentacion") );
        ColumCantidad.setCellValueFactory( new PropertyValueFactory("cantidad") );
        Precio.setCellValueFactory( new PropertyValueFactory("precioUnitarioVenta") );
        
        
        ColumMed1.setCellValueFactory(new PropertyValueFactory("nombreComercial") );
        ColumPres1.setCellValueFactory(  new PropertyValueFactory("presentacion") );
        ColumCantidad1.setCellValueFactory( new PropertyValueFactory("cantidad") );
        Precio1.setCellValueFactory( new PropertyValueFactory("precioUnitarioVenta") );
        
         ColumMed2.setCellValueFactory(new PropertyValueFactory("nombreComercial") );
        ColumPres2.setCellValueFactory(  new PropertyValueFactory("presentacion") );
        ColumCantidad2.setCellValueFactory( new PropertyValueFactory("cantidad") );
        Precio2.setCellValueFactory( new PropertyValueFactory("precioUnitarioVenta") );
        
 ////Recetas contiene los medicamentos para ese horario y receta especifica ////
        CargarDatosR1(Receta1);  
        CargarDatosR2(Receta2);
        CargarDatosR3(Receta3);
        
       tableReceta.setItems(lista);
       tableReceta1.setItems(lista2);
       tableReceta2.setItems(lista3);
        
        
    }    

    
    private void AgregarHorario() throws Exception{
    
    ////creacion de horario para pruebas los positivos estan arriba /////
     //Horario horario1 = new Horario();
     //Horario horario2 = new Horario();
     //Horario horario3 = new Horario();
       
            
    ////set recetas en admiMedicina/////    
    AdmiMedicina am = new AdmiMedicina(); 
    am.setMedicamentos(Receta1);
    ///////////////////////
     AdmiMedicina am1 = new AdmiMedicina(); 
    am1.setMedicamentos(Receta2);
    //////////////
     AdmiMedicina am2 = new AdmiMedicina(); 
    am2.setMedicamentos(Receta3);
    ///////////////
    
    ////////////set hora y condicon a horario/////
    horario1.setHora(LocalTime.parse(txtHora.getText()));
    horario1.setCondicionComida(txtCombo.getValue());
    ////////horario2///////////////
    horario2.setHora( LocalTime.parse(txtHora2.getText()));
    horario2.setCondicionComida(combo2.getValue());
    ///horario 3////////////////
    horario3.setHora(LocalTime.parse(txtHora3.getText()));
    horario3.setCondicionComida(combo3.getValue());
    ////set acciones////
    ArrayList ac1 = new ArrayList();
    ArrayList ac2 = new ArrayList();
    ArrayList ac3 = new ArrayList();
   
 
   ///////////////ac2//// 

    ac1.add(am);
    ac2.add(am1);
    ///////ac3/////////
    ac3.add(am2);
    ////set mediciones horario1////////
    
    if(btnPresion.isSelected() && BtnGlucosa.isSelected()){
            MedicionPA mpa1 = new MedicionPA();
           
            MedicionGlucosa mg1 = new MedicionGlucosa();  
        ac1.add( mpa1); ac1.add( mg1);

    }else if(btnPresion.isSelected() && !BtnGlucosa.isSelected()){
          
            MedicionPA mpa1 = new MedicionPA();
          ac1.add(mpa1); 
  
    }else if(!btnPresion.isSelected() && BtnGlucosa.isSelected()){

            MedicionGlucosa mg1 = new MedicionGlucosa();
   
         ac1.add(mg1);
    }
    ////set mediciones horario2////////
    if(radio2Presion.isSelected() && radio2Glucosa.isSelected()){
            MedicionPA mpa2 = new MedicionPA();
            MedicionGlucosa mg2 = new MedicionGlucosa();
        ac2.add( mpa2); ac2.add( mg2);
        
    }else if(radio2Presion.isSelected() && !radio2Glucosa.isSelected()){
            MedicionPA mpa2 = new MedicionPA();
            ac2.add(mpa2); 
        
    }else if(!radio2Presion.isSelected() && radio2Glucosa.isSelected()){

            MedicionGlucosa mg2 = new MedicionGlucosa();  
            ac2.add(mg2);
    }
    

    ////set mediciones horario3////////
    if(radioPresio3.isSelected() && radioGlucosa3.isSelected()){
            MedicionPA mpa3 = new MedicionPA();
            MedicionGlucosa mg3 = new MedicionGlucosa();
        ac3.add( mpa3); ac3.add( mg3);
    }else if(radioPresio3.isSelected() && !radioGlucosa3.isSelected()){

            MedicionPA mpa3 = new MedicionPA();
            ac3.add( mpa3);
    }else if(!radioPresio3.isSelected() && radioGlucosa3.isSelected()){
            MedicionGlucosa mg3 = new MedicionGlucosa();
              ac3.add( mg3);
    }
   /////////////////////////////////////////
    horario1.setAcciones(ac1);
    horario2.setAcciones(ac2);
    horario3.setAcciones(ac3);
    
    
     horarios.add(horario1);
    
    if(!horario2.getHora().equals("")){
        horarios.add(horario2);
    }if(!horario2.getHora().equals("")){
        horarios.add(horario3);
    }

   
        
    }
    
   
  
    
     private void CargarDatosR1(ArrayList<Producto> medicamentos) {
        
        for(Producto p : medicamentos ){
            lista.add( new Producto(p .getIdProducto(),p.getCantidad(),p.getNombreComercial(),p.getPresentacion(),
                   p.getLaboratorio(),p.getPrecioUnitarioVenta()));
            
        }
       
        
    }
    
      private void CargarDatosR2(ArrayList<Producto> medicamentos) {
        
        for(Producto p : medicamentos ){
            lista2.add( new Producto(p.getIdProducto(),p.getCantidad(),p.getNombreComercial(),p.getPresentacion(),
                    p.getLaboratorio(),p.getPrecioUnitarioVenta()));
            
        }
       
        
    }

    private void CargarDatosR3(ArrayList<Producto> medicamentos) {
        
        for(Producto p : medicamentos ){
            lista3.add( new Producto(p.getIdProducto(),p.getCantidad(),p.getNombreComercial(),p.getPresentacion(),
                    p.getLaboratorio(),p.getPrecioUnitarioVenta()));
            
        }
       
        
    }
    
    
    
           @FXML
    private void BackPaso4(Event event) throws IOException{
       
        
    FXMLLoader loader= new FXMLLoader(getClass().getResource("/Pantallas/ConsultaP4FXML.fxml"));
    Parent BackPaso4 = (Parent)loader.load();
    
    ConsultaP4FXMLController p4 = loader.getController();
    p4.setTextos(medicacion, indicaciones);
    
    Scene  BackPaso4Scene = new Scene(BackPaso4);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackPaso4Scene );
        window.show();
    }
    
     @FXML
    private void ConsultaP6(ActionEvent event) throws IOException, Exception{
       
        if(txtHora.getText().equals("") || btnPresion.getText().equals("") ||BtnGlucosa.getText().equals("")){
            
            lblFaltanDatos.setVisible(true);
            
            
        }else{
            
            FXMLLoader  loader = new FXMLLoader(getClass().getResource("/Pantallas/ProximaConsultaFXML.fxml"));
            
            Parent ConsultaP6Parent = (Parent)loader.load();
            ProximaConsultaFXMLController p6 = loader.getController();
            AgregarHorario();
            
             Scene  ConsultaP6Scene = new Scene(ConsultaP6Parent);
    
           //aqui nos da la infomarcion del stage
           Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
           window.setScene(ConsultaP6Scene  );
           window.show();
    }
        
    
    }
    
    
    @FXML
    private void VerMed(Event event) throws IOException{
       
        
    Parent VerMedParent = FXMLLoader.load(getClass().getResource("/Pantallas/MedicamentosFXML.fxml"));
    Scene  VerMedScene = new Scene(VerMedParent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(VerMedScene  );
        window.show();
    }
    
    
    
    
    
}
