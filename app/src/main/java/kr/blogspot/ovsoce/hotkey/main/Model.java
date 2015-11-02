package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;

import kr.blogspot.ovsoce.hotkey.application.MyApplication;

/**
 * Created by jaeho_oh on 2015-11-02.
 */
public class Model {
    public String getVersionName(Context context) {
        return ((MyApplication)context).getVersionName();
    }
}
