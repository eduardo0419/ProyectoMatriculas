package com.edudev.proyectomatriculas.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.edudev.proyectomatriculas.R;
import com.edudev.proyectomatriculas.io.MatriculaApiAdapter;
import com.edudev.proyectomatriculas.io.model.Curso;
import com.edudev.proyectomatriculas.io.response.ListarResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatriculaFragment extends Fragment implements Callback<ListarResponse> {

    View view;
    TableLayout tableLayout;
    ArrayList<Curso> listarCurso;
    public MatriculaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_matricula, container, false);

        tableLayout = (TableLayout) view.findViewById(R.id.tableLayout);

        cargarDatos();

        return view;
    }

    public void cargarDatos(){
        Call<ListarResponse> call= MatriculaApiAdapter.getApiService().getCursos();
        call.enqueue(this);
    }

    public void armarTabla(){
        TableRow cabecera = (TableRow) LayoutInflater.from(getContext()).inflate(R.layout.encabezado_cursos, null);
        tableLayout.addView(cabecera);
        for (int i=0;i<listarCurso.size();i++) {

            TableRow fila  = (TableRow)LayoutInflater.from(getContext()).inflate(R.layout.fila_curso, null);

            ((EditText)fila.findViewById(R.id.input_codigo)).setText(listarCurso.get(i).getCodigo());
            ((EditText)fila.findViewById(R.id.input_nombre_curso)).setText(listarCurso.get(i).getDescripcion());
            ((EditText)fila.findViewById(R.id.input_credito)).setText(listarCurso.get(i).getCredito());
            ((EditText)fila.findViewById(R.id.input_ciclo)).setText(listarCurso.get(i).getCiclo());

            tableLayout.addView(fila);
        }
        tableLayout.requestLayout();
    }
    @Override
    public void onResponse(Call<ListarResponse> call, Response<ListarResponse> response) {
        if (response.isSuccessful()) {
            ListarResponse listarResponses= response.body();
            listarCurso=listarResponses.getArrayList();
            armarTabla();
        }
    }

    @Override
    public void onFailure(Call<ListarResponse> call, Throwable t) {
        Toast.makeText(getContext(),"Error de conectivilidad al servidor",Toast.LENGTH_SHORT).show();
    }
}
