package kr.blogspot.ovsoce.hotkey.main;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.fsn.cauly.CaulyAdView;

import kr.blogspot.ovsoce.hotkey.fragment.BaseFragment;

/**
 * Created by jaeho_oh on 2015-10-16.
 */
public interface MainPresenter {
    void onNavigationItemSelected(Context context, int id);
    void init(Context context);
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

    }
}
