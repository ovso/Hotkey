package kr.blogspot.ovsoce.hotkey.dialog;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

import kr.blogspot.ovsoce.hotkey.R;
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

