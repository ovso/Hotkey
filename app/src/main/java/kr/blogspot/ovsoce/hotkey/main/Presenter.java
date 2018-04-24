package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;

public interface Presenter {
    void onCreate(Context context);

    void onOptionsItemSelected(int itemId);

    interface View {
        void onInit();
        void showToast(String msg);
        void showToast(int resId);
        void activityFinish();
    }
}
