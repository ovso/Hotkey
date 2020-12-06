package kr.blogspot.ovsoce.hotkey.framework;

import android.content.Context;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Locale;
import java.util.StringTokenizer;

public class SystemUtils {

  public static String getLocaleToString(Context context) {
    Locale systemLocale = context.getResources().getConfiguration().locale;
    //String strDisplayCountry = systemLocale.getDisplayCountry();
    String strCountry = systemLocale.getCountry(); // KR
    String strLanguage = systemLocale.getLanguage(); // ko
    return strLanguage + "," + strCountry;
  }

  public static Locale getStringToLocale(String s) {
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

  public static boolean isDebuggable(Context context) {
    boolean debuggable = false;

    PackageManager pm = context.getPackageManager();
    try {
      ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
      debuggable = (0 != (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
    } catch (PackageManager.NameNotFoundException e) {
      /* debuggable variable will remain false */
    }

    return debuggable;
  }
}
