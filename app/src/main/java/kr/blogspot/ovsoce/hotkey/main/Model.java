package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;

import kr.blogspot.ovsoce.hotkey.application.MyApplication;

public class Model {
    public String getVersionName(Context context) {
        return ((MyApplication)context).getVersionName();
    }
}
