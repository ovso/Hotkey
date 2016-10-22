package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import kr.blogspot.ovsoce.hotkey.application.MyApplication;

public class Model {
    protected Context mContext;
    public Model(Context context) {
        mContext = context;
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
