package org.techtown.doitmission_22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements fragment1.sendDataListener {
    TabLayout tabs;
    fragment1 fragment1;
    fragment2 fragment2;
    Book book;
    ArrayList<Book> books = new ArrayList<Book>();
    SQLiteDatabase database;
    String tablename = "bookrecords";
    DatabaseHelper dbHelper;
    int beforeposition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabs = findViewById(R.id.tabs);
        fragment1 = new fragment1();
        fragment2 = new fragment2();

        createDatabase("library");
        createTable(tablename);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if(position == 0){
                    selected = fragment1;
                }
                else if(position == 1){
                    selected = fragment2;
                    exequteQuery();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void sendata(Book book) {
        this.book = book;
        Toast.makeText(getApplicationContext(), book.getName() + " ",Toast.LENGTH_LONG).show();
        insertRecord();
    }

    public void exequteQuery(){
        println("exequteQuery 호출됨");
        Cursor cursor = database.rawQuery("select _id, name, writer, contents from " + tablename , null);
        int recordCount = cursor.getCount();
        println("레코드 갯수 : " + recordCount);

        for(int i=beforeposition; i< recordCount; i++){
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String writer = cursor.getString(2);
            String contents =cursor.getString(3);

            println("레코드#" + i + " : " + id + ", " + name + ", " + writer + ", " + contents);
            books.add(new Book(name,writer,contents));
        }
        beforeposition = recordCount; //여기까지 읽었다고 표시
        cursor.close();
        senddata2();
    }
    public void senddata2(){
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("booklist",books);
        fragment2.setArguments(bundle);
    }

    private void createDatabase(String name){
        println("createDatabase 호출됨.");
        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
        println("데이터베이스 생성됨 : " + name);
    }

    private void createTable(String name){
        println("createTable 호출됨.");
        if(database == null){
            println("데이터베이스를 먼저 생성하세요.");
            return;
        }
        database.execSQL("create table if not exists " + name + "(" + "_id integer PRIMARY KEY autoincrement, " + " name text, " + "writer text, " + "contents text)");
        println("테이블 생성함 : " + name);
    }

    private void insertRecord(){
        println("insertRecod 호출됨.");
        if(database==null){
            println("데이터베이스를 먼저 생성하세요.");
            return;
        }
        if(tablename == null){
            println("테이블을 먼저 생성하세요.");
            return;
        }
        database.execSQL("insert into " + tablename + "(name, writer, contents) " + " values " + "('" + book.getName() + "' , '"+ book.getWriter() + "' , '" + book.getContents() + "')" );
        println("레코드 추가됨");
    }

    public void println(String data){Log.d("MainAcitivity", data);}
}