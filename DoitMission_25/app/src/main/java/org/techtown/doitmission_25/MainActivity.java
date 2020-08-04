package org.techtown.doitmission_25;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.TextView;

import com.pedro.library.AutoPermissions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    imageAdapter imageadapter;
    TextView textNum;

    Cursor cursor;
    int totalpages = 0;

    Bitmap bitmap;

    String date;
    Calendar cal = new GregorianCalendar();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    ArrayList<sampleimage> sampleimages = new ArrayList<sampleimage>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoPermissions.Companion.loadAllPermissions(this,101);
        textNum = findViewById(R.id.textNum);

        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        imageadapter = new imageAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(imageadapter);

        getImages();
    }

    private void getImages(){
        sampleimage images;
        String[] projection = new String[]{
                MediaStore.Images.ImageColumns._ID,
                MediaStore.Images.ImageColumns.DISPLAY_NAME,
                MediaStore.Images.ImageColumns.DATE_TAKEN,
                MediaStore.Images.ImageColumns.DATA
        };
        cursor = getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null,
                        null, MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC");
        totalpages = cursor.getCount();
        textNum.setText(totalpages + " 개");

        for (int i=0; i<100; i++){
            cursor.moveToPosition(i);
            images = new sampleimage();
            long id = cursor.getLong(0);
            String name = cursor.getString(1);
            long datessec = cursor.getLong(2);
            String data = cursor.getString(3);

            getThumbnail(id, images);
            getDate(datessec,images);
            //getImage(data, images);
            images.setName(name);
            imageadapter.addItem(images);
        }
        cursor.close();
    }

    //썸네일 띄우기
    public void getThumbnail(long id, sampleimage image){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = true;
        options.inSampleSize = 2; // 이 숫자가 커질수록 사진 크기가 작아져서 속도가 빨라진다
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bit = MediaStore.Images.Thumbnails.getThumbnail( getContentResolver(), id, MediaStore.Images.Thumbnails.MINI_KIND,options);
        bitmap = ThumbnailUtils.extractThumbnail(bit,100,100,ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        image.setThumbnail(bitmap);
    }
    public void getImage(String path, sampleimage image){
        File file = new File(path);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 5;
        Bitmap bit  = BitmapFactory.decodeFile(path);
        bitmap = ThumbnailUtils.extractThumbnail(bit,100,100,ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        image.setThumbnail(bitmap);
    }
    //날짜 형식
    public void getDate(long time, sampleimage image){
        cal.setTimeInMillis(time);
        Date d = cal.getTime();
        date = format.format(d);
        image.setDate(date);
    }
}