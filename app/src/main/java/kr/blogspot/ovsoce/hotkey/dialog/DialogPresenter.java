package kr.blogspot.ovsoce.hotkey.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

/**
 * Created by jaeho_oh on 2015-10-27.
 */
public interface DialogPresenter {
    void init(Context context);
    void setColorSelected(int colorPosition, android.view.View container);
    void setContacts(Context context, String id, String name, String number, int colorPosition, int menuType);
    interface View {
        void initScrollView(String[] colors);
        void setVisible(android.view.View v, int visible);
    }
}
