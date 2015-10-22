package kr.blogspot.ovsoce.hotkey.fragment;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.ContactsItem;
import kr.blogspot.ovsoce.hotkey.common.ContactsItemImpl;
import kr.blogspot.ovsoce.hotkey.db.DatabaseHelper;

/**
 * Created by ovso on 2015. 10. 17..
 */
public class FragmentModel {

    private List<ContactsItemImpl> contactsItemListData;
    public int getGridLayoutSpanCount(Context context) {
        return context.getResources().getInteger(R.integer.recyclerview_gridlayout_spancount);
    }

    public List<ContactsItem> getContactsItemListData() {

        List<ContactsItem> dataItems = new ArrayList<ContactsItem>();

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        return dataItems;
    }
    public List<ContactsItem> getDBInitContactsItemListData() {

        List<ContactsItem> dataItems = new ArrayList<ContactsItem>();

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        dataItems.add(new ContactsItemImpl("", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("","", "#795548"));
        dataItems.add(new ContactsItemImpl("","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("","", "#F44336"));

        return dataItems;
    }
    public int[] getMenuIds() {
        return new int[]{R.id.nav_family,R.id.nav_friends,R.id.nav_others};
    }

    DatabaseHelper dbHelper = null;
    public void init(Context context) {
        dbHelper = new DatabaseHelper(context, this);
    }

    /*
            dataItems.add(new ContactsItemImpl("Indigo", "", "#3F51B5"));
        dataItems.add(new ContactsItemImpl("Pink", "", "#E91E63"));
        dataItems.add(new ContactsItemImpl("Orange","", "#FF5722"));
        dataItems.add(new ContactsItemImpl("Green","", "#4CAF50"));
        dataItems.add(new ContactsItemImpl("Grey","", "#607D8B"));
        dataItems.add(new ContactsItemImpl("Cyan","", "#00BCD4"));
        dataItems.add(new ContactsItemImpl("Amber","", "#FFC107"));
        dataItems.add(new ContactsItemImpl("Brown","", "#795548"));
        dataItems.add(new ContactsItemImpl("Blue","", "#03A9F4"));
        dataItems.add(new ContactsItemImpl("Red","", "#F44336"));
     */
}
