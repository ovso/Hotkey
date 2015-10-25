package kr.blogspot.ovsoce.hotkey.fragment;

import android.app.Fragment;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.application.MyApplication;

/**
 * Created by ovso on 2015. 10. 17..
 */
public abstract class FragmentModel {

    public int getGridLayoutSpanCount(Context context) {
        return context.getResources().getInteger(R.integer.recyclerview_gridlayout_spancount);
    }

    public List<ContactsItem> getContactsItemList(Context context) {

        MyApplication app = (MyApplication) context.getApplicationContext();

        return app.getDatabaseHelper().getTableContactsItemList(getMenuId());
        //return app.getDatabaseHelper().getDummyData();
    }
    public ContactsItem getContactsItem(Context context, int position) {
        ContactsItem item = null;
        MyApplication app = (MyApplication)context.getApplicationContext();
        item = app.getDatabaseHelper().getContactsItem(getMenuId(), position);
        return item;
    }
    public abstract int getMenuId();
}
