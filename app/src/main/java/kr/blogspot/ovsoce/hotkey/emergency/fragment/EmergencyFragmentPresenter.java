package kr.blogspot.ovsoce.hotkey.emergency.fragment;

import android.content.Context;

import java.util.List;

public interface EmergencyFragmentPresenter {

    void onActivityCreate(int index);

    interface View {

        void setRecyclerView(List<EmergencyContact> contactList);

        Context getContext();
    }
}
