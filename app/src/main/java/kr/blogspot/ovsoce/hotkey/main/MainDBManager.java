package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MainDBManager{
    private Context mContext;
    MainDBManager(Context context) {
        mContext = context;
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
