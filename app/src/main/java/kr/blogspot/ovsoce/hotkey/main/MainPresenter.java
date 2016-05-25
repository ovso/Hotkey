package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

import com.fsn.cauly.CaulyAdView;

import kr.blogspot.ovsoce.hotkey.fragment.BaseFragment;

public interface MainPresenter {
    void onNavigationItemSelected(Context context, int id);
    void onCreate(Context context);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onTabSelected(int position);

    void onTabReselected(Context context, int position);

    void onClickEditNameOk(Context context, String name, int position);

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

        void showEditNameDialog(String name, int position);

        void setTabTitle(String name, int position);
    }
}
