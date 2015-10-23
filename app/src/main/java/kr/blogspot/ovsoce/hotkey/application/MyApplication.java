package kr.blogspot.ovsoce.hotkey.application;

import android.app.Application;
import android.widget.Toast;

import kr.blogspot.ovsoce.hotkey.R;
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
}
