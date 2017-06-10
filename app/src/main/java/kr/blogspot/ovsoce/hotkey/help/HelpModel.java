package kr.blogspot.ovsoce.hotkey.help;

import android.content.Context;

import java.util.List;
import java.util.Locale;

import kr.blogspot.ovsoce.hotkey.main.Model;

public class HelpModel extends Model {
    public HelpModel(Context context) {
        super(context);
    }

    public String getHelpText(List<String> texts) {
        String helpText = "";
        for (String text : texts) {
            helpText += text + "\n\n";
        }

        return helpText;
    }
}
