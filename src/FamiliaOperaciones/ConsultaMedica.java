/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FamiliaOperaciones;

import ClasesAuxiliares.Orden;
import ClasesAuxiliares.SignosVitales;
import ClasesAuxiliares.Tratamiento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class ConsultaMedica extends Operacion {

    private int idConsulta, idMedicalVisit;
    private Tratamiento tratamiento;
    private Orden orden;
    private LocalDateTime fechaHoraFin;
    private boolean Emergency;
    private SignosVitales signosVitales;
    private ArrayList<String[]> proximasConsultas = new ArrayList<>();
    private String motivos, acompañante, relacion, examenFisico, procedimiento;
    private HashMap<String, ArrayList<String[]>> diagnosticos = new HashMap<>();

    {
        diagnosticos.put("Personales", new ArrayList<>());
        diagnosticos.put("Familiares", new ArrayList<>());
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getIdMedicalVisit() {
        return idMedicalVisit;
    }

    public void setIdMedicalVisit(int idMedicalVisit) {
        this.idMedicalVisit = idMedicalVisit;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public boolean isEmergency() {
        return Emergency;
    }

    public void setEmergency(boolean Emergency) {
        this.Emergency = Emergency;
    }

    public SignosVitales getSignosVitales() {
        return signosVitales;
    }

    public void setSignosVitales(SignosVitales signosVitales) {
        this.signosVitales = signosVitales;
    }

    public ArrayList<String[]> getProximasConsultas() {
        return proximasConsultas;
    }

    public void setProximasConsultas(ArrayList<String[]> proximasConsultas) {
        this.proximasConsultas = proximasConsultas;
    }

    public String getMotivos() {
        return motivos;
    }

    public void setMotivos(String motivos) {
        this.motivos = motivos;
    }

    public String getAcompañante() {
        return acompañante;
    }

    public void setAcompañante(String acompañante) {
        this.acompañante = acompañante;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public String getExamenFisico() {
        return examenFisico;
    }

    public void setExamenFisico(String examenFisico) {
        this.examenFisico = examenFisico;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public HashMap<String, ArrayList<String[]>> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(HashMap<String, ArrayList<String[]>> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    @Override
    public String toString() {
        return "ConsultaMedica{" + "idConsulta=" + idConsulta + ", idMedicalVisit=" + idMedicalVisit + ", tratamiento=" + tratamiento + ", orden=" + orden + ", fechaHoraFin=" + fechaHoraFin + ", Emergency=" + Emergency + ", signosVitales=" + signosVitales + ", proximasConsultas=" + proximasConsultas + ", motivos=" + motivos + ", acompa\u00f1ante=" + acompañante + ", relacion=" + relacion + ", examenFisico=" + examenFisico + ", procedimiento=" + procedimiento + ", diagnosticos=" + diagnosticos + '}';
    }

}
