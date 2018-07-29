package com.example.android.storeapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.storeapp.data.StoreContract.StoreEntry;

public class StoreDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = StoreDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "shelter.db";

    private static final int DATABASE_VERSION = 1;


    public StoreDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PRODUCTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + StoreEntry.TABLE_NAME + " ("
                + StoreEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + StoreEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + StoreEntry.COLUMN_PRODUCT_SUPPLIER_NAME + " TEXT NOT NULL, "
                + StoreEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER + " TEXT, "
                + StoreEntry.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL, "
                + StoreEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL DEFAULT 0);";
        db.execSQL(SQL_CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}