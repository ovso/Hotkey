package kr.blogspot.ovsoce.hotkey.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.List;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.application.MyApplication;
import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItem;
import kr.blogspot.ovsoce.hotkey.main.Model;

public abstract class FragmentModel extends Model {

    public FragmentModel(Context context) {
        super(context);
    }

    public enum MESSAGE_TYPE{EMPTY_NUMBER};

    public int getGridLayoutSpanCount() {
        return mContext.getResources().getInteger(R.integer.recyclerview_gridlayout_spancount);
    }

    public List<ContactsItem> getContactsItemList() {

        MyApplication app = (MyApplication) mContext.getApplicationContext();

        return app.getDatabaseHelper().getTableContactsItemList(getMenuId());
    }
    public ContactsItem getContactsItem(int position) {
        ContactsItem item = null;
        MyApplication app = (MyApplication)mContext.getApplicationContext();
        item = app.getDatabaseHelper().getContactsItem(getMenuId(), position);
        return item;
    }
    public Intent getMakeACallIntent(int position) {
        String number = getContactsItem(position).getNumber().trim();
        //Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+number));
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number));

        if(number.length()>0) {
            return intent;
        } else {
            return null;
        }
    }
    public String getMessage(MESSAGE_TYPE type) {
        if(type == MESSAGE_TYPE.EMPTY_NUMBER){
            return mContext.getString(R.string.empty_number);
        }
        return null;
    }
    public abstract int getMenuId();
    public abstract void setMenuId(int id);
}
