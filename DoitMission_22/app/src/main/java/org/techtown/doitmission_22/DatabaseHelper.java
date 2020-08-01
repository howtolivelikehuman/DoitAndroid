package org.techtown.doitmission_22;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String NAME = "library.db";
    public static String TABLENAME = "bookrecords";
    public static int VERSION = 1;

    public DatabaseHelper(Context context){
        super(context,NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        println("onCreate 호출됨");
        String sql = "create table if not exists "+TABLENAME+"( _id integer PRIMARY KEY autoincrement, name text, writer text, contents text)";
        sqLiteDatabase.execSQL(sql);
    }

    public void onOpen(SQLiteDatabase db){
        println("onOpen 호출됨");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        println("onUpgrade 호출됨 : " +i+" -> "+ i1);
        if(i1 > 1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLENAME);
        }
    }
    public void println(String data){
        Log.d("DatabaseHelper" , data);
    }
}
