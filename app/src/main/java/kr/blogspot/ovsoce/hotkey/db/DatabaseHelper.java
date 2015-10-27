package kr.blogspot.ovsoce.hotkey.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.fragment.ContactsItem;
import kr.blogspot.ovsoce.hotkey.fragment.ContactsItemImpl;

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
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_COLOR = "color";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
        initDB(db);
    }

    public void initDB(SQLiteDatabase db) {
        List<ContactsItem> list = getDBInitContactsItemListData();

        int[] menuIds = {R.id.nav_family, R.id.nav_friends, R.id.nav_others};

        for (int id : menuIds) {
            for (ContactsItem item:list) {
                insertContact(item, id, db);
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
    public ContactsItem getContactsItem(int menuType, int position) {
        SQLiteDatabase db = this.getReadableDatabase();
        int id = position+1;
        String table = getTable(menuType);

        Cursor cursor = db.query(
                table,
                new String[]{KEY_ID, KEY_NAME, KEY_NUMBER, KEY_COLOR},
                KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        if (cursor != null)
            cursor.moveToFirst();

        ContactsItem contact = new ContactsItemImpl(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
        contact.setMenuType(menuType);
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
    public int updateContact(ContactsItem contact, int type, SQLiteDatabase db) {

        String table = getTable(type);

        ContentValues values = new ContentValues();
        values.put(KEY_ID, contact.getId());
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_NUMBER, contact.getNumber());
        values.put(KEY_COLOR, contact.getColor());

        // updating row
        return db.update(table, values, KEY_ID + " = ?", null);
    }

    public void insertContact(ContactsItem item, int type, SQLiteDatabase db) {

        String table = getTable(type);

        ContentValues values = new ContentValues();
        values.put(KEY_ID, item.getId());
        values.put(KEY_NAME, item.getName()); // Contact Name
        values.put(KEY_NUMBER, item.getNumber()); // Contact Phone
        values.put(KEY_COLOR, item.getColor());
        // Inserting Row
        db.insert(table, null, values);
        //db.close(); // Closing database connection
    }

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

    public void exportDB(Context context) {
        //createExternalStoragePrivateFile
        // Create a path where we will place our private file on external
        // storage.
        File file = new File(context.getExternalFilesDir(null), DATABASE_NAME);
        //File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(),DATABASE_NAME);
        try {
            // Very simple code to copy a picture from the application's
            // resource into the external file.  Note that this code does
            // no error checking, and assumes the picture is small (does not
            // try to copy it in chunks).  Note that if external storage is
            // not currently mounted this will silently fail.
            InputStream is = new FileInputStream(context.getDatabasePath(DATABASE_NAME));
            OutputStream os = new FileOutputStream(file);
            byte[] data = new byte[is.available()];
            is.read(data);
            os.write(data);
            is.close();
            os.close();
            Log.d(file.toString());
        } catch (IOException e) {
            // Unable to create file, likely because external storage is
            // not currently mounted.
            Log.e(e.toString());

        }
    }
    private List<ContactsItem> getDBInitContactsItemListData() {

        List<ContactsItem> dataItems = new ArrayList<ContactsItem>();
        String[] colors = sDefaultColors;
        int k=0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < colors.length; j++) {
                String id = String.valueOf(k);
                String name = "", number="", color="";
                color = colors[j];
                if(id.equals("0")) name = "Indigo";
                dataItems.add(new ContactsItemImpl(id, name, number, color));
                k++;
            }
        }

        return dataItems;
    }
    private static String[] sDefaultColors = new String[]{"#3F51B5", "#E91E63", "#FF5722", "#4CAF50", "#607D8B", "#00BCD4", "#FFC107", "#795548", "#03A9F4", "#F44336"};
    public String[] getDefaultColors() {
        return sDefaultColors;
    }
//    public List<ContactsItem> getTableContactsItemList(int type) {
//        List<ContactsItem> list = new ArrayList<ContactsItem>();
//
//        for(int i=0; i<100; i++) {
//            list.add(getContactsItem(type, i+1));
//        }
//
//        return list;
//    }
    public List<ContactsItem> getTableContactsItemList(int type) {
        List<ContactsItem> list = new ArrayList<ContactsItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + getTable(type);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ContactsItem item = new ContactsItemImpl(cursor.getString(0),cursor.getString(1),cursor.getString(2), cursor.getString(3));
                list.add(item);
            } while (cursor.moveToNext());
        }

        return list;
    }
    public List<ContactsItem> getDummyData() {

        List<ContactsItem> dataItems = new ArrayList<ContactsItem>();
        String[] colors = {"#3F51B5", "#E91E63", "#FF5722", "#4CAF50", "#607D8B", "#00BCD4", "#FFC107", "#795548", "#03A9F4", "#F44336"};
        int k=0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < colors.length; j++) {
                String id = String.valueOf(k);
                String name = "", number="", color="";
                color = colors[j];
                if(id.equals("0")) name = "Indigo";
                dataItems.add(new ContactsItemImpl(id,name,number,color));
                Log.d("id = " + id);
                k++;
            }
        }

        return dataItems;
    }

}
