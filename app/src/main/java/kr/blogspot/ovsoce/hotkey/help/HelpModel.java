package kr.blogspot.ovsoce.hotkey.help;

import android.content.Context;

import java.util.Locale;

import kr.blogspot.ovsoce.hotkey.main.Model;

public class HelpModel extends Model {
    private final static String URL = "http://blog.naver.com/ovso/220902347204";
    public HelpModel(Context context) {
        super(context);
    }

    public String getLanguage(Context context) {
        Locale locale = context.getResources().getConfiguration( ).locale;
        return locale.getLanguage();
    }

    public String getHelpUrl() {
        return URL;
    }
}
