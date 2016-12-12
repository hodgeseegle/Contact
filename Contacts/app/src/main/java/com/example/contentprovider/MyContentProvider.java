package com.example.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * @description 通讯录的内容提供者
 * Created by lzw on 2016/9/12.
 */
public class MyContentProvider extends ContentProvider {
    //访问PhoneType表类型的uri
    private static final int URI_CODE_PHONETYPE = 1;
    //访问Catering表类型的uri
    private static final int URI_CODE_CATERING = 0;
    //实例化一个urimatcher对象
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        //给内容提供者添加一条匹配TYpeEntry.TABLE_NAME的uri，uri类型为URI_CODE_PHONETYPE
        uriMatcher.addURI(MyContentContract.AUTHORITY, MyContentContract.PHONETYPE,URI_CODE_PHONETYPE);


        //给内容提供者添加一条匹配Catering表的uri,URI_CODE_CATERING
        uriMatcher.addURI(MyContentContract.AUTHORITY,
                "Catering",
                URI_CODE_CATERING);
    }
    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
       //获取可读的database对象，通过打开固定路径的方式
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
                MyContentContract.DATABASE_PATH,null
        );
        //要返回给用户的游标
        Cursor mCursor = null;
        //使用urimatcher类匹配uri返回uri的类型
        switch (uriMatcher.match(uri)){
            //匹配phoneType表的uri
            case 1:
                //查询数据库，返回一个游标
                mCursor = db.query(MyContentContract.PHONETYPE,//表名
                        projection,//COLUMNS
                        selection,//where
                        selectionArgs,//where args
                        null,//group by
                        null,//having
                        sortOrder//order by
                        );
                break;
            case 0:
                //查询数据库,返回一个游标
                mCursor = db.query("Catering", //表名
                        projection,//COLUMNS
                        selection, //WHERE
                        selectionArgs, //Where args
                        null, //GROUP BY
                        null, //HAVING
                        sortOrder //ORDER BY
                );
                break;
        }
        return mCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        //返回给用户的MIME类型
        String mType = null;
        //分析匹配的uri
        switch (uriMatcher.match(uri)){
            //查询phoneType表的uri
            case 1:
                mType = "vnd.android.cursor.dir/" + MyContentContract.AUTHORITY + "." +
                MyContentContract.PHONETYPE;
                break;
        }
        return mType;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //获取可读的database对象，通过打开固定路径的方式
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
                MyContentContract.DATABASE_PATH,null
        );
        //新插入行的id
        long id = 0;
        switch (uriMatcher.match(uri)){
            //插入PhoneType表的uri
            case 1:
                //插入一行
                id = db.insert(MyContentContract.PHONETYPE,//表名
                        null,
                        values
                        );
                //关闭数据库
                db.close();
                break;
        }
        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        //获取可读的database对象，通过打开固定路径的方式
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
                MyContentContract.DATABASE_PATH,null
        );
        //成功删除的条目
        int i_delete = 0;
        //分析匹配的uri
        switch (uriMatcher.match(uri)){
            case 1:
                //删除某行
                i_delete = db.delete(MyContentContract.PHONETYPE,//表名
                        selection,//选择条件
                        selectionArgs//选择参数
                        );
                //关闭数据库
                db.close();
                break;
        }
        return i_delete;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
      //获取可读的database对象，通过打开固定路径的方式
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
                MyContentContract.DATABASE_PATH,null
        );
        //更新条数
        int i_update = 0;
        //分析匹配的uri
        switch (uriMatcher.match(uri)){
            //在PhoneType中更新的uri
            case 1:
                //更新MyContentContract.PHONETYPE表中某一行的值
                i_update = db.update(
                        MyContentContract.PHONETYPE,//表名
                        values,//更新的值
                        selection,//选择条件
                        selectionArgs//选择参数
                );
                //关闭数据库
                db.close();
                break;
        }
        return i_update;
    }
}
