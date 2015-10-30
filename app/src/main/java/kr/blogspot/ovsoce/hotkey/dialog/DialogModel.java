package kr.blogspot.ovsoce.hotkey.dialog;

import android.content.Context;

import kr.blogspot.ovsoce.hotkey.application.MyApplication;
import kr.blogspot.ovsoce.hotkey.db.DatabaseHelper;

/**
 * Created by jaeho_oh on 2015-10-27.
 */
public class DialogModel {
    public String[] getDefaultColors(Context context) {
        MyApplication app = (MyApplication)context.getApplicationContext();
        return app.getDatabaseHelper().getDefaultColors();
    }

    public DatabaseHelper getDatabaseHelper(Context context) {
        MyApplication app = (MyApplication) context.getApplicationContext();
        return app.getDatabaseHelper();
    }

}

