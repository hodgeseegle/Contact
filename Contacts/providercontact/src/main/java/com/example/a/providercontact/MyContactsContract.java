package com.example.a.providercontact;

import android.net.Uri;

/**
 * Created by 15732 on 2016/9/13.
 */

public class MyContactsContract {
    public static final String AUTHORITY = "com.example.lzw.myproject";

    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PHONETYPE = "PhoneType";

    public static final Uri PHONETYPE_URI = Uri.withAppendedPath(AUTHORITY_URI,PHONETYPE);

    public static final String DATABASE_PATH = "/data/data/com.example.lzw.myproject/databases/phone.db";

    public static final String TYPENAME = "type";

    public static final String ID = "_id";
}
