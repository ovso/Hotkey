package kr.blogspot.ovsoce.hotkey.emergency.fragment;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import kr.blogspot.ovsoce.hotkey.R;

class EmergencyFragmentContactModel {

    private Context mContext;
    EmergencyFragmentContactModel(Context context) {
        mContext = context;
    }

    public List<EmergencyContact> getContactList(int index) {
        if(index == 0) {
            return generationEmergencyContact();
        } else if(index == 1) {
            return generationLivingInfoContact();
        } else {
            return generationComplaintsContact();
        }
    }

    private List<EmergencyContact> generationComplaintsContact() {
        String[] array = mContext.getResources().getStringArray(R.array.complaints);
        return parseContact(array);
    }

    private List<EmergencyContact> generationLivingInfoContact() {
        String[] array = mContext.getResources().getStringArray(R.array.living_info);
        return parseContact(array);

    }

    private List<EmergencyContact> generationEmergencyContact() {
        String[] array = mContext.getResources().getStringArray(R.array.emergency);
        return parseContact(array);

    }


    private List<EmergencyContact> parseContact(String[] strArray) {
        List<EmergencyContact> list = new ArrayList<>();
        for (int i = 0; i < strArray.length; i++) {
            EmergencyContact c = new EmergencyContact();
            c.receptionContents = strArray[i].split("/")[0];
            c.phoneNumber = strArray[i].split("/")[1];
            c.relatedInstitutions = strArray[i].split("/")[2];
            list.add(c);
        }
        return list;

    }
}
