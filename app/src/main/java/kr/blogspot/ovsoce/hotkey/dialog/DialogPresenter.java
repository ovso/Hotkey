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
    void setSelected(int position, android.view.View container);
    void setContacts(Context context, String name, String number, int colorPosition);
    interface View {
        void initScrollView(String[] colors);
        void setVisible(android.view.View v, int visible);
    }
}
