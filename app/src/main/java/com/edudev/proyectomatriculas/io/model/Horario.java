package com.edudev.proyectomatriculas.io.model;



public class Horario {
    private String rownumber;
    private String idcursoprogramado;
    private String idgrupolectivo;
    private String grupo;
    private String docente;
    private String vacantes;
    private String cruce;
    private String lugarhora;

    public String getRownumber() {
        return rownumber;
    }

    public void setRownumber(String rownumber) {
        this.rownumber = rownumber;
    }

    public String getIdcursoprogramado() {
        return idcursoprogramado;
    }

    public void setIdcursoprogramado(String idcursoprogramado) {
        this.idcursoprogramado = idcursoprogramado;
    }

    public String getIdgrupolectivo() {
        return idgrupolectivo;
    }

    public void setIdgrupolectivo(String idgrupolectivo) {
        this.idgrupolectivo = idgrupolectivo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getVacantes() {
        return vacantes;
    }

    public void setVacantes(String vacantes) {
        this.vacantes = vacantes;
    }

    public String getCruce() {
        return cruce;
    }

    public void setCruce(String cruce) {
        this.cruce = cruce;
    }

    public String getLugarhora() {
        return lugarhora;
    }

    public void setLugarhora(String lugarhora) {
        this.lugarhora = lugarhora;
    }
}
