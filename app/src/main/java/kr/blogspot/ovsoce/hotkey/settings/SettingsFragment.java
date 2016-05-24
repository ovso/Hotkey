package kr.blogspot.ovsoce.hotkey.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.common.TypefaceUtil;

public class SettingsFragment extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);

        ListPreference fontsListPrefs = (ListPreference) findPreference("fonts");
        fontsListPrefs.setOnPreferenceChangeListener(this);
        String title = getFontName(fontsListPrefs, fontsListPrefs.getValue());
        fontsListPrefs.setTitle(title);

        ListPreference fontsSizeListPrefs = (ListPreference) findPreference("fonts_size");
        fontsSizeListPrefs.setOnPreferenceChangeListener(this);
        String fontsSizeListPrefsTitle = getFontSizeTitle(fontsSizeListPrefs, fontsSizeListPrefs.getValue());
        fontsSizeListPrefs.setTitle(fontsSizeListPrefsTitle);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        //Log.d(getClass().getSimpleName(), "newValue = " + newValue);
        Log.d("key = " + preference.getKey());
        Log.d("value = " + newValue);
        String key = preference.getKey();
        if(key.equals("fonts")) {
            TypefaceUtil.overrideFont(preference.getContext(), "SERIF", "fonts/"+newValue);
            String title = getFontName((ListPreference)findPreference("fonts"), newValue.toString());
            preference.setTitle(title);

        } else if(key.equals("fonts_size")) {
            TypefaceUtil.fontsSize(getActivity().getApplicationContext(), getFontsSize());
            String title = getFontSizeTitle((ListPreference)findPreference("fonts_size"), newValue.toString());
            preference.setTitle(title);

        }
        return true;
    }
    private float getFontsSize() {
        SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String fonts_size = s.getString("fonts_size", "1");
        float size = Float.parseFloat(fonts_size);
        return size;
    }

    private String getFontName(ListPreference preference, String newValue) {
        int lenth = preference.getEntries().length;
        int newValueIndex = 0;
        for (int i=0; i<lenth; i++) {
            if(preference.getEntryValues()[i].equals(newValue)) {
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
        for (int i=0; i<lenth; i++) {
            if(preference.getEntryValues()[i].equals(newValue)) {
                newValueIndex = i;
                break;
            }
        }
        return preference.getContext().getString(R.string.settings_fonts_size)
                + " : "
                + preference.getEntries()[newValueIndex].toString();
    }
}
