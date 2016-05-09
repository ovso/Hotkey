package kr.blogspot.ovsoce.hotkey.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public final class Prefs {
    private static final String LENGTH = "#LENGTH";
    public static String getString(Context context, String prefsName, String key, String defValue) {
        SharedPreferences p = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        return p.getString(key, defValue);
    }
    public static int getInt(Context context, String prefsName, String key, int defValue) {
        SharedPreferences p = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        return p.getInt(key, defValue);
    }
    public static boolean getBoolean(Context context, String prefsName, String key, boolean defValue) {
        SharedPreferences p = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        return p.getBoolean(key, defValue);
    }
    public static long getLong(Context context, String prefsName, String key, long defValue) {
        SharedPreferences p = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        return p.getLong(key, defValue);
    }
    public static Double getDouble(Context context, String prefsName, String key, Double defValue) {
        SharedPreferences p = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        return Double.longBitsToDouble(p.getLong(key, Double.doubleToLongBits(defValue)));
    }
    public static float getFloat(Context context, String prefsName, String key, float defValue) {
        SharedPreferences p = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        return p.getFloat(key, defValue);
    }

    public static void putLong(Context context, String prefsName, String key, long value) {
        SharedPreferences p = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = p.edit();
        editor.putLong(key, value);
        editor.apply();
    }
    public static void putInt(Context context, String prefsName, String key, int value) {
        SharedPreferences p = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = p.edit();
        editor.putLong(key, value);
        editor.apply();
    }
    public static void putDouble(Context context, String prefsName, String key, double value) {
        SharedPreferences p = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = p.edit();
        editor.putLong(key, Double.doubleToRawLongBits(value));
        editor.apply();
    }
    public static void putFloat(Context context, String prefsName, String key, float value) {
        SharedPreferences p = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = p.edit();
        editor.putFloat(key, value);
        editor.apply();
    }
    public static void putBoolean(Context context, String prefsName, String key, boolean value) {
        SharedPreferences p = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = p.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    public static void putString(Context context, String prefsName, String key, String value) {
        SharedPreferences p = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = p.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public static void remove(Context context, String prefsName, final String key) {
        SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        if (prefs.contains(key + LENGTH)) {
            int stringSetLength = prefs.getInt(key + LENGTH, -1);
            if (stringSetLength >= 0) {
                editor.remove(key + LENGTH);
                for (int i = 0; i < stringSetLength; i++) {
                    editor.remove(key + "[" + i + "]");
                }
            }
        }
        editor.remove(key);
        editor.apply();
    }
    public static boolean contains(Context context, String prefsName, final String key) {
        SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        return prefs.contains(key);
    }

    public static SharedPreferences.Editor clear(Context context, String prefsName) {
        SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit().clear();
        editor.apply();
        return editor;
    }


    public static String getString(Context context, String key, String defValue) {
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(context);
        return p.getString(key, defValue);
    }
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(context);
        return p.getBoolean(key, defValue);
    }
    public static long getLong(Context context, String key, long defValue) {
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(context);
        return p.getLong(key, defValue);
    }
    public static Double getDouble(Context context, String key, Double defValue) {
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(context);
        return Double.longBitsToDouble(p.getLong(key, Double.doubleToLongBits(defValue)));
    }
    public static float getFloat(Context context, String key, float defValue) {
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(context);
        return p.getFloat(key, defValue);
    }

    public static void putLong(Context context, String key, long value) {
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = p.edit();
        editor.putLong(key, value);
        editor.apply();
    }
    public static void putInt(Context context, String key, int value) {
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = p.edit();
        editor.putLong(key, value);
        editor.apply();
    }
    public static void putDouble(Context context, String key, double value) {
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = p.edit();
        editor.putLong(key, Double.doubleToRawLongBits(value));
        editor.apply();
    }
    public static void putFloat(Context context, String key, float value) {
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = p.edit();
        editor.putFloat(key, value);
        editor.apply();
    }
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = p.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    public static void putString(Context context, String key, String value) {
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = p.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public static void remove(Context context, final String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        if (prefs.contains(key + LENGTH)) {
            int stringSetLength = prefs.getInt(key + LENGTH, -1);
            if (stringSetLength >= 0) {
                editor.remove(key + LENGTH);
                for (int i = 0; i < stringSetLength; i++) {
                    editor.remove(key + "[" + i + "]");
                }
            }
        }
        editor.remove(key);
        editor.apply();
    }
    public static boolean contains(Context context, final String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.contains(key);
    }

    public static SharedPreferences.Editor clear(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit().clear();
        editor.apply();
        return editor;
    }
}
