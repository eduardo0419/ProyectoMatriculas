package com.edudev.proyectomatriculas.io.response;


import com.edudev.proyectomatriculas.io.model.DataSesion;

import java.util.ArrayList;

public class SessionResponse {


    private int suceso;
    private ArrayList<DataSesion> data;


    public int getSuceso() {
        return suceso;
    }

    public void setSuceso(int suceso) {
        this.suceso = suceso;
    }

    public ArrayList<DataSesion> getData() {
        return data;
    }

    public void setData(ArrayList<DataSesion> data) {
        this.data = data;
    }
}
