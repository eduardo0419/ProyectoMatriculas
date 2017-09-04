package com.edudev.proyectomatriculas.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.edudev.proyectomatriculas.io.response.VerificaResponse;
import com.edudev.proyectomatriculas.ui.fragment.AyudaFragment;
import com.edudev.proyectomatriculas.Global;
import com.edudev.proyectomatriculas.R;
import com.edudev.proyectomatriculas.io.MatriculaApiAdapter;
import com.edudev.proyectomatriculas.ui.fragment.MatriculaFragment;
import com.edudev.proyectomatriculas.ui.fragment.PieChartFragment;
import com.edudev.proyectomatriculas.ui.fragment.PrincipalFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Callback<ArrayList<VerificaResponse>> {

    FragmentManager fragmentManager;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content,new PrincipalFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        fragmentManager=getSupportFragmentManager();
        if (id == R.id.ir_principal) {
            fragment=new PrincipalFragment();
        } else if (id == R.id.ir_matricula) {
            progressDialog= ProgressDialog.show(this,"Verificando","Espere por favor...");

            Call<ArrayList<VerificaResponse>> call= MatriculaApiAdapter.getApiService().verifi(Global.getUsuarioDatosFromShared(this,"login"),"verifica");
            call.enqueue(this);
        } else if (id == R.id.ir_repostes) {
            fragment=new PieChartFragment();
        } else if (id == R.id.ir_ayuda) {
            fragment=new AyudaFragment();

        } else if (id == R.id.ir_salir) {
            startActivity(new Intent(this,LoginActivity.class));
            Global.clearUsuarioDatosShared(this);
        }
        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content,fragment)
                    .commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResponse(Call<ArrayList<VerificaResponse>> call, Response<ArrayList<VerificaResponse>> response) {
        if (response.isSuccessful()){
            VerificaResponse datosResponse =response.body().get(0);
            String mensaje="";

            progressDialog.dismiss();

            if(datosResponse.getIdPeriodo() == null || datosResponse.getIdPeriodo() == "null" || datosResponse.getIdPeriodo() == "") {
                mensaje="No podemos obtener un Periodo Valido Posiblemente algun problema ocurra en su codigo";
            }

            if (datosResponse.getEstadoalu().equals("1")) {
                if (datosResponse.getCronograma().equals("1")) {
                    if (datosResponse.getCount_matri().equals("0")) {

                        Global.setUsuarioDatosShared(this,"idPeriodo",datosResponse.getIdPeriodo());
                        Global.setUsuarioDatosShared(this,"seccion",datosResponse.getSeccion());
                        Global.setUsuarioDatosShared(this,"tipocurricula",datosResponse.getTipocurricula());
                        Global.setUsuarioDatosShared(this,"cred_max",datosResponse.getCred_max());
                        Global.setUsuarioDatosShared(this,"cred_min",datosResponse.getCred_min());
                        Global.setUsuarioDatosShared(this,"count_matri",datosResponse.getCount_matri());
                        Global.setUsuarioDatosShared(this,"cronograma",datosResponse.getCronograma());
                        Global.setUsuarioDatosShared(this,"curriculaalu",datosResponse.getCurriculaalu());
                        Global.setUsuarioDatosShared(this,"estadoalu",datosResponse.getEstadoalu());

                        Fragment fragment=new MatriculaFragment();
                        fragmentManager.beginTransaction()
                                .replace(R.id.content,fragment)
                                .commit();
                    }else{
                        mensaje="Ya tiene una matricula generada, revise su ficha de Matricula";
                    }
                }else {
                    if (datosResponse.getCronograma() == "" || datosResponse.getCronograma() == "null"  || datosResponse.getCronograma() == null) {
                        mensaje="Usted aun no posee una fecha para matricularse en " +datosResponse.getIdPeriodo();
                    }
                }
            }else {
                switch (datosResponse.getEstadoalu()) {
                    case "2":
                        mensaje="Usted ya es egresado";
                        break;
                    case "3":
                        mensaje="Usted ya es bachiller";
                        break;
                    case "4":
                        mensaje="Usted ya es titulado";
                        break;
                    case "5":
                        mensaje="Usted se encuentra sancionado";
                        break;
                }
            }
            if (mensaje!=""){
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setMessage(mensaje)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick( DialogInterface dialog, int id) {
                            }
                        });
                AlertDialog alert = builder2.create();
                alert.show();

            }

        }
    }

    @Override
    public void onFailure(Call<ArrayList<VerificaResponse>> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(this,"Ocurrio un error en el servidor",Toast.LENGTH_LONG).show();
    }
}
