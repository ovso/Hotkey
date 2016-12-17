package kr.blogspot.ovsoce.hotkey.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItem;
import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItemImpl;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "hotkey.db";

    private static final String TABLE_FAMILY = "family";
    private static final String TABLE_FRIENDS = "friends";
    private static final String TABLE_OTHERS = "others";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_COLOR = "color";

    private static final int SECTION_NUMBER_FAMILY = 0;
    private static final int SECTION_NUMBER_FRIEND = 1;
    private static final int SECTION_NUMBER_OTHERS = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

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

        initDB(db);
    }

    private void initDB(SQLiteDatabase db) {
        List<ContactsItem> list = getDBInitContactsItemListData();

        int[] menuIds = {SECTION_NUMBER_FAMILY, SECTION_NUMBER_FRIEND, SECTION_NUMBER_OTHERS};

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
    }
    // Getting single contact
    public ContactsItem getContactsItem(int menuType, int position) {
        SQLiteDatabase db = this.getReadableDatabase();
        String table = getTable(menuType);

        Cursor cursor = db.query(
                table,
                new String[]{KEY_ID, KEY_NAME, KEY_NUMBER, KEY_COLOR},
                KEY_ID + "=?",
                new String[]{String.valueOf(position)},
                null,
                null,
                null,
                null
        );

        if (cursor != null)
            cursor.moveToFirst();

        ContactsItemImpl contact = new ContactsItemImpl();
        contact.setId(cursor.getString(0));
        contact.setName(cursor.getString(1));
        contact.setNumber(cursor.getString(2));
        contact.setColor(cursor.getString(3));
        contact.setMenuType(menuType);

        cursor.close();
        db.close();

        return contact;
    }
    private String getTable(int type) {
        String table = null;

        if(type == SECTION_NUMBER_FAMILY) {
            table = TABLE_FAMILY;
        } else if(type == SECTION_NUMBER_FRIEND) {
            table = TABLE_FRIENDS;
        } else if(type == SECTION_NUMBER_OTHERS) {
            table = TABLE_OTHERS;
        }

        return table;
    }

    // Updating single contact
    public int updateContact(ContactsItem contact) {
        SQLiteDatabase db = getWritableDatabase();
        String table = getTable(contact.getMenuType());

        ContentValues values = new ContentValues();
        values.put(KEY_ID, contact.getId());
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_NUMBER, contact.getNumber());
        values.put(KEY_COLOR, contact.getColor());

        // updating row
        return db.update(table, values, KEY_ID + " = ?", new String[]{contact.getId()});
    }

    private void insertContact(ContactsItem item, int type, SQLiteDatabase db) {

        String table = getTable(type);

        ContentValues values = new ContentValues();
        values.put(KEY_ID, item.getId());
        values.put(KEY_NAME, item.getName()); // Contact Name
        values.put(KEY_NUMBER, item.getNumber()); // Contact Phone
        values.put(KEY_COLOR, item.getColor());
        // Inserting Row
        db.insert(table, null, values);
    }
    private List<ContactsItem> getDBInitContactsItemListData() {

        List<ContactsItem> dataItems = new ArrayList<>();
        String[] colors = sDefaultColors;
        int k=0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < colors.length; j++) {
                String id = String.valueOf(k);
                String name = "", number="", color="";
                //color = colors[j];
                color = String.valueOf(j);
                if(id.equals("0")) name = "Indigo";

                ContactsItemImpl item = new ContactsItemImpl();
                item.setId(id);
                item.setName(name);
                item.setNumber(number);
                item.setColor(color);

                dataItems.add(item);
                k++;
            }
        }

        return dataItems;
    }
    private static String[] sDefaultColors = new String[]{"#3F51B5", "#E91E63", "#FF5722", "#4CAF50", "#607D8B", "#00BCD4", "#FFC107", "#795548", "#03A9F4", "#F44336"};
    public String[] getDefaultColors() {
        return sDefaultColors;
    }

    public List<ContactsItem> getTableContactsItemList(int type) {
        List<ContactsItem> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + getTable(type);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ContactsItemImpl item = new ContactsItemImpl();

                item.setId(cursor.getString(0));
                item.setName(cursor.getString(1));
                item.setNumber(cursor.getString(2));
                item.setColor(cursor.getString(3));

                list.add(item);
            } while (cursor.moveToNext());
        }

        return list;
    }
}
