package kr.blogspot.ovsoce.hotkey.application;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.PreferenceManager;

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
    }
    /**
     * 글꼴을 설정하는 메서드
     */
    private void setFonts() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String fonts = sharedPreferences.getString("fonts", "NanumBarunGothic.ttf");
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/"+fonts);
    }

    public DatabaseHelper getDatabaseHelper() {
        return mDatabaseHelper;
    }

    public String getVersionName() {
        PackageManager manager = getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public int getSDK_INT() {
        return Build.VERSION.SDK_INT;
    }
}
