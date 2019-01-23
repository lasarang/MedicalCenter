/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesAuxiliares.Diagnostico;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanjimenez
 */
public class ConsultasPaso2FXMLController implements Initializable {

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
    private Label lblExamen;
    @FXML
    private Label lblProce;
    @FXML
    private TextArea txt2;
    @FXML
    private Label lblDiagnostico;
    
    @FXML
    private TableView<Diagnostico> TableDiagnostico;
    @FXML
    private TableColumn<Diagnostico, String> Patoligia;
    @FXML
    private TableColumn<Diagnostico, String> Antecedente;
    @FXML
    private TableColumn<Diagnostico, String> cie10;
    @FXML
    private TextArea txt1;
    @FXML
    private ImageView ImageMas;
    

    private ObservableList<Diagnostico> items;
    
    
    public static  ArrayList<Diagnostico> diagnosticos = new ArrayList<>();
    
//////////////pantalla emergente////////////////////
     private HBox horizonte;
     
     private VBox centro;
     private Scene NuevaEscena;
     private Stage stage ;
     private BorderPane contenedorP;
     private ComboBox<String> combo_categ  ;//= new ComboBox<>(opciones);;
     private TextField nbIn = new TextField();
     
     private  TextField DesIn = new TextField();
   ///////////////////van a la parte final y a la base /////////////////////////////
     
     
    // private  ArrayList<String> datos= new ArrayList<>();
     
     public static   String  ExFísico ;

     public static  String Procedimiento;
     
     
    @FXML
    private Button BtEliminar;
    @FXML
    private Label lblFaltanDatos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        items = FXCollections.observableArrayList();

