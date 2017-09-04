package com.edudev.proyectomatriculas.io.response;

/**
 * Created by pc on 18/01/2017.
 */

public class VerificaResponse {
    private String idperiodo;
    private String seccion;
    private String tipocurricula;
    private String cred_max;
    private String cred_min;
    private String count_matri;
    private String cronograma;
    private String curriculaalu;
    private String estadoalu;

    public String getIdPeriodo() {
        return idperiodo;
    }

    public void setIdPeriodo(String idPeriodo) {
        this.idperiodo = idperiodo;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getTipocurricula() {
        return tipocurricula;
    }

    public void setTipocurricula(String tipocurricula) {
        this.tipocurricula = tipocurricula;
    }

    public String getCred_max() {
        return cred_max;
    }

    public void setCred_max(String cred_max) {
        this.cred_max = cred_max;
    }

    public String getCred_min() {
        return cred_min;
    }

    public void setCred_min(String cred_min) {
        this.cred_min = cred_min;
    }

    public String getCount_matri() {
        return count_matri;
    }

    public void setCount_matri(String count_matri) {
        this.count_matri = count_matri;
    }

    public String getCronograma() {
        return cronograma;
    }

    public void setCronograma(String cronograma) {
        this.cronograma = cronograma;
    }

    public String getCurriculaalu() {
        return curriculaalu;
    }

    public void setCurriculaalu(String curriculaalu) {
        this.curriculaalu = curriculaalu;
    }

    public String getEstadoalu() {
        return estadoalu;
    }

    public void setEstadoalu(String estadoalu) {
        this.estadoalu = estadoalu;
    }
}
