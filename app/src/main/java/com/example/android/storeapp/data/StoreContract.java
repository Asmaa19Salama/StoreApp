package com.example.android.storeapp.data;

import android.provider.BaseColumns;

public final class StoreContract {

    private StoreContract() {
    }

    public static final class StoreEntry implements BaseColumns {

        public final static String TABLE_NAME = "\"products\"";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_PRODUCT_NAME ="name";

        public final static int COLUMN_PRODUCT_PRICE = 0;

        public final static int COLUMN_PRODUCT_QUANTITY = 0;

        public final static String COLUMN_PRODUCT_SUPPLIER_NAME = "default";

        public final static String COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER = "zero";

    }

}

