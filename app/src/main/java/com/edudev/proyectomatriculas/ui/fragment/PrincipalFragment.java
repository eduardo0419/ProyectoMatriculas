package com.edudev.proyectomatriculas.ui.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edudev.proyectomatriculas.Global;
import com.edudev.proyectomatriculas.LoginActivity;
import com.edudev.proyectomatriculas.R;
import com.edudev.proyectomatriculas.io.MatriculaApiAdapter;
import com.edudev.proyectomatriculas.io.response.ActualizaResponse;
import com.edudev.proyectomatriculas.io.response.DatosResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PrincipalFragment extends Fragment implements View.OnClickListener, Callback<DatosResponse> {

    View view;
    EditText Nombre;
    EditText Apellidos;
    EditText DNI;
    EditText Celular;
    EditText Matri;
    Button Actualiza;
    ProgressDialog progressDialog;
    public PrincipalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_principal, container, false);

        progressDialog=ProgressDialog.show(getContext(),"Verificando","Espere por favor...");

        Nombre=(EditText) view.findViewById(R.id.input_nombre);
        Apellidos=(EditText) view.findViewById(R.id.input_apellido);
        DNI=(EditText) view.findViewById(R.id.input_dni);
        Celular=(EditText) view.findViewById(R.id.input_celular);
        Matri=(EditText) view.findViewById(R.id.input_matricula);

        Actualiza=(Button)view.findViewById(R.id.btn_actualiza);
        Actualiza.setOnClickListener(this);

        cargaDatos();
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_actualiza){
            actualizaDatos();
        }
    }

    public void cargaDatos(){
        Call<DatosResponse> call= MatriculaApiAdapter.getApiService().getDatos(Global.getUsuarioFromShared(getActivity(),"id_usuario"));
        call.enqueue(this);
    }

    public void actualizaDatos(){
        Call<ActualizaResponse> call=MatriculaApiAdapter.getApiService().setDatos(Global.getUsuarioFromShared(getActivity(),"id_usuario"),DNI.getText().toString(),Celular.getText().toString());
        call.enqueue(new actualizar());
    }

    @Override
    public void onResponse(Call<DatosResponse> call, Response<DatosResponse> response) {
        if (response.isSuccessful()) {
            DatosResponse sessionResponse= response.body();
            progressDialog.dismiss();
            Nombre.setText(sessionResponse.getNombre());
            Apellidos.setText(sessionResponse.getApellido());
            DNI.setText(sessionResponse.getDni());
            Celular.setText(sessionResponse.getCelular());
            Matri.setText(Global.getUsuarioFromShared(getActivity(),"id_usuario"));
        }
    }

    @Override
    public void onFailure(Call<DatosResponse> call, Throwable t) {
        progressDialog.dismiss();
        Intent intent1=new Intent(getContext(),LoginActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent1);
        Toast.makeText(getContext(),"Error de conectivilidad al servidor",Toast.LENGTH_SHORT).show();
    }

    public class actualizar implements Callback<ActualizaResponse> {

        @Override
        public void onResponse(Call<ActualizaResponse> call, Response<ActualizaResponse> response) {
            if (response.isSuccessful()) {
                Toast.makeText(getContext(),"Datos Actualizados correctamente",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onFailure(Call<ActualizaResponse> call, Throwable t) {
            Toast.makeText(getContext(),"Error de conectivilidad al servidor",Toast.LENGTH_SHORT).show();
        }
    }
}
