package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.Intent;

import com.fsn.cauly.CaulyAdView;

public interface MainPresenter {
    void onNavigationItemSelected(int id);
    void onCreate();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onTabSelected(int position);

    void onTabReselected(int position);

    void onClickEditNameOk(String name, int position);

    void addTab();

    interface View {
        void navigateToEmail(Intent intent);
        void navigateToShare(Intent intent);
        void navigateToReview(Intent intent);
        void navigateToHelp();
        void setVersionName(String versionName);
        void initAd(CaulyAdView view);

        void navigateToSettings();

        void restart();

        void setViewPagerCurrentItem(int position);

        void showEditNameDialog(String name, int position);

        void setTabTitle(String name, int position);

        void showToast(int resId);
        void showToast(String msg);

        void showEditNameError(int resId);

        Context getContext();

        void setToolbar();

        void setDrawableLayout();

        void setListener();

        void setViewPager();

        void setTabLayout();
    }
}
