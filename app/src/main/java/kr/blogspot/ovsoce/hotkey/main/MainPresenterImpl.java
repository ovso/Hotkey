package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.widget.Toast;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Prefs;
import kr.blogspot.ovsoce.hotkey.common.TypefaceUtil;

public class MainPresenterImpl implements MainPresenter {
    MainPresenter.View mView;
    MainModel mModel;

    MainPresenterImpl(MainPresenter.View view) {
        mView = view;
        mModel = new MainModel();
    }

    @Override
    public void onNavigationItemSelected(Context context, int menuId) {
        if(menuId == R.id.nav_share) {
            mView.navigateToShare(mModel.getShareIntent(context));
        } else if(menuId == R.id.nav_review) {
            mView.navigateToReview(mModel.getReviewIntent(context));
        } else if(menuId == R.id.nav_help) {
            //mView.replaceFragment(mModel.getFragmentContainerViewId(), mModel.getFragment(menuId));
            //mView.setToolbarTitle(context.getString(R.string.app_name) + " : " + mModel.getToolbarTitle(context, menuId));
            mView.navigateToHelp();
        } else if(menuId == R.id.nav_settings) {
            mView.navigateToSettings();
        }
    }

    @Override
    public void onCreate(Context context) {
        TypefaceUtil.overrideFont(context, "SERIF", "fonts/"+ Prefs.getString(context, "fonts", "NanumBarunGothic.ttf"));

        mView.onInit();
        mView.setVersionName(context.getString(R.string.app_ver)+mModel.getVersionName(context));
        mView.initAd(mModel.getCaulyAdView(context));
    }
}
