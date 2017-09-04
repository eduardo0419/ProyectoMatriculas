package com.edudev.proyectomatriculas.ui.fragment;


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
import com.edudev.proyectomatriculas.R;
import com.edudev.proyectomatriculas.io.MatriculaApiAdapter;
import com.edudev.proyectomatriculas.io.response.SessionResponse;
import com.edudev.proyectomatriculas.ui.activity.MenuActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AyudaFragment extends Fragment {

    View view;
    EditText titulo,descripcion;
    Button ayud;
    public AyudaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_ayuda, container, false);

        titulo=(EditText)view.findViewById(R.id.titu);
        descripcion=(EditText)view.findViewById(R.id.desrco);
        ayud=(Button)view.findViewById(R.id.btn_ayuda);
        ayud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Call<SessionResponse> call= MatriculaApiAdapter.getApiService().help(Global.getUsuarioFromShared(getActivity(),"id_usuario"),titulo.getText().toString(),descripcion.getText().toString());
                //call.enqueue(new  help());
            }
        });
        return view;
    }

    public class help implements Callback<SessionResponse> {

        @Override
        public void onResponse(Call<SessionResponse> call, Response<SessionResponse> response) {
            if (response.isSuccessful()){
                startActivity(new Intent(getContext(), MenuActivity.class));
                Toast.makeText(getContext(),"Su solicituda ha sido enviada",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onFailure(Call<SessionResponse> call, Throwable t) {

        }
    }

}
