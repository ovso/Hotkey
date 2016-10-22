package kr.blogspot.ovsoce.hotkey.db;

import android.content.Context;

public abstract class DatabaseManager {
    protected Context mContext;
    protected DatabaseManager(Context context) {
        mContext = context;
    }
    public abstract void close();

}
