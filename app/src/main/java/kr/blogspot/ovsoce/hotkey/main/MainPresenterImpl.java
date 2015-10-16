package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;

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
    public void sendToDeveloper(Context context) {
        mView.callEmailActivity(mModel.getEmailIntent(context));
    }
}
