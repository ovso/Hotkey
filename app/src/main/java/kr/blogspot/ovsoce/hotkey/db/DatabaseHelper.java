package kr.blogspot.ovsoce.hotkey.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.ContactsItem;
import kr.blogspot.ovsoce.hotkey.common.ContactsItemImpl;
import kr.blogspot.ovsoce.hotkey.fragment.FragmentModel;

/**
 * Created by jaeho_oh on 2015-10-22.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "hotkey.db";

    private static final String FILE_PATH = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + File.separator + DATABASE_NAME;

    // Contacts table name
    private static final String TABLE_FAMILY = "family";
    private static final String TABLE_FRIENDS = "friends";
    private static final String TABLE_OTHERS = "others";
    //private static final String TABLE_WHO = "who";

    // Contacts Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_COLOR = "color";

    private final ArrayList<ContactsItem> mContactsItemList = new ArrayList<ContactsItem>();
    private FragmentModel mModel;
    private Context mContext;
    public DatabaseHelper(Context context, FragmentModel model) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mModel = model;
        mContext = context;
        getWritableDatabase();
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAMILY_TABLE = "CREATE TABLE " + TABLE_FAMILY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_NUMBER + " TEXT," + KEY_COLOR + " TEXT" + ")";
        db.execSQL(CREATE_FAMILY_TABLE);

        String CREATE_FRIENDS_TABLE = "CREATE TABLE " + TABLE_FRIENDS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_NUMBER + " TEXT," + KEY_COLOR + " TEXT" + ")";
        db.execSQL(CREATE_FRIENDS_TABLE);

        String CREATE_OTHERS_TABLE = "CREATE TABLE " + TABLE_OTHERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_NUMBER + " TEXT," + KEY_COLOR + " TEXT" + ")";
        db.execSQL(CREATE_OTHERS_TABLE);

//        String CREATE_WHO_TABLE = "CREATE TABLE " + TABLE_WHO + "("
//                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
//                + KEY_NUMBER + " TEXT," + KEY_COLOR + " TEXT" + ")";
//
//        db.execSQL(CREATE_WHO_TABLE);
        Toast.makeText(mContext, "DatabseHelper", Toast.LENGTH_LONG).show();
        initDB();
    }

    private void initDB() {
        List<ContactsItem> list = mModel.getDBInitContactsItemListData();
        int total = 0;
        for(int i=0; i<mModel.getMenuIds().length; i++) {
            for (int j=0; j<list.size(); j++) {
                updateContact(list.get(j), mModel.getMenuIds()[i]);
                Log.d("DatabaseHelper", "total = " + total);
            }
        }
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAMILY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OTHERS);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_WHO);

        // Create tables again
        onCreate(db);
    }
    // Getting single contact
    ContactsItem getContactsItem(int type, int position) {
        SQLiteDatabase db = this.getReadableDatabase();
        int id = position;
        String table = getTable(type);

        Cursor cursor = db.query(
                table,
                new String[]{KEY_NAME, KEY_NUMBER, KEY_COLOR}, KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        if (cursor != null)
            cursor.moveToFirst();


        ContactsItem contact = new ContactsItemImpl(
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
        // return contact
        cursor.close();
        db.close();

        return contact;
    }
    private String getTable(int type) {
        String table = null;

        if(type == R.id.nav_family) {
            table = TABLE_FAMILY;
        } else if(type == R.id.nav_friends) {
            table = TABLE_FRIENDS;
        } else if(type == R.id.nav_others) {
            table = TABLE_OTHERS;
        }
//        else if(type == R.id.nav_who) {
//            table = TABLE_WHO;
//        }

        return table;
    }

    // Updating single contact
    public int updateContact(ContactsItem contact, int type) {
        String table = getTable(type);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_NUMBER, contact.getNumber());
        values.put(KEY_COLOR, contact.getColor());

        // updating row
        return db.update(table, values, KEY_ID + " = ?", null);
    }

//    public void addContact(ContactsItemImpl item, int type) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, item.getName()); // Contact Name
//        values.put(KEY_NUMBER, item.getNumber()); // Contact Phone
//        values.put(KEY_COLOR, item.getColor());
//        // Inserting Row
//        db.insert(TABLE_FAMILY, null, values);
//        db.close(); // Closing database connection
//    }

//    public ArrayList<ContactsItem> getContactsItemList(int type) {
//        String table = getTable(type);
//
//        try {
//            mContactsItemList.clear();
//
//            // Select All Query
//            String selectQuery = "SELECT  * FROM " + table;
//
//            SQLiteDatabase db = this.getWritableDatabase();
//            Cursor cursor = db.rawQuery(selectQuery, null);
//
//            // looping through all rows and adding to list
//            if (cursor.moveToFirst()) {
//                do {
//                    ContactsItem contact = new ContactsItemImpl(cursor.getString(1),cursor.getString(2),cursor.getString(3));
//                    // Adding contact to list
//                    mContactsItemList.add(contact);
//                } while (cursor.moveToNext());
//            }
//
//            // return contact list
//            cursor.close();
//            db.close();
//            return mContactsItemList;
//        } catch (Exception e) {
//            Log.e("DatabaseHelper", e.toString());
//        }
//
//        return mContactsItemList;
//    }

    // Deleting single contact
//    public void deleteContact(int id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
//                new String[] { String.valueOf(id) });
//        db.close();
//    }

    // Getting contacts Count
//    public int Get_Total_Contacts() {
//        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
//
//        // return count
//        return cursor.getCount();
//    }
}
