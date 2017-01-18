package com.edudev.proyectomatriculas.io.response;

import com.edudev.proyectomatriculas.io.model.Curso;

import java.util.ArrayList;

public class ListarResponse {

    private ArrayList<Curso> arrayList;

    public ArrayList<Curso> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Curso> arrayList) {
        this.arrayList = arrayList;
    }
}
