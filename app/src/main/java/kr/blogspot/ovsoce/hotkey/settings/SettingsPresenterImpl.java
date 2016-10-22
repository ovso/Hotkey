package kr.blogspot.ovsoce.hotkey.settings;

import android.content.Context;

public class SettingsPresenterImpl implements SettingsPresenter {
    private SettingsPresenter.View mView;
    private SettingsModel mModel;
    public SettingsPresenterImpl(View view) {
        mView = view;
        mModel = new SettingsModel(mView.getContext());
    }

    @Override
    public void onCreate(Context context) {

    }

    @Override
    public void onOptionsItemSelected(int itemId) {
        if(itemId == android.R.id.home) {
            mView.activityFinish();
        }
    }
}
