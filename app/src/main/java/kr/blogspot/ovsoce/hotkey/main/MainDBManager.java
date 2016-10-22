package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;

import kr.blogspot.ovsoce.hotkey.db.DatabaseManager;

public class MainDBManager extends DatabaseManager {
    MainDBManager(Context context) {
        super(context);
    }

    @Override
    public void close() {

    }
}
