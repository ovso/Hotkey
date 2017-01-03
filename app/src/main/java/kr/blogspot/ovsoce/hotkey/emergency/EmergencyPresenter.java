package kr.blogspot.ovsoce.hotkey.emergency;

import android.content.Context;

public interface EmergencyPresenter {

    void onCreate();

    interface View {

        void setRootView();
        void setToolbar();
        void setViewPager();
        void setTabLayout();
        Context getContext();
    }
}
