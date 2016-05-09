package kr.blogspot.ovsoce.hotkey.settings;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.TypefaceUtil;
import kr.blogspot.ovsoce.hotkey.main.MainActivity;

public class SettingsFragment extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
        ListPreference listPreference = (ListPreference) findPreference("fonts");
        listPreference.setOnPreferenceChangeListener(this);
        String title = getFontName(listPreference, listPreference.getValue());
        listPreference.setTitle(title);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        //Log.d(getClass().getSimpleName(), "newValue = " + newValue);
        TypefaceUtil.overrideFont(preference.getContext(), "SERIF", "fonts/"+newValue);

        String title = getFontName((ListPreference)findPreference("fonts"), newValue.toString());
        preference.setTitle(title);
        return true;
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
}
