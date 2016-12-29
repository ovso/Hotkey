package kr.blogspot.ovsoce.hotkey.reveiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import kr.blogspot.ovsoce.hotkey.common.Log;

public class PhoneStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("action = " + intent.getAction());
    }
}
