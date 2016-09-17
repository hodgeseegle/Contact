package com.example.sqlite;

import android.provider.BaseColumns;

/**
 * Created by lzw on 2016/8/29.
 * @description 电话类型SQL契约类
 */
public class TypeEntry implements BaseColumns {
    //存放数据库的目录
    public static final String DATABASE_PATH =
            "/data/data/com.example.lzw.myproject/databases";
    //表名
    public static final String TABLE_NAME = "PhoneType";
    //列名
    public static final String COLUMMNS_NAME_TYPE = "type";
    public static final String COLUMMNS_NAME_SUBTABLE = "subtable";
    //创建表格的SQL语句
    public static final String SQL_CREATE_TABLE = "create table " + TABLE_NAME + " (" +
                    _ID + " integer primary key," + COLUMMNS_NAME_TYPE + " text," +
                    COLUMMNS_NAME_SUBTABLE + " text" + ")";
    //删除表格的SQL语句
    public static final String SQL_DELETE_TABLE =
            "drop table if exists " + TABLE_NAME;
    //子表表名
    public static final String SUB_LOCAL = "Local";//本地电话
    public static final String SUB_CATERING = "Catering";//订餐电话
    public static final String SUB_PUBLIC_SERVICE = "PublicService";//公共服务
    public static final String SUB_OPERATOR = "Operater";//运营商
    public static final String SUB_EXPRESSAGE = "Expressage";//快速服务
    public static final String SUB_TRAVEL = "Travel";//机票酒店
    public static final String SUB_BANK = "Bank";//银行证券
    public static final String SUB_INSURANCE = "Insurance";//保险服务
    public static final String SUB_AFTER_SALE = "AfterSale";//品牌售后
}
