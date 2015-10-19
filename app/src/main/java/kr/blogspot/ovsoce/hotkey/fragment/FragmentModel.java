package kr.blogspot.ovsoce.hotkey.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import kr.blogspot.ovsoce.hotkey.R;

/**
 * Created by ovso on 2015. 10. 17..
 */
public class FragmentModel {

    private List<ContactsItem> contactsItemListData;
    public int getGridLayoutSpanCount(Context context) {
        return context.getResources().getInteger(R.integer.recyclerview_gridlayout_spancount);
    }
    public List<ContactsItem> getContactsItemListData() {
        //RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Populating our data set
        List<ContactsItem> dataItems = new ArrayList<ContactsItem>();

        dataItems.add(new ContactsItem("Indigo", "#3F51B5"));
        dataItems.add(new ContactsItem("Pink", "#E91E63"));
        dataItems.add(new ContactsItem("Orange", "#FF5722"));
        dataItems.add(new ContactsItem("Green", "#4CAF50"));
        dataItems.add(new ContactsItem("Grey", "#607D8B"));
        dataItems.add(new ContactsItem("Cyan", "#00BCD4"));
        dataItems.add(new ContactsItem("Amber", "#FFC107"));
        dataItems.add(new ContactsItem("Brown", "#795548"));
        dataItems.add(new ContactsItem("Blue", "#03A9F4"));
        dataItems.add(new ContactsItem("Red", "#F44336"));
        dataItems.add(new ContactsItem("Indigo", "#3F51B5"));
        dataItems.add(new ContactsItem("Pink", "#E91E63"));
        dataItems.add(new ContactsItem("Orange", "#FF5722"));
        dataItems.add(new ContactsItem("Green", "#4CAF50"));
        dataItems.add(new ContactsItem("Grey", "#607D8B"));
        dataItems.add(new ContactsItem("Cyan", "#00BCD4"));
        dataItems.add(new ContactsItem("Amber", "#FFC107"));
        dataItems.add(new ContactsItem("Brown", "#795548"));
        dataItems.add(new ContactsItem("Blue", "#03A9F4"));
        dataItems.add(new ContactsItem("Red", "#F44336"));

        // Creating new adapter object
        //MyAdapter myAdapter = new MyAdapter(dataItems);
        //recyclerView.setAdapter(myAdapter);

        // Setting the layoutManager
        //recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return dataItems;
    }
}
