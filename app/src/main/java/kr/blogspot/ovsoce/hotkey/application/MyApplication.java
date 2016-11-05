package kr.blogspot.ovsoce.hotkey.application;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import io.realm.Realm;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.common.TypefaceUtil;
import kr.blogspot.ovsoce.hotkey.db.DatabaseHelper;

public class MyApplication extends Application {

    DatabaseHelper mDatabaseHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        mDatabaseHelper = new DatabaseHelper(getApplicationContext());
        mDatabaseHelper.getWritableDatabase();

        setFonts();

        Realm.init(this);

        Log.d("");
    }

    /**
     * 글꼴을 설정하는 메서드
     */
    private void setFonts() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String fonts = sharedPreferences.getString("fonts", "NanumBarunGothic.ttf");
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/" + fonts);
    }

    public DatabaseHelper getDatabaseHelper() {
        return mDatabaseHelper;
    }
}
