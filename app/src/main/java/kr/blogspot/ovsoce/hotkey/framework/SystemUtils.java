package kr.blogspot.ovsoce.hotkey.framework;

import android.content.Context;

import java.util.Locale;
import java.util.StringTokenizer;

import hugo.weaving.DebugLog;

public class SystemUtils {

    public static String getLocaleToString(Context context) {
        Locale systemLocale = context.getResources().getConfiguration().locale;
        //String strDisplayCountry = systemLocale.getDisplayCountry();
        String strCountry = systemLocale.getCountry(); // KR
        String strLanguage = systemLocale.getLanguage(); // ko
        return strLanguage + "," + strCountry;
    }

    @DebugLog public static Locale getStringToLocale(String s) {
        StringTokenizer tempStringTokenizer = new StringTokenizer(s, ",");
        String language = null;
        String country = null;
        if (tempStringTokenizer.hasMoreTokens()) {
            language = tempStringTokenizer.nextElement().toString();
        }
        if (tempStringTokenizer.hasMoreTokens()) {
            country = tempStringTokenizer.nextElement().toString();
        }

        return new Locale(language, country);
    }
}
