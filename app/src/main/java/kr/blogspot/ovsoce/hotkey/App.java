package kr.blogspot.ovsoce.hotkey;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.google.android.gms.ads.MobileAds;
import io.fabric.sdk.android.Fabric;
import kr.blogspot.ovsoce.hotkey.framework.SystemUtils;
import kr.blogspot.ovsoce.hotkey.framework.TypefaceUtil;
import kr.blogspot.ovsoce.hotkey.db.DatabaseHelper;
import lombok.Getter;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {
  public static boolean DEBUG = false;
  private DatabaseHelper mDatabaseHelper;
  @Getter private static App instance;

  @Override public void onCreate() {
    super.onCreate();
    Fabric.with(this, new Crashlytics());
    mDatabaseHelper = new DatabaseHelper(getApplicationContext());
    Stetho.initializeWithDefaults(this);
    instance = this;
    setFont();
    setFontSize();
    initDebuggable();
    initAdmob();
  }

  private void initAdmob() {
    MobileAds.initialize(getApplicationContext(), Security.ADMOB_APP_ID.getValue());
  }

  private void initDebuggable() {
    this.DEBUG = SystemUtils.isDebuggable(this);
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
