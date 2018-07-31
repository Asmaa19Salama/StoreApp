package com.example.android.storeapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.storeapp.data.StoreContract.StoreEntry;
import com.example.android.storeapp.data.StoreDbHelper;

public class CatalogActivity extends AppCompatActivity {

    private StoreDbHelper mDbHelper;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new StoreDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {

        db = mDbHelper.getReadableDatabase();

        String[] projection = {
                StoreEntry._ID,
                StoreEntry.COLUMN_PRODUCT_NAME,
                StoreEntry.COLUMN_PRODUCT_SUPPLIER_NAME,
                StoreEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER,
                StoreEntry.COLUMN_PRODUCT_PRICE,
                StoreEntry.COLUMN_PRODUCT_QUANTITY};

        Cursor cursor = db.query(
                StoreEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.text_view_product);

        try {
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(StoreEntry._ID + " - " +
                    StoreEntry.COLUMN_PRODUCT_NAME + " - " +
                    StoreEntry.COLUMN_PRODUCT_SUPPLIER_NAME + " - " +
                    StoreEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER +
                    StoreEntry.COLUMN_PRODUCT_PRICE + " - " +
                    StoreEntry.COLUMN_PRODUCT_QUANTITY + " - " + "\n");

            int idColumnIndex = cursor.getColumnIndex(StoreEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(StoreEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(String.valueOf(StoreEntry.COLUMN_PRODUCT_PRICE));
            int quantityColumnIndex = cursor.getColumnIndex(String.valueOf(StoreEntry.COLUMN_PRODUCT_QUANTITY));
            int supplierNameColumnIndex = cursor.getColumnIndex(StoreEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
            int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(StoreEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentPrice = cursor.getString(priceColumnIndex);
                String currentQuantity = cursor.getString(quantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                String currentsupplierPhoneNumber = cursor.getString(supplierPhoneNumberColumnIndex);
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentSupplierName + " - " +
                        currentsupplierPhoneNumber +
                        currentPrice + " - " +
                        currentQuantity + " - "));
            }
        } finally {
            cursor.close();
        }
    }

    private void insertProduct() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(StoreEntry.COLUMN_PRODUCT_NAME, "Laptop");
        values.put(StoreEntry.COLUMN_PRODUCT_SUPPLIER_NAME, "Asmaa");
        values.put(StoreEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER, "01234");
        values.put(StoreEntry.COLUMN_PRODUCT_PRICE, "10");
        values.put(StoreEntry.COLUMN_PRODUCT_QUANTITY, "10");

        long newRowId = db.insert(StoreEntry.TABLE_NAME, null, values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                insertProduct();
                displayDatabaseInfo();
                return true;
            case R.id.action_delete_all_entries:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
