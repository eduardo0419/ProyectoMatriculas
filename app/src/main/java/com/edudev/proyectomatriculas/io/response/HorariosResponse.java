package com.edudev.proyectomatriculas.io.response;

import com.edudev.proyectomatriculas.io.model.Horario;

import java.util.ArrayList;

/**
 * Created by EDUARDO_RIVERA on 27/08/2017.
 */

public class HorariosResponse {
    private ArrayList<Horario> HorarioTeoria;
    private ArrayList<Horario> HorarioPractica;
    private ArrayList<Horario> HorarioLaboratorio;
    private ArrayList<Horario> HorarioTeoAndPrac;

    public ArrayList<Horario> getHorarioTeoria() {
        return HorarioTeoria;
    }

    public void setHorarioTeoria(ArrayList<Horario> horarioTeoria) {
        HorarioTeoria = horarioTeoria;
    }

    public ArrayList<Horario> getHorarioPractica() {
        return HorarioPractica;
    }

    public void setHorarioPractica(ArrayList<Horario> horarioPractica) {
        HorarioPractica = horarioPractica;
    }

    public ArrayList<Horario> getHorarioLaboratorio() {
        return HorarioLaboratorio;
    }

    public void setHorarioLaboratorio(ArrayList<Horario> horarioLaboratorio) {
        HorarioLaboratorio = horarioLaboratorio;
    }

    public ArrayList<Horario> getHorarioTeoAndPrac() {
        return HorarioTeoAndPrac;
    }

    public void setHorarioTeoAndPrac(ArrayList<Horario> horarioTeoAndPrac) {
        HorarioTeoAndPrac = horarioTeoAndPrac;
    }
}
