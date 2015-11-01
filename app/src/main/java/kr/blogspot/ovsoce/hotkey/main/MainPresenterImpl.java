package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.application.MyApplication;
import kr.blogspot.ovsoce.hotkey.common.Log;

/**
 * Created by jaeho_oh on 2015-10-16.
 */
public class MainPresenterImpl implements MainPresenter {
    MainPresenter.View mView;
    MainModel mModel;

    MainPresenterImpl(MainPresenter.View view) {
        mView = view;
        mModel = new MainModel();
    }

    @Override
    public void onNavigationItemSelected(Context context, int menuId) {
        if(menuId == R.id.nav_send) {
            mView.navigateToEmail(mModel.getEmailIntent(context));
            MyApplication application = (MyApplication)context.getApplicationContext();
            application.getDatabaseHelper().exportDB(context);
        } else {
            mView.replaceFragment(mModel.getFragmentContainerViewId(), mModel.getFragment(menuId));
        }

        mView.setToolbarTitle(context.getString(R.string.app_name)+" : "+mModel.getToolbarTitle(context, menuId));
    }

}
