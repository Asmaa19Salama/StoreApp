package com.example.android.storeapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.storeapp.data.StoreContract;

public class ProductCursorAdapter extends CursorAdapter {
    String productquantity;

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
        TextView priceTextView = view.findViewById(R.id.price);
        final TextView quantityTextView = view.findViewById(R.id.quantity);
        Button saleButton = view.findViewById(R.id.sale);

        int nameColumnIndex = cursor.getColumnIndex(StoreContract.StoreEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(StoreContract.StoreEntry.COLUMN_PRODUCT_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(StoreContract.StoreEntry.COLUMN_PRODUCT_QUANTITY);

        String productName = cursor.getString(nameColumnIndex);
        String productprice = cursor.getString(priceColumnIndex);
        productquantity = cursor.getString(quantityColumnIndex);

        nameTextView.setText("Product name: " + productName);
        priceTextView.setText("Product price: " + productprice);
        quantityTextView.setText("Product quantity: " + productquantity);

        saleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int temp = Integer.parseInt(productquantity);
                if (temp > 0) {
                    temp--;
                }
                quantityTextView.setText("Product quantity: " + temp);
                productquantity = String.valueOf(temp);
            }
        });
    }
}
