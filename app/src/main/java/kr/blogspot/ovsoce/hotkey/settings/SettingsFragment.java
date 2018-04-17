package kr.blogspot.ovsoce.hotkey.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.common.TypefaceUtil;

public class SettingsFragment extends PreferenceFragment
    implements Preference.OnPreferenceChangeListener {
  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    addPreferencesFromResource(R.xml.preferences);

    ListPreference fontsListPrefs = (ListPreference) findPreference("fonts");
    fontsListPrefs.setOnPreferenceChangeListener(this);
    String title = getFontName(fontsListPrefs, fontsListPrefs.getValue());
    fontsListPrefs.setTitle(title);
    SharedPreferences sharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(getActivity());
    String fonts = sharedPreferences.getString("fonts", "NanumPen.ttf");
    fontsListPrefs.setValue(fonts);


    ListPreference fontsSizeListPrefs = (ListPreference) findPreference("fonts_size");
    fontsSizeListPrefs.setOnPreferenceChangeListener(this);
    String fontsSizeListPrefsTitle =
        getFontSizeTitle(fontsSizeListPrefs, fontsSizeListPrefs.getValue());
    fontsSizeListPrefs.setTitle(fontsSizeListPrefsTitle);
    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    String fontSize = sharedPreferences.getString("fonts_size", "1.0");
    if(fontSize.equals("1")) fontSize = "1.0";
    fontsSizeListPrefs.setValue(fontSize);

    SwitchPreference autoEndPrefs = (SwitchPreference) findPreference("auto_end");
    autoEndPrefs.setOnPreferenceChangeListener(this);

    SwitchPreference ttsPrefs = (SwitchPreference) findPreference("tts");
    ttsPrefs.setOnPreferenceChangeListener(this);

  }

  @Override public boolean onPreferenceChange(Preference preference, Object newValue) {
    Log.d("key = " + preference.getKey());
    Log.d("value = " + newValue);
    String key = preference.getKey();
    if (key.equals("fonts")) {
      TypefaceUtil.overrideFont(preference.getContext(), "SERIF", "fonts/" + newValue);
      String title = getFontName((ListPreference) findPreference("fonts"), newValue.toString());
      preference.setTitle(title);
      showRestartDialog();
    } else if (key.equals("fonts_size")) {
      TypefaceUtil.fontsSize(getActivity().getApplicationContext(), getFontsSize());
      String title =
          getFontSizeTitle((ListPreference) findPreference("fonts_size"), newValue.toString());
      preference.setTitle(title);
      showRestartDialog();
    }

    return true;
  }

  private void showRestartDialog() {
    new AlertDialog.Builder(getActivity()).setMessage(R.string.settings_fonts_summary)
        .setPositiveButton(R.string.settings_btn_restart, new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialog, int which) {
            Activity a = getActivity();
            a.setResult(Activity.RESULT_OK, new Intent().putExtra("restart", true));
            a.finish();
          }
        })
        .setNegativeButton(R.string.settings_btn_cancel, new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialog, int which) {

          }
        })
        .show();
  }

  private float getFontsSize() {
    SharedPreferences s =
        PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
    String fonts_size = s.getString("fonts_size", "1");
    float size = Float.parseFloat(fonts_size);
    return size;
  }

  private String getFontName(ListPreference preference, String newValue) {
    int lenth = preference.getEntries().length;
    int newValueIndex = 0;
    for (int i = 0; i < lenth; i++) {
      if (preference.getEntryValues()[i].equals(newValue)) {
        newValueIndex = i;
        break;
      }
    }
    return preference.getContext().getString(R.string.settings_fonts)
        + " : "
        + preference.getEntries()[newValueIndex].toString();
  }

  private String getFontSizeTitle(ListPreference preference, String newValue) {
    int lenth = preference.getEntries().length;
    int newValueIndex = 0;
    for (int i = 0; i < lenth; i++) {
      if (preference.getEntryValues()[i].equals(newValue)) {
        newValueIndex = i;
        break;
      }
    }
    return preference.getContext().getString(R.string.settings_fonts_size)
        + " : "
        + preference.getEntries()[newValueIndex].toString();
  }
}
