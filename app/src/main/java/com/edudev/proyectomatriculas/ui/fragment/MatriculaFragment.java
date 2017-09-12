package com.edudev.proyectomatriculas.ui.fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.internal.util.Predicate;
import com.edudev.proyectomatriculas.Global;
import com.edudev.proyectomatriculas.R;
import com.edudev.proyectomatriculas.io.MatriculaApiAdapter;
import com.edudev.proyectomatriculas.io.model.Curso;
import com.edudev.proyectomatriculas.io.model.Horario;
import com.edudev.proyectomatriculas.io.model.Matricula;
import com.edudev.proyectomatriculas.io.response.CursoSeleccionado;
import com.edudev.proyectomatriculas.io.response.HorariosResponse;
import com.edudev.proyectomatriculas.io.response.ListarResponse;
import com.edudev.proyectomatriculas.io.response.MatriculaResponse;
import com.edudev.proyectomatriculas.io.response.SessionResponse;
import com.edudev.proyectomatriculas.ui.activity.MenuActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatriculaFragment extends Fragment implements Callback<ListarResponse>, View.OnTouchListener, View.OnLongClickListener {

    View view;
    TableLayout tableLayout;
    ArrayList<Curso> listarCurso;
    Button matri;

    TextView semestre;
    TextView credito_max;

    ProgressDialog progressDialog;
    ProgressDialog progressDialog1;

    ArrayList<Horario> listTeoria,listPractica,listLab,lisTP;

    int creditos,aux;

    ArrayList<String> CursosSelecc=new ArrayList<String>();
    ArrayList<CursoSeleccionado> cursoSeleccionados=new ArrayList<CursoSeleccionado>();
    CursoSeleccionado cursoSeleccionado;

    public MatriculaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_matricula, container, false);
        //Contar();
        tableLayout = (TableLayout) view.findViewById(R.id.tableLayout);
        matri=(Button)view.findViewById(R.id.btn_matricula);

        semestre =(TextView)view.findViewById(R.id.txt_semestre);
        credito_max=(TextView) view.findViewById(R.id.txt_ciclo);

        semestre.setText("Semestre: "+Global.getUsuarioDatosFromShared(getActivity(),"idPeriodo"));
        credito_max.setText("Creditos: "+Global.getUsuarioDatosFromShared(getActivity(),"cred_max"));

        progressDialog=ProgressDialog.show(getContext(),"","Cargando cursos..");
        cargarDatos();

        matri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog labora;
                AlertDialog.Builder builderla = new AlertDialog.Builder(getContext());
                builderla.setMessage("La matricula se procedera a guardar esta seguro?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Matricula matricula=new Matricula();
                                matricula.setArrayList(cursoSeleccionados);
                                matricula.setUser(Global.getUsuarioDatosFromShared(getActivity(),"login"));
                                matricula.setCred(""+(Integer.parseInt(Global.getUsuarioDatosFromShared(getActivity(),"cred_max"))-creditos));

                                Call<MatriculaResponse> call =MatriculaApiAdapter.getApiService().setMatricula(/*Global.getUsuarioDatosFromShared(getActivity(),"login"),"matri",*/matricula);
                                call.enqueue(new guardaMatri());

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface,int d){

                            }
                        });
                labora = builderla.create();
                labora.show();
            }
        });
        return view;
    }

    public void cargarDatos(){
        Call<ListarResponse> call= MatriculaApiAdapter.getApiService().getCursos(Global.getUsuarioDatosFromShared(getActivity(),"login"),Global.getUsuarioDatosFromShared(getActivity(),"idPeriodo"),Global.getUsuarioDatosFromShared(getActivity(),"curriculaalu"),Global.getUsuarioDatosFromShared(getActivity(),"seccion"),"cursos");
        call.enqueue(this);
    }

    public void armarTabla(){

        creditos=Integer.parseInt(Global.getUsuarioDatosFromShared(getActivity(),"cred_max"));

        TableRow cabecera = (TableRow) LayoutInflater.from(getContext()).inflate(R.layout.encabezado_cursos, null);
        tableLayout.addView(cabecera);
        for (int i=0;i<listarCurso.size();i++) {

            final TableRow fila  = (TableRow)LayoutInflater.from(getContext()).inflate(R.layout.fila_curso, null);

            EditText codigo=(EditText) fila.findViewById(R.id.input_codigo);
            codigo.setText(listarCurso.get(i).getCodigocurso());
            final EditText curso=(EditText)fila.findViewById(R.id.input_nombre_curso);
            curso.setText(listarCurso.get(i).getCurso());
            final EditText tipo_curso=(EditText)fila.findViewById(R.id.input_tipocurso);
            tipo_curso.setText(listarCurso.get(i).getAbreviatura());
            final EditText ciclo=(EditText)fila.findViewById(R.id.input_ciclo);
            ciclo.setText(listarCurso.get(i).getCiclo());
            final EditText seccion=(EditText)fila.findViewById(R.id.input_seccion);
            seccion.setText(listarCurso.get(i).getSeccion());
            final EditText credito=(EditText)fila.findViewById(R.id.input_credito);
            credito.setText(listarCurso.get(i).getCreditos());
            final EditText vez=(EditText)fila.findViewById(R.id.input_vez);
            vez.setText(listarCurso.get(i).getVez());

            switch (vez.getText().toString()){
                case "2":
                    fila.setBackgroundColor(Color.CYAN);
                    break;
                case "3":
                    fila.setBackgroundColor(Color.YELLOW);
                    break;
                case "4":
                    fila.setBackgroundColor(Color.RED);
                    break;
            }

            codigo.setOnTouchListener(this);
            curso.setOnTouchListener(this);
            tipo_curso.setOnTouchListener(this);
            ciclo.setOnTouchListener(this);
            seccion.setOnTouchListener(this);
            credito.setOnTouchListener(this);
            vez.setOnTouchListener(this);

            curso.setOnLongClickListener(this);

            tableLayout.addView(fila);
        }
        tableLayout.requestLayout();
        progressDialog.dismiss();
    }

    @Override
    public void onResponse(Call<ListarResponse> call, Response<ListarResponse> response) {
        if (response.isSuccessful()) {
            ListarResponse listarResponses= response.body();
            listarCurso=listarResponses.getDatos();
            armarTabla();
        }
    }

    @Override
    public void onFailure(Call<ListarResponse> call, Throwable t) {
        Toast.makeText(getContext(),"Error de conectivilidad al servidor",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.input_codigo || v.getId() == R.id.input_nombre_curso || v.getId() == R.id.input_tipocurso || v.getId() == R.id.input_ciclo || v.getId() == R.id.input_seccion || v.getId() == R.id.input_credito || v.getId() == R.id.input_vez) {
            TableRow row = (TableRow) v.getParent();

            int index = tableLayout.indexOfChild(row);
            int color_aux = Color.parseColor("#ffffff");

            EditText codigo = (EditText) row.findViewById(R.id.input_codigo);
            EditText vez = (EditText) row.findViewById(R.id.input_vez);

            if (CursosSelecc.contains(codigo.getText().toString())) {
                color_aux = Color.GREEN;
            } else {
                switch (vez.getText().toString()) {
                    case "2":
                        color_aux = Color.CYAN;
                        break;
                    case "3":
                        color_aux = Color.YELLOW;
                        break;
                    case "4":
                        color_aux = Color.RED;
                        break;
                }
            }
            ColorDrawable[] color = {new ColorDrawable(Color.parseColor("#A5FFA5")), new ColorDrawable((color_aux))};
            TransitionDrawable trans = new TransitionDrawable(color);

            row.setBackgroundDrawable(trans);
            trans.startTransition(1000);
        }
        return false;
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == R.id.input_codigo || v.getId() == R.id.input_nombre_curso || v.getId() == R.id.input_tipocurso || v.getId() == R.id.input_ciclo || v.getId() == R.id.input_seccion || v.getId() == R.id.input_credito || v.getId() == R.id.input_vez) {
            final TableRow row = (TableRow) v.getParent();

            Vibrator vibrator=(Vibrator)getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(100);

            int index = tableLayout.indexOfChild(row);


            final EditText codigo = (EditText) row.findViewById(R.id.input_codigo);
            final EditText curso = (EditText) row.findViewById(R.id.input_nombre_curso);
            EditText tipo_curso = (EditText) row.findViewById(R.id.input_tipocurso);
            final EditText ciclo = (EditText) row.findViewById(R.id.input_ciclo);
            EditText seccion = (EditText) row.findViewById(R.id.input_seccion);
            final EditText credito = (EditText) row.findViewById(R.id.input_credito);
            final EditText ve = (EditText) row.findViewById(R.id.input_vez);

            if (CursosSelecc.contains(codigo.getText().toString())){
                    AlertDialog labora;
                    AlertDialog.Builder builderla = new AlertDialog.Builder(getContext());
                    builderla.setMessage("Desea cancelar la seleccion del curso?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (ve.getText().toString()) {
                                        case "2":
                                            row.setBackgroundColor(Color.CYAN);
                                            break;
                                        case "3":
                                            row.setBackgroundColor(Color.YELLOW);
                                            break;
                                        case "4":
                                            row.setBackgroundColor(Color.RED);
                                            break;
                                        default:
                                            row.setBackgroundColor(Color.WHITE);
                                            break;
                                    }
                                    CursosSelecc.remove(codigo.getText().toString());
                                    creditos=creditos+Integer.parseInt(credito.getText().toString());

                                    for(int k=0;k<cursoSeleccionados.size();k++){
                                        if (cursoSeleccionados.get(k).getIdcurso().equals(codigo.getText().toString())){
                                            cursoSeleccionados.remove(k);
                                        }
                                    }
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialogInterface,int d){

                                }
                            });
                    labora = builderla.create();
                    labora.show();

            }else {
                if (creditos>=Integer.parseInt(credito.getText().toString())) {
                    progressDialog1 = ProgressDialog.show(getContext(), "", "Cargando horarios..");
                    progressDialog1.setCancelable(false);

                    Call<HorariosResponse> call = MatriculaApiAdapter.getApiService().getHorarios(Global.getUsuarioDatosFromShared(getActivity(), "login"), Global.getUsuarioDatosFromShared(getActivity(), "idPeriodo"), listarCurso.get(index - 1).getidcursoprog(), "horarios");
                    call.enqueue(new cargaHorarios());

                    cursoSeleccionado =new CursoSeleccionado();

                    cursoSeleccionado.setPeriodo(Global.getUsuarioDatosFromShared(getActivity(),"idPeriodo"));
                    cursoSeleccionado.setIdcursoprog(listarCurso.get(index-1).getidcursoprog());
                    cursoSeleccionado.setIdcurso(listarCurso.get(index-1).getCodigocurso());
                    cursoSeleccionado.setCurso(listarCurso.get(index-1).getCurso());
                    cursoSeleccionado.setTipocurso(listarCurso.get(index-1).getTipocurso());
                    cursoSeleccionado.setSeccion(listarCurso.get(index-1).getSeccion());
                    cursoSeleccionado.setCiclo(listarCurso.get(index-1).getCiclo());
                    cursoSeleccionado.setCreditos(listarCurso.get(index-1).getCreditos());
                    cursoSeleccionado.setVez(listarCurso.get(index-1).getVez());

                    row.setBackgroundColor(Color.GREEN);
                    creditos = creditos - Integer.parseInt(credito.getText().toString());
                    CursosSelecc.add(codigo.getText().toString());
                }else{
                    Toast.makeText(getContext(),"Ya no tiene creditos disponibles :(",Toast.LENGTH_LONG).show();
                }
            }
        }
        return false;
    }

    public class cargaHorarios implements Callback<HorariosResponse> {
        @Override
        public void onResponse(Call<HorariosResponse> call, Response<HorariosResponse> response) {
            if(response.isSuccessful()){
                progressDialog1.dismiss();
                HorariosResponse horariosResponse=response.body();
                listTeoria=horariosResponse.getHorarioTeoria();
                listPractica=horariosResponse.getHorarioPractica();
                listLab=horariosResponse.getHorarioLaboratorio();
                lisTP=horariosResponse.getHorarioTeoAndPrac();
                aux=0;
                if (listTeoria.isEmpty()==false){
                    armarHorarios(listTeoria,"Seleccione horario de Teoria",1);
                    aux=aux+1;
                }
                if (listPractica.isEmpty()==false){
                    armarHorarios(listPractica,"Seleccione horario de Practica",2);
                    aux=aux+1;
                }
                if (lisTP.isEmpty()==false){
                    armarHorarios(lisTP,"Seleccione horario de Teoria y Practica",3);
                    aux=aux+1;
                }
                if (listLab.isEmpty()==false){
                    armarHorarios(listLab,"Seleccione horario de Laboratorio",4);
                    aux=aux+1;
                }

            }
        }

        private void armarHorarios(final ArrayList<Horario> array, String s, final int i) {
            final AlertDialog teoria;
            final RadioGroup te = new RadioGroup(getContext());
            te.setOrientation(LinearLayout.VERTICAL);

            for (int j = 0; j < array.size(); j++) {
                RadioButton rdbtn = new RadioButton(getContext());
                rdbtn.setText(array.get(j).getGrupo() + "  " + array.get(j).getDocente()+" "+array.get(j).getLugarhora());
                te.addView(rdbtn);
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(s)
                    .setView(te)
                    .setCancelable(false)
                    .setPositiveButton("Listo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            aux=aux-1;

                            int radioButtonID = te.getCheckedRadioButtonId();
                            View radioButton = te.findViewById(radioButtonID);
                            int idx = te.indexOfChild(radioButton);

                            cursoSeleccionado.setDocente(array.get(idx).getDocente());
                            switch (i){
                                case 1:
                                    cursoSeleccionado.setIdteo(array.get(idx).getIdgrupolectivo());
                                    cursoSeleccionado.setGrupoteo(array.get(idx).getGrupo());
                                    cursoSeleccionado.setHorarioteo(array.get(idx).getLugarhora());
                                    break;
                                case 2:
                                    cursoSeleccionado.setIdprac(array.get(idx).getIdgrupolectivo());
                                    cursoSeleccionado.setGrupoprac(array.get(idx).getGrupo());
                                    cursoSeleccionado.setHorarioprac(array.get(idx).getLugarhora());
                                    break;
                                case 3:
                                    cursoSeleccionado.setIdteoprac(array.get(idx).getIdgrupolectivo());
                                    cursoSeleccionado.setGrupoteoprac(array.get(idx).getGrupo());
                                    cursoSeleccionado.setHorarioteoprac(array.get(idx).getLugarhora());
                                    break;
                                case 4:
                                    cursoSeleccionado.setIdlab(array.get(idx).getIdgrupolectivo());
                                    cursoSeleccionado.setGrupolab(array.get(idx).getGrupo());
                                    cursoSeleccionado.setHorariolab(array.get(idx).getLugarhora());
                                    break;
                            }
                        }
                    });
            teoria = builder.create();
            te.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    ((AlertDialog)teoria).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }
            });
            teoria.show();
            teoria.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if (aux==0){
                        cursoSeleccionados.add(cursoSeleccionado);
                    }
                }
            });
            teoria.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        }

        @Override
        public void onFailure(Call<HorariosResponse> call, Throwable t) {
            progressDialog1.dismiss();
            Toast.makeText(getContext(),"Ocurrio un error de servidor",Toast.LENGTH_LONG).show();
        }
    }
    public class guardaMatri implements Callback<MatriculaResponse> {

        @Override
        public void onResponse(Call<MatriculaResponse> call, Response<MatriculaResponse> response) {
            if (response.isSuccessful()){
                startActivity(new Intent(getContext(), MenuActivity.class));
                Toast.makeText(getContext(),"SU MATRICULA DE LOS "+response.body().getCount() +" CURSOS SE HA PROCESADO CORRECTAMENTE ",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onFailure(Call<MatriculaResponse> call, Throwable t) {

        }
    }

}
