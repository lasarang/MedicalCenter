/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import static Controladores.ConsultasPaso2FXMLController.ExFísico;
import static Controladores.ConsultasPaso2FXMLController.Procedimiento;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanjimenez
 */
public class ConsultasP3FXMLController implements Initializable {

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
    private Label lblLugarFecha;
    @FXML
    private Label lblDesc;
    
    
    @FXML
    private TextArea txtLugar;
    @FXML
    private Label lblExam;
    
    @FXML
    private Accordion Acordion1;
    @FXML
    private TitledPane titulo11;
    @FXML
    private ScrollPane Scroll11;
    @FXML
    private VBox Enzimas;
    @FXML
    private TitledPane titulo21;
    @FXML
    private ScrollPane scroll21;
    @FXML
    private VBox Inmuologico;
    @FXML
    private TitledPane titulo31;
    @FXML
    private ScrollPane scroll31;
    @FXML
    private VBox Orina;
    @FXML
    private TitledPane titulo41;
    @FXML
    private ScrollPane scroll41;
    @FXML
    private VBox Fertilidad;
    @FXML
    private TitledPane titulo412;
    @FXML
    private ScrollPane scroll412;
    @FXML
    private VBox Heces;
    @FXML
    private TitledPane titulo4121;
    @FXML
    private ScrollPane scroll4121;
    @FXML
    private VBox Moco;
    @FXML
    private TitledPane titulo41211;
    @FXML
    private ScrollPane scroll41211;
    @FXML
    private VBox Anemias;
    @FXML
    private TitledPane titulo412111;
    @FXML
    private ScrollPane scroll412111;
    @FXML
    private VBox Molecular;
    @FXML
    private TitledPane titulo4121111;
    @FXML
    private ScrollPane scroll4121111;
    @FXML
    private VBox Drogas;
    @FXML
    private TitledPane titulo41211111;
    @FXML
    private ScrollPane scroll41211111;
    @FXML
    private VBox Hepaticos;
    @FXML
    private Accordion Acordion2;
    @FXML
    private TitledPane titulo111;
    @FXML
    private ScrollPane Scroll111;
    @FXML
    private VBox Bioquimicos;
    @FXML
    private TitledPane titulo211;
    @FXML
    private ScrollPane scroll211;
    @FXML
    private VBox Hormonas;
    @FXML
    private TitledPane titulo311;
    @FXML
    private VBox Electrolito;
    @FXML
    private TitledPane titulo411;
    @FXML
    private ScrollPane scroll411;
    @FXML
    private VBox Nefelometria;
    @FXML
    private TitledPane titulo511;
    @FXML
    private ScrollPane scroll511;
    @FXML
    private VBox Hematologicos;
    @FXML
    private TitledPane titulo1111;
    @FXML
    private ScrollPane Scroll1111;
    @FXML
    private VBox IFI;
    @FXML
    private TitledPane titulo1112;
    @FXML
    private ScrollPane Scroll1112;
    @FXML
    private VBox Tumorales;
    @FXML
    private TitledPane titulo1113;
    @FXML
    private ScrollPane Scroll1113;
    @FXML
    private VBox Serologicos;
    @FXML
    private TitledPane titulo1114;
    @FXML
    private ScrollPane Scroll1114;
    @FXML
    private VBox Lipidico;
    @FXML
    private TitledPane titulo1115;
    @FXML
    private ScrollPane Scroll1115;
    @FXML
    private VBox Hemostaticos;
    @FXML
    private TitledPane titulo1116;
    @FXML
    private ScrollPane Scroll1116;
    @FXML
    private ScrollPane ScrollElec;
    @FXML
    private VBox Micro;
    
   ////DATOS QUE VAN A LA SIGUIENTE PAGINA ///// 
   

    public static  ArrayList<String> examenes = new ArrayList<>();
    
    public static String  Lugar ;
    
