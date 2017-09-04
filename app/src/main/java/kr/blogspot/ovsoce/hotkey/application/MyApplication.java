package kr.blogspot.ovsoce.hotkey.application;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.facebook.stetho.Stetho;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.TypefaceUtil;
import kr.blogspot.ovsoce.hotkey.db.DatabaseHelper;
import lombok.Getter;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApplication extends Application {

  private DatabaseHelper mDatabaseHelper;
  @Getter private static MyApplication instance;

  @Override public void onCreate() {
    super.onCreate();
    mDatabaseHelper = new DatabaseHelper(getApplicationContext());
    Stetho.initializeWithDefaults(this);
    instance = this;
    setFont();
    setFontSize();
  }

  private void setFontSize() {
    SharedPreferences sharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    float fonts_size = Float.parseFloat(sharedPreferences.getString("fonts_size", "1.0"));
    TypefaceUtil.fontsSize(getApplicationContext(), fonts_size);
    sharedPreferences.edit().putString("fonts_size", String.valueOf(fonts_size)).apply();
  }

  /**
   * 글꼴을 설정하는 메서드
   */
  public static void setFont() {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(instance);
    String fonts = sharedPreferences.getString("fonts", "NanumBarunGothic.ttf");

    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath(fonts)
        .setFontAttrId(R.attr.fontPath)
        .build());
    sharedPreferences.edit().putString("fonts", fonts).apply();
  }

  public DatabaseHelper getDatabaseHelper() {
    return mDatabaseHelper;
  }
}
