package com.edudev.proyectomatriculas;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pc on 03/12/2016.
 */

public class Global extends Application {


    public static void setBoolShared(Activity activity, String key, boolean value) {
        SharedPreferences sharedPref = activity.getSharedPreferences("global_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void setUsuarioShared(Activity activity, String key, String value) {
        SharedPreferences sharedPref = activity.getSharedPreferences("global_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setNombreUsuarioShared(Activity activity, String key, String value) {
        SharedPreferences sharedPref = activity.getSharedPreferences("global_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static boolean getBoolFromShared(Activity activity, String key) {
        SharedPreferences sharedPref = activity.getSharedPreferences("global_preferences", Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, false);
    }

    public static String getUsuarioFromShared(Activity activity, String key) {
        SharedPreferences sharedPref = activity.getSharedPreferences("global_preferences", Context.MODE_PRIVATE);
        return sharedPref.getString(key,"");
    }

    public static String getNombreUsuarioFromShared(Activity activity, String key) {
        SharedPreferences sharedPref = activity.getSharedPreferences("global_preferences", Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");
    }

    public static void clearUsuarioShared(Activity activity) {
        setUsuarioShared(activity, "id_usuario", "");
        setNombreUsuarioShared(activity, "nombre_p", "");
    }

    public static boolean getCameraFromShared(Activity activity, String key){
        SharedPreferences sharedPref = activity.getSharedPreferences("global_preferences", Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, false);
    }
    public static void setCameraShared(Activity activity, String key, boolean value) {
        SharedPreferences sharedPref = activity.getSharedPreferences("global_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    public static boolean getBurbujaFromShared(Activity activity, String key){
        SharedPreferences sharedPref = activity.getSharedPreferences("global_preferences", Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, false);
    }
    public static void setBurbujaShared(Activity activity, String key, boolean value) {
        SharedPreferences sharedPref = activity.getSharedPreferences("global_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    public static String getLinkImage(String nombre) {
        return "http://www.ticsiperu.net/Townalerts/laravel/public/images/tipoalerta/" + nombre;
        //return "http://192.168.1.40/ciudadano-project/laravel/public//images/tipoalerta/" + nombre;
    }
}