    public static String  Descripcion ;
   
    
///////DATOS QUE VAN A LA SIGUIENTE PAGINA///////////
    @FXML
    private Label lblFaltanDatos;
    @FXML
    private TextArea TxtDescripcion;
    

   
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        ArrayList<String> hematologicos = new ArrayList<String>() {{ 
          add("Biometria Hematica");
          add("Homoglobina");
          add("Hematies");
          add("Hematocritos");
          add("Leucocitos");
          add("Eritrosedimentacion (VSG)");
          add("Plaquetas");
          add("Coombs Directo");
          add("Coombs Indirecto");
          add("Grupo Sanguíneo ABO Rh");
          add("Reticulocitos");
          add("Paludismo Antigeno");
          add("Frotis (Sangre Periferico)");
}};
        
           for(String t : hematologicos){
            CheckBox datos = new CheckBox(t.toString());
            Hematologicos.getChildren().addAll(datos);
            
            Hematologicos.setPadding(new Insets(10));
            
            
           Hematologicos.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                    examenes.add(datos.getText());
                }
                
            });
        }
        
        ArrayList<String> hemostaticos=new ArrayList<String>() {{
          add("T de Sangria");
          add("T de Coagulacion");
          add("T de Protrombina");
          add("T de Protrombina (TP)");
          add("T de Tromboplastina (TPP)");
          add("Fibrinogeno");
          add("Retracion De Coagulo");
}};
        
        for(String t : hemostaticos){
            CheckBox datos = new CheckBox(t.toString());
            Hemostaticos.getChildren().addAll(datos);
            
            Hemostaticos.setPadding(new Insets(10));
            
            
           Hemostaticos.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                    //System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }
        
        
        
        ArrayList<String> controlLipidico=new ArrayList<String>() {{
          add("Colesterol Total");
          add("HDL");
          add("LDL");
          add("Trigliceridos");
          add("VLDL Colesterol");
          add("Frotis (Sangre Periferico)");
}};
        for(String t : controlLipidico){
            CheckBox datos = new CheckBox(t.toString());
            Lipidico.getChildren().addAll(datos);
            
            Lipidico.setPadding(new Insets(10));
            
            
           Lipidico.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                   // ..System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }

ArrayList<String> serologicos=new ArrayList<String>() {{
	  add("Widal y Weill Felix");
          add("Huddleson");
          add("Antigeno VI");
          add("Mono Test");
          add("VDRL (Cualitativo)");
          add("VDRL (Cuantitativo)");
	  add("Chaqas");
}};
    

         for(String t : serologicos){
            CheckBox datos = new CheckBox(t.toString());
            Serologicos.getChildren().addAll(datos);
            
            Serologicos.setPadding(new Insets(10));
            
            
           Serologicos.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                   // System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }

ArrayList<String> bioquimicos = new ArrayList<String>() {{
          add("Urea");
          add("Bun");
          add("Creatinina");
          add("Acido Urico");
          add("Glucosa");
          add("Glucosa Post Prandial");
          add("Prueba Tolerancia De Glucosa");
          add("Fructosamina");
          add("Hemoglobina Glicosilada");
          add("Lipidos Totales");
          add("Fosfolipidos");
          add("Hierro Total");
          add("Bilirrubina Directa");
          add("Bilirrubina Indirecta");
          add("Proteinas Total");
          add("Albuminas");
          add("Globulinas");
          add("Amonio");
          add("HOMA IR");
          add("Apo A1");
          add("Apo B2");
          add("Lipoproteina A");
	 add("Insulina");   
}};
            
        for(String t : bioquimicos){
            CheckBox datos = new CheckBox(t.toString());
            Bioquimicos.getChildren().addAll(datos);
            
            Bioquimicos.setPadding(new Insets(10));
            
            
           Bioquimicos.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                    //System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }



ArrayList<String> enzimas = new ArrayList<String>() {{

	add("Fosfatasa Alcalina");
	add("Fosfatasa Acida Total");
	add("Fosfatasa Acida Prostatica");
	add("GOT");
	add("GPT");
	add("GGT");
	add("LDH");
	add("Amilasa");
	add("Lipasa");
	add("TCPK Total");
	add("CKMB");
	add("Colinesterasa");
	add("Aldolasa");
	add("Troponina T");
	add("Troponina I");

}};

    for(String t : enzimas){
            CheckBox datos = new CheckBox(t.toString());
            Enzimas.getChildren().addAll(datos);
            
            Enzimas.setPadding(new Insets(10));
            
            
           Enzimas.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                    //System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }

ArrayList<String> perfilInmunologico=new ArrayList<String>() {{
	add("Herpes I (IgG)");
	add("Herpes I (IgM)");
	add("Herpes II (IgG)");
	add("Herpes II (IgM)");
	add("Toxoplasma (IgG)");
	add("Toxoplasma (IgM)");
	add("Citomegalovirus (IgG)");
	add("Citomegalovirus (IgM)");
	add("Epstein Barr (IgG)");
	add("Epstein Barr (IgM)");
	add("Chlamydia (IgG)");
	add("Chlamydia (IgM)");
	add("Serameba");
	add("Anticuerpo HIV");
	add("Wester Blot (HIV 1-2)");
	add("Herpes Zoster");
	add("Micoplasma (IgG)");
	add("Pilory Test (IgG-IgA)");
	add("Antic Tuberculosis (TB Complex)");
	add("Varicela");
	add("Sarampion");
	add("Antic Cisticerosis");
	add("Crioglobulina");
	add("Sclero 70");
	add("IgE");
	add("IgM");
	add("IgA");
	add("IgG");
	add("Ac Anti Dengue IgG");
	add("Ac Anti Dengue IgM");
	add("Ac Ant Dengue Cualitativo");
	add("Parotiditis");

}};

 for(String t : perfilInmunologico){
            CheckBox datos = new CheckBox(t.toString());
            Inmuologico.getChildren().addAll(datos);
            
            Inmuologico.setPadding(new Insets(10));
            
            
           Inmuologico.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                   // System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }

ArrayList<String> electrolitos = new ArrayList<String>() {{

	add("Sodio");
	add("Potasio");
	add("Cloro");
	add("Calcio");
	add("Fosforo");
	add("Magnesio");
	add("Calcio Ionico");
	add("Litio");

}};

     for(String t : electrolitos){
            CheckBox datos = new CheckBox(t.toString());
            Electrolito.getChildren().addAll(datos);
            
            Electrolito.setPadding(new Insets(10));
            
            
           Electrolito.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                    //System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }


ArrayList<String> hormonas = new ArrayList<String>() {{

	add("T4 (Trioxina Total)");
	add("Ft4 (Trioxina Libre)");
	add("T3 (Triyodotronina Libre)");
	add("TSH");
	add("TSH (Ultrasensible)");
	add("Anti-Tiroglobulina (ATG)");
	add("Cortisol");
	add("Prolactina");
	add("Fsh");
	add("LH");
	add("Estradiol");
	add("Progesterona");
	add("Testosterona");
	add("Testosterona Libre");
	add("ACTH");
	add("HCG (Cualitativa)");
	add("BHCG (Cualitativa)");
	add("Peptido C");
	add("IGF-1");
	add("DHEAS");
	add("Estradiol Libre");
	add("GH (Hormona De Crecimineto)");
	add("GH (Hormona De Crecimiento) Con Estimulos");
	add("PTH");
	add("IGFBP3");

}};

         for(String t : hormonas){
            CheckBox datos = new CheckBox(t.toString());
            Hormonas.getChildren().addAll(datos);
            
            Hormonas.setPadding(new Insets(10));
            
            
           Hormonas.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                    System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }

ArrayList<String> nefelometria = new ArrayList<String>() {{

	add("Inmunoglobulina G");
	add("PCR Cuantitativa");
	add("Ra Test Cuantitativa");
	add("Asto Cuantitativa");
	add("Complemento C3");
	add("Complemento C4");

}};

          for(String t : nefelometria){
            CheckBox datos = new CheckBox(t.toString());
            Nefelometria.getChildren().addAll(datos);
            
            Nefelometria.setPadding(new Insets(10));
            
            
           Nefelometria.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                    System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }

ArrayList<String> marcadoresTumorales = new ArrayList<String>() {{

	add("AFG (Alfa Feto Proteina)");
	add("ACE (Antigeno Carcinoembrionario)");
	add("APS (Antigeno Prostatico Especifico)");
	add("APS Libre");
	add("Ca 15-3");
	add("Ca 125");
	add("Ca 72-4");
	add("Ca 19-9");
	add("Ca 21-1");
	add("Ca 15-3 (Mamas)");
	add("Ca 19-9 (Pancreas)");
	add("Ca 72-4 (Estomago)");
	add("Ca 125 (Ovario-Pulmon)");
}};

    
        for(String t : marcadoresTumorales){
            CheckBox datos = new CheckBox(t.toString());
            Tumorales.getChildren().addAll(datos);
            
            Tumorales.setPadding(new Insets(10));
            
            
           Tumorales.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                    System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }


ArrayList<String> microbiologiaBacteriologia = new ArrayList<String>() {{

	add("Cultivo De Orina");
	add("Cultivo De Heces");
	add("Cultivo De E.X. Faringeo");
	add("Cultivo De Sec Vaginal");
	add("Hemocultivo");
	add("Cultivo De Sec Uretal");
	add("Cultivo De Esputo");
	add("Cultivo Para Micobacterium (TB)");
	add("Cultivo Para Hongos");
	add("Strep A");
	add("Autovacuna");
	add("Cultivo De Catheter");
	add("Cultivo De Forunculo");
	add("Cultivo De Liquidos");
	add("Cultivo De Pustulas");
	add("Cultivo De Secrecion Uretral");
	add("Cultivo De Semen");
	add("KOH Directo");
}};

 for(String t : microbiologiaBacteriologia){
            CheckBox datos = new CheckBox(t.toString());
            Micro.getChildren().addAll(datos);
            
            Micro.setPadding(new Insets(10));
            
            
           Micro.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                    System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }

       

ArrayList<String> fertilidad = new ArrayList<String>() {{

	add("Espermatograma");
	add("Cultivo y Antibiograma");
	add("Test Post-Coito");
	add("Score Cervical");
	add("Otros");

}};

           for(String t : fertilidad){
            CheckBox datos = new CheckBox(t.toString());
            Fertilidad.getChildren().addAll(datos);
            
            Fertilidad.setPadding(new Insets(10));
            
            
           Fertilidad.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                    System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }

ArrayList<String> ifi = new ArrayList<String>() {{

	add("Anti DNA");
	add("ANA");
	add("ASMA");
	add("ANCA (C)(P)");
	add("Anti RNP");
	add("Anti Sm");
	add("Anti SSA");
	add("Anti SSB");

}};
          for(String t : ifi){
            CheckBox datos = new CheckBox(t.toString());
            IFI.getChildren().addAll(datos);
            
            IFI.setPadding(new Insets(10));
            
            
           IFI.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                    System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }

ArrayList<String> marcadoresHepaticos= new ArrayList<String>() {{

	add("Anti Hav Total");
	add("Anti Hav IgM-IgG");
	add("Anti HBC");
	add("Has A9");
	add("HCV");

}};

        for(String t : marcadoresHepaticos){
            CheckBox datos = new CheckBox(t.toString());
            Hepaticos.getChildren().addAll(datos);
            
            Hepaticos.setPadding(new Insets(10));
            
            
           Hepaticos.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                    System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }


ArrayList<String> nivelesFarmacosAbusoDrogas = new ArrayList<String>() {{

	add("Acido Vaiproico");
	add("Barbiturico");
	add("Carbamezepina");
	add("Marihuana");
	add("Cocaina");
	add("Oplaceos En Orina");

}};

    for(String t : nivelesFarmacosAbusoDrogas){
            CheckBox datos = new CheckBox(t.toString());
            Drogas.getChildren().addAll(datos);
            
            Drogas.setPadding(new Insets(10));
            
            
           Drogas.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                    System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }

ArrayList<String> biologiaMolecular = new ArrayList<String>() {{

	add("Carga Viral HIV");
	add("CD4/CD8");
	add("Carga Viral En Hepatitis B");
	add("Carga Viral En Hepatitis C");
	add("Deteccion Epstein Barr Virus");
	add("Deteccion De Neisseria G");
	add("Deteccion De M Tuberculosis");

}};

     for(String t : biologiaMolecular){
            CheckBox datos = new CheckBox(t.toString());
            Molecular.getChildren().addAll(datos);
            
            Molecular.setPadding(new Insets(10));
            
            
           Molecular.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                    System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }

ArrayList<String>  controlAnemias= new ArrayList<String>() {{

	add("Vitamina B12");
	add("Transaferrina");
	add("Hierro Serico");
	add("Ac Folico");
	add("Capacidad De Fijacion Fe");
	add("Electroforesis De Hemoglobina");
	add("Ferritina Eritropoyetina");
	add("Haptoglobina");
	add("Glucosa 6 Fosfato");

}};

         for(String t : controlAnemias){
            CheckBox datos = new CheckBox(t.toString());
            Anemias.getChildren().addAll(datos);
            
            Anemias.setPadding(new Insets(10));
            
            
           Anemias.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                    System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }

ArrayList<String>  mocoNasal= new ArrayList<String>() {{
	add("Citologia De Moco Nasal");
}};

  for(String t : mocoNasal){
            CheckBox datos = new CheckBox(t.toString());
            Moco.getChildren().addAll(datos);
            
            Moco.setPadding(new Insets(10));
            
            
           Moco.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                    System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }

ArrayList<String>  heces= new ArrayList<String>() {{

	add("Parasitologico");
	add("Clostridium Difficile");
	add("Sangre Oculta");
	add("Citologia De Moco Nasal");
	add("Metodo De Graham (Oxiuros)");
	add("Adenovirus");
	add("Rotavirus");
	add("Pilory En Heces");
	add("Azucares Reductores");
	add("Tincion De Sudan");
	add("Parasitologico Por Concentracion");

}};
          for(String t : heces){
            CheckBox datos = new CheckBox(t.toString());
            Heces.getChildren().addAll(datos);
            
            Heces.setPadding(new Insets(10));
            
            
           Heces.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                    System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }
ArrayList<String>  orina= new ArrayList<String>() {{

	add("Fisico-Quimico-Sedimento");
	add("Gravindex");
	add("Proteinuria Orina 24 Hrs");
	add("Depuracion Creatinina Orina 24 Hrs");
	add("Microalbumina En Orina Al Azar");
	add("Proteina De Bence Jones");
	add("Pyrilinks-D");
	add("Calcio En Orina 24 Hrs");
	add("Estudio De Calculo");
	add("Sodio En Orina");
	add("Urea En Orina 24 Hrs");
	add("Potasio En Orina 24 Hrs");

}};

       for(String t : orina){
            CheckBox datos = new CheckBox(t.toString());
            Orina.getChildren().addAll(datos);
            
            Orina.setPadding(new Insets(10));
            
            
           Orina.setSpacing(10);
            datos.setOnMouseClicked(evento->{
                if(datos.isSelected()){
                    System.out.println(datos.getText());
                    examenes.add(datos.getText());
                }
                
            });
        }

     

    }    

    
   
    public void setExFísico(String ExFísico) {
        //this.ExFísico = ExFísico;
    }
    
    public void setProcedimiento(String Procedimiento) {
        //this.Procedimiento = Procedimiento;
    }
    
    
    public void SetTextos(String des, String hora ){
       TxtDescripcion.setText(des);
       txtLugar.setText(hora);
    }
    
    
      @FXML
    private void BackPaso2(Event event) throws IOException{
       
        
   FXMLLoader loader = new FXMLLoader(getClass().getResource("/Pantallas/ConsultasPaso2FXML.fxml"));
    Parent BackPaso2 = (Parent)loader.load();
    
    ConsultasPaso2FXMLController p2 = loader.getController();
    p2.SetTextos(ExFísico, Procedimiento);
    
    
    Scene  BackPaso2Scene = new Scene(BackPaso2);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(BackPaso2Scene );
        window.show();
        
        
        
    }
    
    
    @FXML
    private void ConsultaP4(ActionEvent event) throws IOException{
        if(txtLugar.getText().equals("") || TxtDescripcion.getText().equals("")){
            lblFaltanDatos.setVisible(true);
            
        }else{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Pantallas/ConsultaP4FXML.fxml"));
            
            Parent ConsultaP4Parent = (Parent)loader.load();
            
            ConsultaP4FXMLController p = loader.getController();
           
            Descripcion = TxtDescripcion.getText();
            Lugar = txtLugar.getText();
           // p.setExamenes(examenes);
            Scene  ConsultaP4Scene = new Scene(ConsultaP4Parent);
    
    //aqui nos da la infomarcion del stage
        Stage window = (Stage)( (Node)event.getSource()).getScene().getWindow();
        window.setScene(ConsultaP4Scene);
        window.show();
        }
        
    
    }
    
    
}
