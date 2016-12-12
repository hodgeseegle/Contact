package com.example.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.Entity.PhoneNumberEntity;
import com.example.lzw.myproject.R;
import com.example.MyAdaptar.PhoneNumberAdapter;
import com.example.SQLite.NumberEntry;
import com.example.SQLite.TypeEntry;

import java.util.ArrayList;

/**
 * @author Neal 2016-09-01
 * @description 展示电话号码列表的页面
 */
public class PhoneNumber extends BaseActivity {
    //显示号码的列表
    ListView lv_phoneNumber;
    //loading界面
    LinearLayout ll_loading;
    //电话号码类型适配器
    PhoneNumberAdapter pnAdapter;
    public final int CALL_PHONE = 0;
    @Override
    protected int setContent() {
        return R.layout.activity_phone_number;
    }

    @Override
    protected void initView() {
        lv_phoneNumber = (ListView)findViewById(R.id.lv_phoneNumber);
        ll_loading = (LinearLayout)findViewById(R.id.ll_loading);
        //开始装载界面资
        new InitTask().execute();

    }

    @Override
    protected void setListener() {

    }
    //异步初始化任务类
    class InitTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            //装载ListView
            initList();
            requestPermission();
            return null;
        }
        //在任务启动之前执行的代码，可操作UI

        @Override
        protected void onPreExecute() {
            //让loading界面显示
            ll_loading.setVisibility(View.VISIBLE);
        }
        //任务完成之后执行的代码，可操作UI

        @Override
        protected void onPostExecute(Void aVoid) {
            //隐藏loading界面
            ll_loading.setVisibility(View.GONE);
            //给列表设置适配器
            lv_phoneNumber.setAdapter(pnAdapter);
        }
    }
    /**
     * @description 装载ListView
     */
    private void initList() {
        //获取可读的database对象，通过打开固定路劲的方式
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(TypeEntry.DATABASE_PATH
                + "/phone.db", null);
        //获得当前应加载的数据库表名
        String subTable = getIntent().getStringExtra(TypeEntry.
                COLUMMNS_NAME_SUBTABLE);
        //查询数据库，返回一个游标
        Cursor cursor = db.query(subTable, new String[]{"*"},
                null, null, null, null, null);
        //将游标移动到第一行
        cursor.moveToFirst();
        //准备好实体数据组
        final ArrayList<PhoneNumberEntity> entities = new ArrayList<PhoneNumberEntity>();
        do {
            //将游标中的数据遍历取出
            String phoneName = cursor.getString(
                    cursor.getColumnIndexOrThrow(NumberEntry.COLUMNS_NAME_NAME)
            );
            String phoneNumber = cursor.getString(
                    cursor.getColumnIndexOrThrow(NumberEntry.COLUMNS_NAME_NUMBER)
            );
            //装载进实体类中
            PhoneNumberEntity entity = new PhoneNumberEntity();
            entity.setPhoneName(phoneName);
            entity.setPhoneNumber(phoneNumber);
            //装载进实体类组中
            entities.add(entity);
        } while (cursor.moveToNext());

        //初始化适配器
        pnAdapter = new PhoneNumberAdapter(this, entities);
        //给列表设置点击监听
        lv_phoneNumber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position,
                                    long id) {


                //创建询问是否拨打电话的对话框
                new AlertDialog.Builder(PhoneNumber.this)
                        .setTitle("警告")//标题
                        .setMessage(
                                "是否开始拨打"
                                        + entities.get(position).getPhoneName()
                                        + "电话"
                                        + "\n"
                                        + "TEL:" + entities.get(position).
                                        getPhoneNumber())//对话框信息
                        .setPositiveButton(
                                "拨号",
                                //点击拨号后的监听
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //拨打当前点击的电话号码
                                        Intent intent = new Intent(
                                                "android.intent.action.CALL",
                                                Uri.parse("tel:" + entities.get(position).
                                                        getPhoneNumber()));
                                        startActivity(intent);
                                    }
                                })//确认按钮
                        .setNegativeButton("取消", null)//取消按钮
                        .show();//显示警告框
            }
        });
    }
    //申请运行时权限
    private void requestPermission(){
        //检查是否拥有外部存储器写权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission
                .CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.CALL_PHONE,
                    },CALL_PHONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //判断申请码
        switch (requestCode){
            case CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //申请的第一个权限成功后
                }else{
                    //申请的第一个权限失败后
                    finish();
                }
                break;
        }
    }
}




