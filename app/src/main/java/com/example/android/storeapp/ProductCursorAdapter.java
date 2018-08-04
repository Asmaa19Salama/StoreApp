package com.example.android.storeapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.storeapp.data.StoreContract;

public class ProductCursorAdapter extends CursorAdapter {

    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.name);
        TextView summaryTextView = view.findViewById(R.id.summary);

        int nameColumnIndex = cursor.getColumnIndex(StoreContract.StoreEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(StoreContract.StoreEntry.COLUMN_PRODUCT_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(StoreContract.StoreEntry.COLUMN_PRODUCT_PRICE);
        int supplierNameColumnIndex = cursor.getColumnIndex(StoreContract.StoreEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
        int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(StoreContract.StoreEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER);

        String productName = cursor.getString(nameColumnIndex);

        nameTextView.setText(productName);
    }
}
