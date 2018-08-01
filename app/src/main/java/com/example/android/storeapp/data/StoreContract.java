package com.example.android.storeapp.data;

import android.provider.BaseColumns;

public final class StoreContract {

    private StoreContract() {
    }

    public static final class StoreEntry implements BaseColumns {

        public final static String TABLE_NAME = "\"products\"";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_PRODUCT_NAME ="product_name";

        public final static String COLUMN_PRODUCT_PRICE ="product_price";

        public final static String COLUMN_PRODUCT_QUANTITY = "product_quantity";

        public final static String COLUMN_PRODUCT_SUPPLIER_NAME = "product_supplier_name";

        public final static String COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER = "product_supplier_phone_number";

    }

}

