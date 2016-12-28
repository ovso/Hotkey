package kr.blogspot.ovsoce.hotkey.help;

import android.content.Context;

public interface HelpPresenter {
    void onClick(android.view.View view);

    void onCreate(Context context);

    void onOptionsItemSelected(int itemId);

    interface View {
        void onInit();
        void showToast(int resId);
        void showToast(String msg);

        void activityFinish();

        void initWebView(String url);

        Context getContext();
    }
}
