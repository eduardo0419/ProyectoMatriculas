package com.edudev.proyectomatriculas.io.response;

import com.edudev.proyectomatriculas.io.model.Curso;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListarResponse {
    @SerializedName("cursos")
    private ArrayList<Curso> arrayList;

    public ArrayList<Curso> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Curso> arrayList) {
        this.arrayList = arrayList;
    }
}
