package kr.blogspot.ovsoce.hotkey.fragment;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.application.MyApplication;

/**
 * Created by ovso on 2015. 10. 17..
 */
public class FragmentModel {

    private List<ContactsItemImpl> contactsItemListData;
    public int getGridLayoutSpanCount(Context context) {
        return context.getResources().getInteger(R.integer.recyclerview_gridlayout_spancount);
    }

    public List<ContactsItem> getContactsItemListData(Context context) {

        MyApplication app = (MyApplication) context.getApplicationContext();
        int type = R.id.nav_family;

        //return app.getDatabaseHelper().getTableContactsItemList(context, type);
        return app.getDatabaseHelper().getDummyData();
    }
}
