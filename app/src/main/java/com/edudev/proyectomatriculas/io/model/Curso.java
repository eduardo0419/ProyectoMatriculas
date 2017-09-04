package com.edudev.proyectomatriculas.io.model;

/**
 * Created by pc on 18/01/2017.
 */

public class Curso {
    private String cursodictar;
    private String idcursoprog ;
    private String codigocurso;
    private String curso;
    private String tipocurso;
    private String seccion;
    private String ciclo;
    private String creditos;
    private String vez;
    String idperiodo;
    private String abreviatura;

    public String getCursodictar() {
        return cursodictar;
    }

    public void setCursodictar(String cursodictar) {
        this.cursodictar = cursodictar;
    }

    public String getidcursoprog() {
        return idcursoprog;
    }

    public void setidcursoprog(String idcursoprog) {
        this.idcursoprog = idcursoprog;
    }

    public String getCodigocurso() {
        return codigocurso;
    }

    public void setCodigocurso(String codigocurso) {
        this.codigocurso = codigocurso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTipocurso() {
        return tipocurso;
    }

    public void setTipocurso(String tipocurso) {
        this.tipocurso = tipocurso;
    }


    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public String getVez() {
        return vez;
    }

    public void setVez(String vez) {
        this.vez = vez;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
}
