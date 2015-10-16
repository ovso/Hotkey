package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.Intent;

/**
 * Created by jaeho_oh on 2015-10-16.
 */
public interface MainPresenter {
    void sendToDeveloper(Context context);
    interface View {
        void callEmailActivity(Intent intent);
    }
}
