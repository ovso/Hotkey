package kr.blogspot.ovsoce.hotkey.dialog;

import android.content.Context;
import android.content.Intent;

import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItem;

/**
 * Created by jaeho_oh on 2015-10-27.
 */
public interface DialogPresenter {
    void init(Context context, ContactsItem item);
    void setColorSelected(int colorPosition, android.view.View container);
    int setContacts(Context context, ContactsItem item);
    void pickContacts(Context context);
    void contactsResult(Context context, Intent data);
    void onClickSMS(android.view.View v, String number);
    interface View {
        void setContentView();
        void setDialogTitle(String title);
        void initScrollView(String[] colors, int colorPosition);
        void setVisible(android.view.View v, int visible);
        void setName(String name);
        void setNumber(String number);
        void navigateToContacts(Intent intent);
        void navigateToSMS(Intent intent);
        void showToast(String msg);

        Context getContext();
    }
}
