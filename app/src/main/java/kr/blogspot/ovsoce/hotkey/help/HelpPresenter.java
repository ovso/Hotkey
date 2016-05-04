package kr.blogspot.ovsoce.hotkey.help;

public interface HelpPresenter {
    void onClick(android.view.View view);

    void onCreate();

    void onOptionsItemSelected(int itemId);

    interface View {
        void onInit();
        void showToast(int resId);
        void showToast(String msg);

        void activityFinish();

        void initWebView(String url);
    }
}
