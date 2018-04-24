package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.application.MyApplication;
import kr.blogspot.ovsoce.hotkey.framework.Prefs;
import kr.blogspot.ovsoce.hotkey.db.DatabaseHelper;
import kr.blogspot.ovsoce.hotkey.framework.ObjectUtils;

public class MainDBManager {
    private Context mContext;

    MainDBManager(Context context) {
        mContext = context;
    }

    public String getTabName(int tabSelectedPosition) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        Resources res = mContext.getResources();
        String key = "";
        String defValue;
        if(tabSelectedPosition < MainActivity.DEFAULT_TITLE_RES_ID.length) {
            String oldName = prefs.getString("tab_"+tabSelectedPosition, null);
            if(ObjectUtils.isEmpty(oldName)) {
                key = getTableName(tabSelectedPosition);
            } else {
                Prefs.putString(mContext, getTableName(tabSelectedPosition), oldName);
                key = getTableName(tabSelectedPosition);
            }
            defValue = res.getString(MainActivity.DEFAULT_TITLE_RES_ID[tabSelectedPosition]);
        } else {
            key = getTableName(tabSelectedPosition);
            defValue = res.getString(R.string.default_tab_name);
        }
        return prefs.getString(key, defValue);
    }

    public void setTabName(String tabName, int tabSelectedPosition) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(getTableName(tabSelectedPosition), tabName);
        editor.apply();
    }
    private String getTableName(int tabPosition) {
        DatabaseHelper helper = getDbHelper();
        return helper.getTableName(tabPosition);
    }
    public int getTabCount() {
        return (int) getDbHelper().getTableCount();
    }

    public boolean createTable() {
        return getDbHelper().createTable();
    }

    public boolean deleteTable(int tabPosition) {
        return getDbHelper().deleteTable(tabPosition);
    }

    private DatabaseHelper getDbHelper() {
        return MyApplication.getInstance().getDatabaseHelper();
    }

    public List<String> getPageTitleList(int tabCount) {
        List<String> pageTitleList = new ArrayList<>();
        for (int i = 0; i < tabCount; i++) {
            int tabPosition = i;
            pageTitleList.add(getTabName(tabPosition));
        }

        return pageTitleList;
    }
}
