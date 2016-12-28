package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import kr.blogspot.ovsoce.hotkey.application.MyApplication;

public class Model {
    protected Context mContext;
    public Model(Context context) {
        mContext = context;
        initAnalytics();
    }

    private Tracker mTracker;
    private void initAnalytics() {
        MyApplication application = (MyApplication)mContext.getApplicationContext();
        mTracker = application.getDefaultTracker();
    }
    public void setScreenTracker(String screenName) {
        mTracker.setScreenName(screenName);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
    public void setSendEventTracker(String action) {
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction(action)
                .build());
    }
    public String getVersionName() {
        PackageManager manager = mContext.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
