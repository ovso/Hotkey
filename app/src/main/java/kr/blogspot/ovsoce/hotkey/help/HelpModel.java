package kr.blogspot.ovsoce.hotkey.help;

import android.content.Context;

import java.util.Locale;

public class HelpModel {
    public String getLanguage(Context context) {
        Locale locale = context.getResources().getConfiguration( ).locale;
        return locale.getLanguage();
    }
}
