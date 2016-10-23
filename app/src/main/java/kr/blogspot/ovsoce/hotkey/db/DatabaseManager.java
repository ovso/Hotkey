package kr.blogspot.ovsoce.hotkey.db;

import android.content.Context;

import io.realm.Realm;

public abstract class DatabaseManager {
    protected Context mContext;
    protected Realm mRealm;
    protected DatabaseManager(Context context) {
        mContext = context;
        mRealm = Realm.getDefaultInstance();
    }
    public abstract void close();

}
