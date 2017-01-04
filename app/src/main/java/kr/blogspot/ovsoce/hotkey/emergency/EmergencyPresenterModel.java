package kr.blogspot.ovsoce.hotkey.emergency;

import android.content.Context;

import kr.blogspot.ovsoce.hotkey.main.Model;

class EmergencyPresenterModel extends Model {
    public EmergencyPresenterModel(Context context) {
        super(context);
    }

    public int getTabCount() {
        return 9;
    }
}
