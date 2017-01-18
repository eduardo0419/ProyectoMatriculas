package com.edudev.proyectomatriculas.io;


import com.edudev.proyectomatriculas.io.response.ActualizaResponse;
import com.edudev.proyectomatriculas.io.response.DatosResponse;
import com.edudev.proyectomatriculas.io.response.SessionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MatriculaApiService {

    @GET("login.php")
    Call<SessionResponse> getLogin(@Query("user") String user, @Query("pass") String password);

    @GET("datos_personales.php")
    Call<DatosResponse> getDatos(@Query("matricula") String matricula);

    @GET("actualiza_datos.php")
    Call<ActualizaResponse> setDatos(@Query("matricula") String matricula,@Query("dni") String dni,@Query("celular") String celular);
}