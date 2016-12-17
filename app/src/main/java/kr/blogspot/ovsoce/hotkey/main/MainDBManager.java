package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.application.MyApplication;
import kr.blogspot.ovsoce.hotkey.db.DatabaseHelper;

public class MainDBManager{
    private Context mContext;
    MainDBManager(Context context) {
        mContext = context;
    }

    public String getTabName(int tabSelectedPosition) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        return prefs.getString("tab_"+tabSelectedPosition, mContext.getString(R.string.default_tab_name));
    }

    public void setTabName(String tabName, int tabSelectedPosition) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("tab_"+tabSelectedPosition, tabName);
        editor.apply();
    }

    public int getTabCount() {
        MyApplication app = (MyApplication) mContext.getApplicationContext();
        DatabaseHelper helper = app.getDatabaseHelper();
        return (int) helper.getTableCount();
    }

    public boolean createTable() {
        MyApplication app = (MyApplication) mContext.getApplicationContext();
        DatabaseHelper helper = app.getDatabaseHelper();
        return helper.createTable();
    }
}