        Patoligia.setCellValueFactory(new PropertyValueFactory("Patologia") );
        Antecedente.setCellValueFactory( new PropertyValueFactory("Antecedente") );
        cie10.setCellValueFactory( new PropertyValueFactory("Cie10") );
        //items.add(new  Diagnostico("sangrado","familiar" ,"1010"));
        CrearTable(items);
        TableDiagnostico.setItems(items);
       
        
        // TODO
    }    
    
    
    public void SetTextos(String t1 , String t2){
        txt1.setText(t1);
        txt2.setText(t2);
    }
    
    
    
       @FXML
    private void BackPaso1(Event event) throws IOException{
       
        
    Parent BackPaso1Parent = FXMLLoader.load(getClass().getResource("/Pantallas/ConsultaPaso1FXML.fxml"));
    Scene  BackPaso1Scene = new Scene(BackPaso1Parent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackPaso1Scene);
        window.show();
    }
    
    @FXML
    private void ConsultasP3(ActionEvent event) throws IOException{
        
        if( txt1.getText().equals("") ||  txt2.getText().equals("")){
            lblFaltanDatos.setVisible(true);
            
        }else{
             FXMLLoader loader =new  FXMLLoader(getClass().getResource("/Pantallas/ConsultasP3FXML.fxml"));
             
             Parent ConsultasP3Parent = (Parent)loader.load();
             
             ConsultasP3FXMLController p = loader.getController();
             ExFísico = txt1.getText();
             Procedimiento =  txt2.getText();
             
             /*  p.setDatos(datos);
             System.out.println(datos.toString());*/
            
             
            Scene  ConsultasP3Scene = new Scene(ConsultasP3Parent);
    
            //aqui nos da la infomarcion del stage
                Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
                window.setScene(ConsultasP3Scene );
                window.show();
            
        }
        
       
        
   
        
    }
    
    
      private  void CrearTable(ObservableList<Diagnostico> lista) {
       
           lista.addAll(diagnosticos);
       
         //ObservableList<Producto> ProWcheck =FXCollections.observableArrayList();
         //ObservableList<Producto> Pro =FXCollections.observableArrayList();
        

    }
      
      
    @FXML
      private void DeleteRows(ActionEvent evento){
         
         
         TableDiagnostico.getItems().removeAll(TableDiagnostico.getSelectionModel().getSelectedItem());//  
         diagnosticos.clear();
          System.out.println(diagnosticos.toString());
         diagnosticos.addAll(items);
         System.out.println(diagnosticos.toString());

      }
      
      
    
    @FXML
      private void nuevaVentana(Event evento) {
            
         contenedorP = new BorderPane();
       contenedorP = new BorderPane();
       contenedorP.setTop(topBorder());
       contenedorP.setBottom(BottonBorder());
       contenedorP.setCenter(crearCentro());
       
        stage = new Stage();
     
        Scene scene = new Scene(contenedorP,300, 350);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Agregar Diagnostico");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
      
     private HBox topBorder(){   // crea los elementos del top de la pagina 
        HBox  topPagina = new HBox();  
        Label  tituloP= new Label("Nuevo Diagnostico");
        tituloP.setAlignment(Pos.CENTER);
        //tituloP.setMaxSize(17, 17);
      // creando el borde superior
      
       tituloP.setPadding(new Insets(5,5,5,5));
       
       
        topPagina.setAlignment(Pos.CENTER);
        topPagina.getChildren().add(tituloP); 
        
        return topPagina;
    }
     
       public Pane crearCentro(){
       centro = new VBox();
       
       HBox texto1 = new HBox();
       HBox texto2 = new HBox();
       HBox texto3 = new HBox();

       Label nb = new Label("Patología"); 
       nbIn  = new TextField();
       texto1.getChildren().addAll(nb,nbIn);
      
      
      
       Label Pr = new Label ("Tipo de Antecedentes");
       
       ObservableList<String> opciones = FXCollections.observableArrayList();
       opciones.add("Personal");
       opciones.add("Familiar");
       
        //LUEGO DE ESTO CREAR UN COMBO BOX 
       combo_categ = new ComboBox<>(opciones); 
    

        texto2.getChildren().addAll(Pr,combo_categ);

       Label Des = new Label ("CIE10");
       
       DesIn = new TextField();
       
       DesIn.setMaxSize(100,20);
       
       texto3.getChildren().addAll(Des,DesIn);
        texto1.setSpacing(20);
        texto2.setSpacing(20);
        texto3.setSpacing(20);
        
       
       centro.getChildren().addAll(texto1,texto2,texto3);
       centro.setSpacing(80);
       centro.setPadding(new Insets(20));
       centro.setAlignment(Pos.CENTER);
           
       return centro;
   }
       
       
       public HBox BottonBorder(){
         
         horizonte = new HBox();
         //         botones
       // creacion de botones y posicionamiento
       Button Guardar = new Button("Guardar");
       Guardar.setAlignment(Pos.BOTTOM_LEFT); 
       
       if( !(nbIn.getText()).equals(" ")  && !(DesIn.getText()).equals(" ")     
                ){ //!(combo_categ.getValue()).equals(" ")
           //System.out.println(nbIn.getText());
           //
                 Guardar.setOnMouseClicked(EventoG ->{
                  
                     //System.out.println(combo_categ.getValue());
                diagnosticos.add(new  Diagnostico(nbIn.getText(),combo_categ.getValue() ,DesIn.getText()));
                items.add(new  Diagnostico(nbIn.getText(),combo_categ.getValue() ,DesIn.getText()));
                    // System.out.println(diagnosticos.toString());
                stage.close();
      
                 });
       }
       Button Cancelar = new Button("Cancelar");
       Cancelar.setCancelButton(true);
       Cancelar.setAlignment(Pos.BOTTOM_RIGHT);
       Cancelar.setOnMouseClicked(Evento2->{
           stage.close();
       });
       
       // anadir al HBox horizonte que sera la parte inferior del BorderPane
       horizonte.getChildren().addAll(Guardar,Cancelar);
       contenedorP.setPrefSize(400, 400);  // le das un tamano minimo al borderpane
       // aqui se setea el margen o distancia entre los botones cancelar y guardar  y se agrega el Hbox al borderPane
       HBox.setMargin(Guardar,new Insets(0,0,10,10) );
       HBox.setMargin(Cancelar,new Insets(0,0,10,10) );
       horizonte.setAlignment(Pos.CENTER);
       
         return horizonte;
     }
}
