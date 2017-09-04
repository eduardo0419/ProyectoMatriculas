package com.edudev.proyectomatriculas;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pc on 03/12/2016.
 */

public class Global extends Application {

    public static void setUsuarioDatosShared(Activity activity, String key, String value) {
        SharedPreferences sharedPref = activity.getSharedPreferences("global_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getUsuarioDatosFromShared(Activity activity, String key) {
        SharedPreferences sharedPref = activity.getSharedPreferences("global_preferences", Context.MODE_PRIVATE);
        return sharedPref.getString(key,"");
    }

    public static void clearUsuarioDatosShared(Activity activity) {
        Global.setUsuarioDatosShared(activity,"login","");
        Global.setUsuarioDatosShared(activity,"per_codigo","");
        Global.setUsuarioDatosShared(activity,"per_paterno","");
        Global.setUsuarioDatosShared(activity,"per_materno","");
        Global.setUsuarioDatosShared(activity,"per_nombres","");
        Global.setUsuarioDatosShared(activity,"documento","");
        Global.setUsuarioDatosShared(activity,"email","");
        Global.setUsuarioDatosShared(activity,"idEmpresa","");
        Global.setUsuarioDatosShared(activity,"idArea","");
        Global.setUsuarioDatosShared(activity,"idEstructura","");
        Global.setUsuarioDatosShared(activity,"estr_descripcion","");
        Global.setUsuarioDatosShared(activity,"idsede","");
        Global.setUsuarioDatosShared(activity,"rol","");
        Global.setUsuarioDatosShared(activity,"empresaname","");
        Global.setUsuarioDatosShared(activity,"sistemaname","");
        Global.setUsuarioDatosShared(activity,"per_cargo","");
        Global.setUsuarioDatosShared(activity,"idUnidadNegocio","");
        Global.setUsuarioDatosShared(activity,"Empresa","");
        Global.setUsuarioDatosShared(activity,"_id_","");
        Global.setUsuarioDatosShared(activity,"_session","");

        setUsuarioDatosShared(activity,"idPeriodo","");
        Global.setUsuarioDatosShared(activity,"seccion","");
        Global.setUsuarioDatosShared(activity,"tipocurricula","");
        Global.setUsuarioDatosShared(activity,"cred_max","");
        Global.setUsuarioDatosShared(activity,"cred_min","");
        Global.setUsuarioDatosShared(activity,"count_matri","");
        Global.setUsuarioDatosShared(activity,"cronograma","");
        Global.setUsuarioDatosShared(activity,"curriculaalu","");
        Global.setUsuarioDatosShared(activity,"estadoalu","");

    }



    public static String getLinkImage(String nombre) {
        return "http://www.ticsiperu.net/Townalerts/laravel/public/images/tipoalerta/" + nombre;
        //return "http://192.168.1.40/ciudadano-project/laravel/public//images/tipoalerta/" + nombre;
    }
}
