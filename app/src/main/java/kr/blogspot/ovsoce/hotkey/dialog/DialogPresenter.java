package kr.blogspot.ovsoce.hotkey.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import kr.blogspot.ovsoce.hotkey.fragment.ContactsItem;

/**
 * Created by jaeho_oh on 2015-10-27.
 */
public interface DialogPresenter {
    void init(DialogFragment fragment);
    void setColorSelected(int colorPosition, android.view.View container);
    int setContacts(Context context, ContactsItem item);
    interface View {
        void initScrollView(String[] colors, int colorPosition);
        void setVisible(android.view.View v, int visible);
        void setName(String name);
        void setNumber(String number);

    }
}
