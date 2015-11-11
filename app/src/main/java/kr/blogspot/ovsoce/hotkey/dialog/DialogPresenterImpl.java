package kr.blogspot.ovsoce.hotkey.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.fragment.ContactsItem;
import kr.blogspot.ovsoce.hotkey.fragment.ContactsItemImpl;

/**
 * Created by jaeho_oh on 2015-10-27.
 */
public class DialogPresenterImpl implements DialogPresenter {
    DialogPresenter.View mView;
    DialogModel mModel;

    public DialogPresenterImpl(DialogPresenter.View view) {
        mView = view;
        mModel = new DialogModel();
    }

    @Override
    public void init(DialogFragment fragment) {
        ContactsItem item = (ContactsItem) fragment.getArguments().getSerializable("item");
        mView.initScrollView(mModel.getDefaultColors(fragment.getActivity()), Integer.parseInt(item.getColor()));
        mView.setName(item.getName());
        mView.setNumber(item.getNumber());
    }

    @Override
    public void init(Context context, ContactsItem item) {
        mView.initScrollView(mModel.getDefaultColors(context), Integer.parseInt(item.getColor()));
        mView.setName(item.getName());
        mView.setNumber(item.getNumber());
    }

    @Override
    public void setColorSelected(int colorPosition, android.view.View container) {
        ViewGroup group = (ViewGroup) container;
        int itemCount = group.getChildCount();

        for (int i = 0; i < itemCount; i++) {
            mView.setVisible(group.getChildAt(i).findViewById(R.id.item_rect_select), android.view.View.GONE);
        }

        mView.setVisible(group.getChildAt(colorPosition).findViewById(R.id.item_rect_select), android.view.View.VISIBLE);

        container.setTag(String.valueOf(colorPosition));
    }

    @Override
    public int setContacts(Context context, ContactsItem item) {
        return mModel.getDatabaseHelper(context).updateContact(item);
    }

    @Override
    public void pickContacts(Context context) {
        mView.navigateToContacts(mModel.getContactsIntent(context));
    }

    @Override
    public void contactsResult(Context context, Intent data) {
        // Get the URI that points to the selected contact
        Uri contactUri = data.getData();
        // We only need the NUMBER column, because there will be only one row in the result
        String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};

        // Perform the query on the contact to get the NUMBER column
        // We don't need a selection or sort order (there's only one result for the given URI)
        // CAUTION: The query() method should be called from a separate thread to avoid blocking
        // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
        // Consider using CursorLoader to perform the query.
        Cursor cursor = context.getContentResolver().query(contactUri, projection, null, null, null);
        cursor.moveToFirst();

        // Retrieve the phone number from the NUMBER column
        int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        mView.setNumber(cursor.getString(column));

        column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        mView.setName(cursor.getString(column));
    }
}
