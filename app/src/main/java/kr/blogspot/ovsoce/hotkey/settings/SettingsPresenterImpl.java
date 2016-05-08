package kr.blogspot.ovsoce.hotkey.settings;

import android.content.Context;

import kr.blogspot.ovsoce.hotkey.R;

public class SettingsPresenterImpl implements SettingsPresenter {
    private SettingsPresenter.View mView;
    private SettingsModel mModel;
    public SettingsPresenterImpl(View view) {
        mView = view;
        mModel = new SettingsModel();
    }

    @Override
    public void onCreate(Context context) {

    }

    @Override
    public void onOptionsItemSelected(int itemId) {
        if(itemId == R.id.toolbar) {
            mView.activityFinish();
        }
    }
}
