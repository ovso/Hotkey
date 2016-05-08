package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.speech.tts.Voice;

public interface Presenter {
    void onCreate(Context context);
    interface View {
        void onInit();
        void showToast(String msg);
        void showToast(int resId);
        void activityFinish();
    }
}
