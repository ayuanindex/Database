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
    private static final int QUERYSUCESS = 0;
    private static final int INSERTSUCESS = 1;
    private static final int DELETESUCESS = 2;
    private static final int UPDATESUCESS = 3;
    private MyOpenHelper myOpenHelper;

    //2.定义静态代码块 添加匹配规则
    static {
        /**
         * authority:这个参数和清单文件里面定义的要一致
         * path:
         */
        matcher.addURI("com.ayuan.provider", "query", QUERYSUCESS);
        matcher.addURI("com.ayuan.provider", "insert", INSERTSUCESS);
        matcher.addURI("com.ayuan.provider", "delete", DELETESUCESS);
        matcher.addURI("com.ayuan.provider", "update", UPDATESUCESS);
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

        int code = matcher.match(uri);//返回一个匹配码
        if (code == QUERYSUCESS) {
            //说明路径匹配成功则实现query方法    数据库的查询方法 对数据库进行查询操作 想操作数据库必须获得Sqlitedatabase对象android.database.sqlite.SQLiteDatabase
            SQLiteDatabase readableDatabase = myOpenHelper.getReadableDatabase();
            Cursor info = readableDatabase.query("info", projection, selection, selectionArgs, null, null, sortOrder);
            //Cursor不能关闭
            return info;
        } else {
            //说明路径不匹配 抛出参数异常
            Log.i("AccountProvider", "提供成功");
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
        int code = matcher.match(uri);
        if (code == INSERTSUCESS) {
            //操作数据库 说明路径匹配成功   对数据库进行添加的操作
            SQLiteDatabase readableDatabase = myOpenHelper.getReadableDatabase();
            //返回值代表新插入行的id
            long info = readableDatabase.insert("info", null, values);
            Uri parse = Uri.parse("com.ayuan.accountprovider_insert/" + info);
            return parse;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int code = matcher.match(uri);
        if (code == DELETESUCESS) {
            //说明路径匹配成功-------->对数据库进行删除的操作
            SQLiteDatabase readableDatabase = myOpenHelper.getReadableDatabase();
            //返回值为删除所影响的行数
            int info = readableDatabase.delete("info", selection, selectionArgs);
            return info;
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int code = matcher.match(uri);
        if (code == UPDATESUCESS) {
            SQLiteDatabase readableDatabase = myOpenHelper.getReadableDatabase();
            //这里是更新所影响的行数
            int info = readableDatabase.update("info", values, selection, selectionArgs);
            return info;
        }
        return 0;
    }
}
