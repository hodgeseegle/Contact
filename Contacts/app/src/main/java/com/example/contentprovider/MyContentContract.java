package com.example.ContentProvider;

import android.net.Uri;

/**
 * @description 我的联系人内容提供者协定类
 * Created by lzw on 2016/9/13.
 */
public final class MyContentContract {

    public static final String AUTHORITY = "com.example.lzw.myproject";

    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PHONETYPE = "PhoneType";

    public static final Uri PHONETYPE_URI = Uri.withAppendedPath(AUTHORITY_URI,
            PHONETYPE);

    public static final String DATABASE_PATH = "/data/data/com.example.lzw.myproject/databases/phone.db";
}
