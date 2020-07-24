package pl.wotu.barretto.util;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferences {
    SharedPreferences preferences;

    public static int getInt(Context context,String key,int defaultValue){
        SharedPreferences preferences = context.getSharedPreferences("BarrettoPreferences", Context.MODE_PRIVATE);
        return preferences.getInt(key,0);
    }

    public static String getString(Context context, String key,String defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences("BarrettoPreferences", Context.MODE_PRIVATE);
        return preferences.getString(key,defaultValue);
    }

    public static boolean getBoolean(Context context, String key,boolean defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences("BarrettoPreferences", Context.MODE_PRIVATE);
        return preferences.getBoolean(key,defaultValue);
    }

    public static void putString(Context context,String key,String value){
        SharedPreferences pref = context.getSharedPreferences("BarrettoPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    };
    public static void putBoolean(Context context,String key,boolean value){
        SharedPreferences pref = context.getSharedPreferences("BarrettoPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    };
    public static void putInt(Context context,String key,int value){
        SharedPreferences pref = context.getSharedPreferences("BarrettoPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.apply();
    };
}
