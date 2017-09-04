package com.edudev.proyectomatriculas.io.response;

/**
 * Created by pc on 18/01/2017.
 */

public class MatriculaResponse {
    private boolean error;
    private int count;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
