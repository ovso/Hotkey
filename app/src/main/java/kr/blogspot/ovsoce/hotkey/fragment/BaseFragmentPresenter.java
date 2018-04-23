package kr.blogspot.ovsoce.hotkey.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;

public interface BaseFragmentPresenter extends FragmentPresenter {
    void onAdapterItemClick(int position);

    void onAdapterItemLongClick(int position);

    void onDetach();

    interface View extends FragmentPresenter.View {

        Context getContext();

        Activity getActivity();

        void showPermissionAlert(@StringRes int resId);
    }
}
