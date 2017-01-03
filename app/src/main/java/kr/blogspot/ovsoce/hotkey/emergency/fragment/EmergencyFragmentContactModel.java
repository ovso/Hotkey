package kr.blogspot.ovsoce.hotkey.emergency.fragment;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import kr.blogspot.ovsoce.hotkey.R;

class EmergencyFragmentContactModel {
    private final static int TYPE_EMERGENCY = 0;
    private final static int TYPE_LIVING_INFO= 1;
    private final static int TYPE_COMPLAINTS = 2;
    private final static int TYPE_CHILD = 3;
    private final static int TYPE_TEEN = 4;
    private final static int TYPE_FEMALE = 5;
    private final static int TYPE_OLD_DISABLED = 6;
    private final static int TYPE_DISEASE_ADDICTED = 7;
    private final static int TYPE_FAMILY = 8;

    private Context mContext;
    EmergencyFragmentContactModel(Context context) {
        mContext = context;
    }

    @Nonnull
    public List<EmergencyContact> getContactList(int type) {
        switch (type) {
            case TYPE_EMERGENCY :
                return generationEmergencyContact();
            case TYPE_LIVING_INFO :
                return generationLivingInfoContact();
            case TYPE_COMPLAINTS :
                return generationComplaintsContact();
            case TYPE_CHILD :
                return generationChildContact();
            case TYPE_TEEN :
                return generationTeenContact();
            case TYPE_FEMALE :
                return generationFemaleContact();
            case TYPE_OLD_DISABLED :
                return generationOldDisabledContact();
            case TYPE_DISEASE_ADDICTED :
                return generationDiseaseAddictedContact();
            case TYPE_FAMILY :
                return generationFamilyContact();
            default:
                return null;
        }
    }

    private List<EmergencyContact> generationFamilyContact() {
        String[] array = mContext.getResources().getStringArray(R.array.family);
        return parseContact(array);
    }

    private List<EmergencyContact> generationDiseaseAddictedContact() {
        String[] array = mContext.getResources().getStringArray(R.array.disease_addicted);
        return parseContact(array);

    }

    private List<EmergencyContact> generationOldDisabledContact() {
        String[] array = mContext.getResources().getStringArray(R.array.oldman_disabled);
        return parseContact(array);
    }

    private List<EmergencyContact> generationFemaleContact() {
        String[] array = mContext.getResources().getStringArray(R.array.female);
        return parseContact(array);
    }

    private List<EmergencyContact> generationTeenContact() {
        String[] array = mContext.getResources().getStringArray(R.array.teen);
        return parseContact(array);
    }

    private List<EmergencyContact> generationChildContact() {
        String[] array = mContext.getResources().getStringArray(R.array.child);
        return parseContact(array);
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

    private int mType;
    public void setType(int type) {
        mType = type;
    }
    public int getType() {
        return mType;
    }

    public EmergencyContact getContact(int type, int itemPosition) {
        return getContactList(type).get(itemPosition);
    }
}
