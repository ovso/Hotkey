package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.framework.Log;
import kr.blogspot.ovsoce.hotkey.framework.Prefs;
import kr.blogspot.ovsoce.hotkey.framework.TypefaceUtil;
import kr.blogspot.ovsoce.hotkey.framework.ObjectUtils;

public class MainModel extends Model {

  MainModel(Context context) {
    super(context);
  }

  public void setFontsSize() {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    float fonts_size = Float.parseFloat(sharedPreferences.getString("fonts_size", "1.0"));
    Log.d("fonts_size = " + fonts_size);
    TypefaceUtil.fontsSize(context, fonts_size);
  }

  @Override
  public String getVersionName() {
    return new StringBuilder(context.getString(R.string.app_ver))
        .append(super.getVersionName()).toString();
  }

  public boolean isAppExit(Intent intent) {
    String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
    if (!ObjectUtils.isEmpty(state) && state.equals("IDLE")) {
      return Prefs.getBoolean(context, "auto_end", false);
    } else {
      return false;
    }
  }
}
