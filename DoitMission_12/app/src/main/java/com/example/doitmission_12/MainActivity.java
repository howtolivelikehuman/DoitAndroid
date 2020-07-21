package com.example.doitmission_12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView textView;
    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("text", text);
                intent.putExtra("name", "메인 출발\n");
                startService(intent);
            }
        });

        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter("com.example.doitmission12.myAction");
        this.registerReceiver(myReceiver, filter);

        NewActivity(getIntent());
    }

    public void NewActivity(Intent intent){
            String text = intent.getStringExtra("text");
            String name = intent.getStringExtra("name");
            if(text != null){
                textView.setText("내용 : " + text+ "\n" + "경로 : " + name + "다시 메인으로");
            }

    }

    public class MyReceiver extends BroadcastReceiver {
        public static final String TAG = "MyReceiver";
        public Intent BroadcastIntent;
        @Override
        public void onReceive(Context context, Intent intent) {
            String text = intent.getStringExtra("text");
            String name = intent.getStringExtra("name") + "브로드캐스트 거침\n";
            Log.d(TAG, "BroadCast에서 받은 문자  : " + text);
            textView.setText("내용 : " + text+ "\n" + "경로 : " + name + "다시 메인으로");
            //sendToActivity(context, text, name);
        }

        //아래 메소드를 사용하면 새 액티비티 창켜서 인텐트 전송 가능.
        public void sendToActivity(Context context, String text, String name){
            BroadcastIntent = new Intent(context.getApplicationContext(), MainActivity.class);
            BroadcastIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            BroadcastIntent.putExtra("text" , text);
            BroadcastIntent.putExtra("name", name);
            context.getApplicationContext().startActivity(BroadcastIntent);
        }
    }
}