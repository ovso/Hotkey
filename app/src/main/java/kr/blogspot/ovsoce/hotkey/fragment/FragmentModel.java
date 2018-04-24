package kr.blogspot.ovsoce.hotkey.fragment;

import android.content.Context;

import java.util.List;

import kr.blogspot.ovsoce.hotkey.application.MyApplication;
import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItem;
import kr.blogspot.ovsoce.hotkey.main.Model;

public abstract class FragmentModel extends Model {

    public FragmentModel(Context context) {
        super(context);
    }

    public List<ContactsItem> getContactsItemList() {

        MyApplication app = (MyApplication) context.getApplicationContext();

        return app.getDatabaseHelper().getTableContactsItemList(getTabPosition());
    }
    public ContactsItem getContactsItem(int itemPosition) {
        MyApplication app = (MyApplication) context.getApplicationContext();
        ContactsItem item = app.getDatabaseHelper().getContactsItem(getTabPosition(), itemPosition);
        return item;
    }
    public String getPhoneNumber(int position) {
        return getContactsItem(position).getNumber().trim();
    }
    public abstract int getTabPosition();
    public abstract void setTabPosition(int id);
}
