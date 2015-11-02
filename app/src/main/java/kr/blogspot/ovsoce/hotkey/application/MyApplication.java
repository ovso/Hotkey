package kr.blogspot.ovsoce.hotkey.application;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import kr.blogspot.ovsoce.hotkey.db.DatabaseHelper;

/**
 * Created by ovso on 2015. 10. 22..
 */
public class MyApplication extends Application {

    DatabaseHelper mDatabaseHelper;
    @Override
    public void onCreate() {
        super.onCreate();

        mDatabaseHelper = new DatabaseHelper(getApplicationContext());
        mDatabaseHelper.getWritableDatabase();

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
}
