package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.Intent;

import com.fsn.cauly.CaulyAdView;

import kr.blogspot.ovsoce.hotkey.fragment.BaseFragment;

public interface MainPresenter {
    void onNavigationItemSelected(Context context, int id);
    void onCreate(Context context);
    void onClick(android.view.View v);

    interface View {
        void navigateToEmail(Intent intent);
        void navigateToShare(Intent intent);
        void navigateToReview(Intent intent);
        void navigateToNavMenuEdit();
        void replaceFragment(int containerViewId, BaseFragment fragment);
        void setToolbarTitle(String title);
        void setVersionName(String versionName);
        void initAd(CaulyAdView view);
        void onInit();
    }
}
