package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.util.Log;

import kr.blogspot.ovsoce.hotkey.R;

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
    public void onNavigationItemSelected(Context context, int id) {
        if(id == R.id.nav_send) {
            mView.navigateToEmail(mModel.getEmailIntent(context));
        } else {
            mView.replaceFragment(mModel.getFragmentContainerViewId(), mModel.getFragment(id));
            Log.d("MainPresenterImpl", "onNavigationItemSelected ?");
        }
    }

}
