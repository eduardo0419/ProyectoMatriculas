package com.edudev.proyectomatriculas.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edudev.proyectomatriculas.Global;
import com.edudev.proyectomatriculas.R;
import com.edudev.proyectomatriculas.io.MatriculaApiAdapter;
import com.edudev.proyectomatriculas.io.response.SessionResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Callback<SessionResponse> {

    Button ingresa;
    EditText usuario;
    EditText pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ingresa=(Button)findViewById(R.id.btn_ingresa);
        ingresa.setOnClickListener(this);

        usuario=(EditText)findViewById(R.id.txt_usuario);
        pass=(EditText)findViewById(R.id.txt_clave);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ingresa:
                if (Global.getUsuarioDatosFromShared(this,"login")=="") {
                    ingresar();
                }else{
                    Intent intent1=new Intent(this,MenuActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent1);
                }
                break;
        }
    }
    public void ingresar(){
        Call<SessionResponse> call= MatriculaApiAdapter.getApiService().getLogin(usuario.getText().toString(),pass.getText().toString());
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<SessionResponse> call, Response<SessionResponse> response) {
        if (response.isSuccessful()) {
            SessionResponse sessionResponse= response.body();
            switch (sessionResponse.getSuceso()){
                case -1:
                    Toast.makeText(this,"EL USUARIO INGRESADO NO EXISTE",Toast.LENGTH_LONG).show();
                    break;
                case 0:
                    Toast.makeText(this,"CONTRASEÑA INCORRECTA",Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    Toast.makeText(this,"EL USUARIO NO PERTENCE AL ALUMNADO DE LA UNIVERSIDAD",Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(this,"SESION INVALIDA",Toast.LENGTH_LONG).show();
                    break;
                case 4:
                    Toast.makeText(this,"EL INGRESO NO ESTA PERMITIDO",Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    Toast.makeText(this,"BIENVENIDO",Toast.LENGTH_LONG).show();
                    Global.setUsuarioDatosShared(this,"login",sessionResponse.getData().get(0).getLogin());
                    Global.setUsuarioDatosShared(this,"per_codigo",sessionResponse.getData().get(0).getPer_codigo());
                    Global.setUsuarioDatosShared(this,"per_paterno",sessionResponse.getData().get(0).getPer_paterno());
                    Global.setUsuarioDatosShared(this,"per_materno",sessionResponse.getData().get(0).getPer_materno());
                    Global.setUsuarioDatosShared(this,"per_nombres",sessionResponse.getData().get(0).getPer_nombres());
                    Global.setUsuarioDatosShared(this,"documento",sessionResponse.getData().get(0).getDocumento());
                    Global.setUsuarioDatosShared(this,"email",sessionResponse.getData().get(0).getEmail());
                    Global.setUsuarioDatosShared(this,"idEmpresa",sessionResponse.getData().get(0).getIdEmpresa());
                    Global.setUsuarioDatosShared(this,"idArea",sessionResponse.getData().get(0).getIdArea());
                    Global.setUsuarioDatosShared(this,"idEstructura",sessionResponse.getData().get(0).getIdEstructura());
                    Global.setUsuarioDatosShared(this,"estr_descripcion",sessionResponse.getData().get(0).getEstr_descripcion());
                    Global.setUsuarioDatosShared(this,"idsede",sessionResponse.getData().get(0).getIdsede());
                    Global.setUsuarioDatosShared(this,"rol",sessionResponse.getData().get(0).getRol());
                    Global.setUsuarioDatosShared(this,"empresaname",sessionResponse.getData().get(0).getEmpresaname());
                    Global.setUsuarioDatosShared(this,"sistemaname",sessionResponse.getData().get(0).getSistemaname());
                    Global.setUsuarioDatosShared(this,"per_cargo",sessionResponse.getData().get(0).getPer_cargo());
                    Global.setUsuarioDatosShared(this,"idUnidadNegocio",sessionResponse.getData().get(0).getIdUnidadNegocio());
                    Global.setUsuarioDatosShared(this,"Empresa",sessionResponse.getData().get(0).getEmpresa());
                    Global.setUsuarioDatosShared(this,"_id_",sessionResponse.getData().get(0).get_id_());
                    Global.setUsuarioDatosShared(this,"_session",sessionResponse.getData().get(0).get_session());

                    Intent intent1=new Intent(this,MenuActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent1);
                    break;

            }
        }
    }

    @Override
    public void onFailure(Call<SessionResponse> call, Throwable t) {
        Toast.makeText(this,"Error de conectivilidad al servidor",Toast.LENGTH_SHORT).show();
    }
}
