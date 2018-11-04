package com.ayuan.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class AccountProvider extends ContentProvider {

    //1.定义一个UriMather   定义一个路径匹配器
    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int QUERYSUCESS = 1;
    private MyOpenHelper myOpenHelper;

    //2.定义静态代码块 添加匹配规则
    static {
        /**
         * authority:这个参数和清单文件里面定义的要一致
         * path:
         */
        matcher.addURI("com.ayuan.provider", "query", QUERYSUCESS);
    }


    //首先调用此方法
    @Override
    public boolean onCreate() {
        myOpenHelper = new MyOpenHelper(getContext(), "Account.db", null, 1);
        return false;
    }


    /**
     * @param uri
     * @param projection    代表需要查询的列
     * @param selection     查询的条件
     * @param selectionArgs 查询条件的参数
     * @param sortOrder
     * @return
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.i("AccountProvider", "提供成功");
        int code = matcher.match(uri);//返回一个匹配码
        if (code == QUERYSUCESS) {
            //说明路径匹配成功则实现query方法    数据库的查询方法 对数据库进行查询操作 想操作数据库必须获得Sqlitedatabase对象android.database.sqlite.SQLiteDatabase
            SQLiteDatabase readableDatabase = myOpenHelper.getReadableDatabase();
            Cursor info = readableDatabase.query("info", projection, selection, selectionArgs, null, null, sortOrder);
            //Cursor不能关闭
            return info;
        } else {
            //说明路径不匹配 抛出参数异常
            throw new IllegalArgumentException("哥们：你的路径不匹配，请检查路径");
            //也可以返回一个null
        }
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
