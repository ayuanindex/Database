package com.ayuan.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
    /**
     * @param context 调用者传入的content对象
     * @param name    数据库的名字
     * @param factory 游标工厂默认填写NULL
     * @param version 数据库版本
     */
    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 当数据库第一次被创建的时候调用
     * 做表结构的初始化
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20),money varchar(20))");
        db.execSQL("insert into info(name,money) values(?,?)", new String[]{"张三", "5000"});
        db.execSQL("insert into info(name,money) values(?,?)", new String[]{"李四", "6000"});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
