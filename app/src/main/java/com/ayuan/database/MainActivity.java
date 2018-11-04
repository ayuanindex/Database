package com.ayuan.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*MyOpenHelper myOpenHelper = new MyOpenHelper(this, "Account.db", null, 1);
        //第一次执行时创建数据库，第二次执行是打开数据库
        SQLiteDatabase readableDatabase = myOpenHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("select * from info", null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                Log.i(TAG, "姓名:" + name + "   号码:" + phone);
            }
        }*/
    }
}
