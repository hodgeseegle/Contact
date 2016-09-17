package com.example.a.providercontact;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView tv_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_data = (TextView) findViewById(R.id.tv_data);
//
//        insertProvider();
 //       deleteProvider();
//        updateProvider();
        queryProvider();

    }

    /**
     * 查询通讯大全的ContentProvider
     */
    private void queryProvider() {

        //与普通的ContentProvider查询数据的方式相同
        Cursor cursor = getContentResolver().query(
                MyContactsContract.PHONETYPE_URI,//自定义的uri地址
                new String[]{"*"},
                null,
                null,
                null
        );
        //临时保存数据的字符
        String temp = "";
        //判断游标状态是否正常
        if (cursor != null && cursor.getCount() > 0) {
            //移动游标到第一行
            cursor.moveToFirst();
            //获得电话类型名称的列下标
            int i_typeNameIndex = cursor.getColumnIndexOrThrow(MyContactsContract.TYPENAME);
            //遍历游标
            do {
                //取到对应下标的类型名称
                String typeName = cursor.getString(
                        i_typeNameIndex
                );
                //拼接临时字符
                temp += "TypeName:" + typeName + "\n" + "\n";

            } while (cursor.moveToNext());
            //更新UI
            tv_data.setText(temp);
        }


    }

    /**
     * @description 向通讯大全中添加一条数据库记录
     */
    private void insertProvider() {
        //要添加的行的值
        ContentValues mContentValues = new ContentValues();
        //只添加一列
        mContentValues.put(MyContactsContract.TYPENAME, "自定义类型");
        //插入
        getContentResolver().insert(
                MyContactsContract.PHONETYPE_URI,//要插入的uir
                mContentValues);//要插入的值

    }

    /**
     * @description 删除通讯大全中某行的内容
     */
    private void deleteProvider() {
        //要删除的选择条件
        String mSelection = MyContactsContract.ID + "=4";
        //删除匹配选择条件的行
        getContentResolver().delete(
                MyContactsContract.PHONETYPE_URI,//要删除的uri
                mSelection,//删除的选择条件
                null);//删除的选择条件参数
    }

    /**
     * @description 更新通讯大全中的某条数据
     */
    private void updateProvider() {
        //待更新的选择条件
        String mSelection = MyContactsContract.ID + "=3";
        //想要更新的值
        ContentValues mContentValues = new ContentValues();
        mContentValues.put(MyContactsContract.TYPENAME, "被修改");
        //更新数据
        getContentResolver().update(
                MyContactsContract.PHONETYPE_URI,//更新的uri
                mContentValues,//要更新的值
                mSelection,//选择条件
                null);//选择条件参数

    }


}
