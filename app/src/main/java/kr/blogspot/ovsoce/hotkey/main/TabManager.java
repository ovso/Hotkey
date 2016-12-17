package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;

public class TabManager {
    public final static int BUTTON_TYPE_OK = -1;
    public final static int BUTTON_TYPE_CANCEL = -2;
    public final static int BUTTON_TYPE_DEL = -3;

    private MainDBManager mDBManager;
    private Context mContext;
    private int tabSelectedPosition;


    public TabManager(Context context, MainDBManager dbManager) {
        mContext = context;
        mDBManager = dbManager;
    }

    public void setTabSelectedPosition(int tabSelectedPosition) {
        this.tabSelectedPosition = tabSelectedPosition;
    }

    public int getTabSelectedPosition() {
        return tabSelectedPosition;
    }

    public boolean isRemoveTab() {
        if(tabSelectedPosition < MainActivity.DEFAULT_TITLE_RES_ID.length) return false;
        else return true;
    }
}
