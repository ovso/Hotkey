package kr.blogspot.ovsoce.hotkey.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;

import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItem;

public class DialogPresenterImpl implements DialogPresenter {
  DialogPresenter.View mView;
  DialogModel mModel;

  public DialogPresenterImpl(DialogPresenter.View view) {
    mView = view;
    mModel = new DialogModel(mView.getContext());
  }

  @Override
  public void init(Context context, ContactsItem item) {
    mView.setContentView();
    mView.setDialogTitle(mModel.getTitle(context));
    mView.setName(item.getName());
    mView.setNumber(item.getNumber());
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
    @SuppressLint("Recycle") Cursor cursor = context.getContentResolver().query(contactUri, projection, null, null, null);
    cursor.moveToFirst();

    // Retrieve the phone number from the NUMBER column
    int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
    mView.setNumber(cursor.getString(column));

    column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
    mView.setName(cursor.getString(column));
  }

  @Override
  public void onClickSMS(android.view.View v, String number) {
    if (!TextUtils.isEmpty(number)) {
      mView.navigateToSMS(mModel.getSMSIntent(v.getContext(), number));
    } else {
      mView.showToast(mModel.getSMSToastMsg(v.getContext()));
    }
  }

  @Override
  public void onClickContactsInit() {
    mView.setEmptyContacts();
  }
}
