package com.edudev.proyectomatriculas.io.model;

import com.edudev.proyectomatriculas.io.response.CursoSeleccionado;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by EDUARDO_RIVERA on 31/08/2017.
 */

public class Matricula{
    @SerializedName("cursos")
    ArrayList<CursoSeleccionado> arrayList;

    @SerializedName("user")
    private String user;

    @SerializedName("cred")
    private String cred;

    public ArrayList<CursoSeleccionado> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<CursoSeleccionado> arrayList) {
        this.arrayList = arrayList;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCred() {
        return cred;
    }

    public void setCred(String cred) {
        this.cred = cred;
    }
}
