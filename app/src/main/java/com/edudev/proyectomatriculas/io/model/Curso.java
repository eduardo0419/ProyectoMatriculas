package com.edudev.proyectomatriculas.io.model;

import java.util.ArrayList;

/**
 * Created by pc on 18/01/2017.
 */

public class Curso {
    private String descripcion;
    private String codigo;
    private String credito;
    private String ciclo;
    private String tipo;
    private ArrayList<Horario> arrayTeoria;
    private ArrayList<Horario> arrayLab;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCredito() {
        return credito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Horario> getArrayTeoria() {
        return arrayTeoria;
    }

    public void setArrayTeoria(ArrayList<Horario> arrayTeoria) {
        this.arrayTeoria = arrayTeoria;
    }

    public ArrayList<Horario> getArrayLab() {
        return arrayLab;
    }

    public void setArrayLab(ArrayList<Horario> arrayLab) {
        this.arrayLab = arrayLab;
    }
}
