package kr.blogspot.ovsoce.hotkey.application;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.facebook.stetho.Stetho;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.TypefaceUtil;
import kr.blogspot.ovsoce.hotkey.db.DatabaseHelper;

public class MyApplication extends Application {

    private DatabaseHelper mDatabaseHelper;
    private Tracker mTracker;
    @Override
    public void onCreate() {
        super.onCreate();
        mDatabaseHelper = new DatabaseHelper(getApplicationContext());
        Stetho.initializeWithDefaults(this);
        setFonts();
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

    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }
}
