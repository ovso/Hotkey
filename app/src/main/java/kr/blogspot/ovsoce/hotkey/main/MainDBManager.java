package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import kr.blogspot.ovsoce.hotkey.db.DatabaseManager;

public class MainDBManager extends DatabaseManager {
    MainDBManager(Context context) {
        super(context);
    }

    @Override
    public void close() {

    }

    public String getTabName(int tabSelectedPosition) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        String defValue = mContext.getString(MainActivity.DEFAULT_TITLE_RES_ID[tabSelectedPosition]);
        return prefs.getString("tab_"+tabSelectedPosition, defValue);
    }

    public void setTabName(String tabName, int tabSelectedPosition) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("tab_"+tabSelectedPosition, tabName);
        editor.apply();
    }
}
