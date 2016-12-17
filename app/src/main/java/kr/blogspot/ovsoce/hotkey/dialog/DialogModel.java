package kr.blogspot.ovsoce.hotkey.dialog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.application.MyApplication;
import kr.blogspot.ovsoce.hotkey.db.DatabaseHelper;

public class DialogModel {
    public String[] getDefaultColors(Context context) {
        MyApplication app = (MyApplication)context.getApplicationContext();
        return app.getDatabaseHelper().getDefaultColors();
    }

    public DatabaseHelper getDatabaseHelper(Context context) {
        MyApplication app = (MyApplication) context.getApplicationContext();
        return app.getDatabaseHelper();
    }

    public Intent getContactsIntent(Context context) {
        Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers

        return intent;
    }
    public Intent getSMSIntent(Context context, String number) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:"+number));
        context.startActivity(intent);
        return intent;
    }
    public String getTitle(Context context) {
        return context.getString(R.string.dialog_title);
    }
    public String getSMSToastMsg(Context context) {
        return context.getString(R.string.empty_number);
    }
}

