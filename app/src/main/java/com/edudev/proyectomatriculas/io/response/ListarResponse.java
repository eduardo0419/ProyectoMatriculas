package com.edudev.proyectomatriculas.io.response;

import com.edudev.proyectomatriculas.io.model.Curso;

import java.util.ArrayList;

public class ListarResponse {

    private String total;

    private ArrayList<Curso> datos;

    public ArrayList<Curso> getDatos() {
        return datos;
    }

    public void setDatos(ArrayList<Curso> datos) {
        this.datos = datos;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
