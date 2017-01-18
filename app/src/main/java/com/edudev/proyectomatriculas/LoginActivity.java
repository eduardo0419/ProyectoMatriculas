package com.edudev.proyectomatriculas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                ingresar();
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
            if(!sessionResponse.isError()){
                Global.setUsuarioShared(this,"id_usuario",sessionResponse.getId());
                Intent intent1=new Intent(this,MenuActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }else{
                Toast.makeText(this,"Email y/o contraseña incorrecta",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onFailure(Call<SessionResponse> call, Throwable t) {
        Toast.makeText(this,"Error de conectivilidad al servidor",Toast.LENGTH_SHORT).show();
    }
}
