package com.edudev.proyectomatriculas.io.response;


public class SessionResponse {


    private String id;
    private boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setId(String dni) {
        this.id = dni;
    }

    public String getId() {
        return id;
    }

}
