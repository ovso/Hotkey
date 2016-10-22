package kr.blogspot.ovsoce.hotkey.settings;

import android.content.Context;

import kr.blogspot.ovsoce.hotkey.main.Presenter;

public interface SettingsPresenter extends Presenter {

    interface View extends Presenter.View {

        Context getContext();
    }
}
