package kr.blogspot.ovsoce.hotkey.emergency;

import android.content.Context;

class EmergencyPresenterModel {
    private Context mContext;
    public EmergencyPresenterModel(Context context) {
        mContext = context;
    }

    public int getTabCount() {
        return 9;
    }
}
