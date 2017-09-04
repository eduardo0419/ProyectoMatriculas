package com.edudev.proyectomatriculas.io;


import com.edudev.proyectomatriculas.io.model.Matricula;
import com.edudev.proyectomatriculas.io.response.CursoSeleccionado;
import com.edudev.proyectomatriculas.io.response.HorariosResponse;
import com.edudev.proyectomatriculas.io.response.MatriculaResponse;
import com.edudev.proyectomatriculas.io.response.VerificaResponse;
import com.edudev.proyectomatriculas.io.response.ListarResponse;
import com.edudev.proyectomatriculas.io.response.SessionResponse;
import com.edudev.proyectomatriculas.ui.fragment.MatriculaFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MatriculaApiService {

    @GET("login.php")
    Call<SessionResponse> getLogin(@Query("username") String user, @Query("password") String password);

    @GET("matricula.php")
    Call<ArrayList<VerificaResponse>> verifi(@Query("user") String user, @Query("task") String task);

    @GET("matricula.php")
    Call<ListarResponse> getCursos(@Query("user") String user,@Query("idperiodo") String idperiodo,@Query("curricula") String curricula,@Query("seccion") String seccion, @Query("task") String task);

    @GET("matricula.php")
    Call<HorariosResponse> getHorarios(@Query("user") String user,@Query("idperiodo") String idperiodo,@Query("idcrusoprog") String idcrusoprog, @Query("task") String task);

    @POST("matricula.php?task=matri")
    Call<MatriculaResponse> setMatricula(/*@Header("user") String user, @Header("task") String task,*/ @Body Matricula matricula);

    @GET("help.php")
    Call<SessionResponse> help(@Query("user") String user,@Query("titu") String titu,@Query("des") String des);

}