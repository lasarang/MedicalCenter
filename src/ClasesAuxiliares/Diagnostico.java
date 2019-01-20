/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAuxiliares;

/**
 *
 * @author juanjimenez
 */
public class Diagnostico {
    
    private String Patologia;
    private String Antecedente;
    private String Cie10;

    public Diagnostico(String Patologia, String Antecedente, String Cie10) {
        this.Patologia = Patologia;
        this.Antecedente = Antecedente;
        this.Cie10 = Cie10;
    }

    public String getPatologia() {
        return Patologia;
    }

    public void setPatologia(String Patologia) {
        this.Patologia = Patologia;
    }

    public String getAntecedente() {
        return Antecedente;
    }

    public void setAntecedente(String Antecedente) {
        this.Antecedente = Antecedente;
    }

    public String getCie10() {
        return Cie10;
    }

    public void setCie10(String Cie10) {
        this.Cie10 = Cie10;
    }

  

    @Override
    public String toString() {
        return "Diagnostico{" + "Patologia=" + Patologia + ", Antecedente=" + Antecedente + ", Cie10=" + Cie10 + '}';
    }
    
    
    
    
}
