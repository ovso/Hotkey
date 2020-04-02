package kr.blogspot.ovsoce.hotkey.emergency.fragment;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.StringRes;

import java.util.List;

public interface EmergencyFragmentPresenter {

    void onActivityCreate(int index);

    void onItemClick(int position);


    interface View {

        void setRecyclerView(List<EmergencyContact> contactList);

        Context getContext();

        void showMakeCallDialog(EmergencyContact contact);

        Activity getActivity();

        void showPermissionAlert(@StringRes int resId);
    }
}
