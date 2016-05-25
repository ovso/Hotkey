package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.Intent;

import com.fsn.cauly.CaulyAdView;

import kr.blogspot.ovsoce.hotkey.fragment.BaseFragment;

public interface MainPresenter {
    void onNavigationItemSelected(Context context, int id);
    void onCreate(Context context);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onTabSelected(int position);

    void onTabReselected(int position);

    interface View {
        void navigateToEmail(Intent intent);
        void navigateToShare(Intent intent);
        void navigateToReview(Intent intent);
        void navigateToHelp();
        void setToolbarTitle(String title);
        void setVersionName(String versionName);
        void initAd(CaulyAdView view);
        void onInit();

        void navigateToSettings();

        void restart();

        void setViewPagerCurrentItem(int position);

        void showEditNameDialog(int position);
    }
}
