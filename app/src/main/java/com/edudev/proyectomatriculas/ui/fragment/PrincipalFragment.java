package com.edudev.proyectomatriculas.ui.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.edudev.proyectomatriculas.Global;
import com.edudev.proyectomatriculas.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PrincipalFragment extends Fragment {

    View view;
    EditText Nombre;
    EditText Apellidos;
    EditText DNI;
    EditText Email;
    EditText Matri;
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
        Email=(EditText) view.findViewById(R.id.input_email);
        Matri=(EditText) view.findViewById(R.id.input_matricula);

        cargaDatos();
        return view;
    }


    public void cargaDatos(){
        new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {}
            public void onFinish() {
                progressDialog.dismiss();
                Nombre.setText(Global.getUsuarioDatosFromShared(getActivity(),"per_nombres"));
                Apellidos.setText(Global.getUsuarioDatosFromShared(getActivity(),"per_paterno")+" "+Global.getUsuarioDatosFromShared(getActivity(),"per_materno"));
                DNI.setText(Global.getUsuarioDatosFromShared(getActivity(),"documento"));
                Matri.setText(Global.getUsuarioDatosFromShared(getActivity(),"login"));
                Email.setText(Global.getUsuarioDatosFromShared(getActivity(),"email"));
            }
        }.start();
    }
}
