package kr.blogspot.ovsoce.hotkey.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.List;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.application.MyApplication;
import kr.blogspot.ovsoce.hotkey.main.Model;

public abstract class FragmentModel extends Model {

    public enum MESSAGE_TYPE{EMPTY_NUMBER};

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
    public Intent getMakeACallIntent(Context context, int position) {
        String number = getContactsItem(context, position).getNumber().trim();
        //Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+number));
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number));

        if(number.length()>0) {
            return intent;
        } else {
            return null;
        }
    }
    public String getMessage(Context context, MESSAGE_TYPE type) {
        if(type == MESSAGE_TYPE.EMPTY_NUMBER){
            return context.getString(R.string.empty_number);
        }
        return null;
    }
    public abstract int getMenuId();
    public abstract void setMenuId(int id);
}
