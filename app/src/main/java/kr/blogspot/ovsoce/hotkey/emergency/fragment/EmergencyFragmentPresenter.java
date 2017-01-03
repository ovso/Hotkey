package kr.blogspot.ovsoce.hotkey.emergency.fragment;

import android.content.Context;

import java.util.List;

public interface EmergencyFragmentPresenter {

    void onActivityCreate(int index);

    void onItemClick(int position);


    interface View {

        void setRecyclerView(List<EmergencyContact> contactList);

        Context getContext();

        void showMakeCallDialog(EmergencyContact contact);
    }
}
