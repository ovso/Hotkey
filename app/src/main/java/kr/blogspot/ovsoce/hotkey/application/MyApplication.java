package kr.blogspot.ovsoce.hotkey.application;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.facebook.stetho.Stetho;
import kr.blogspot.ovsoce.hotkey.common.TypefaceUtil;
import kr.blogspot.ovsoce.hotkey.db.DatabaseHelper;

public class MyApplication extends Application {

  private DatabaseHelper mDatabaseHelper;

  @Override public void onCreate() {
    super.onCreate();
    mDatabaseHelper = new DatabaseHelper(getApplicationContext());
    Stetho.initializeWithDefaults(this);
    setFonts();
  }

  /**
   * 글꼴을 설정하는 메서드
   */
  private void setFonts() {
    SharedPreferences sharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    String fonts = sharedPreferences.getString("fonts", "NanumBarunGothic.ttf");
    TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/" + fonts);
  }

  public DatabaseHelper getDatabaseHelper() {
    return mDatabaseHelper;
  }
}
