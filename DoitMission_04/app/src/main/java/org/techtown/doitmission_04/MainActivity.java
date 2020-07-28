package org.techtown.doitmission_04;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        editText.addTextChangedListener(new TextWatcher() {
            String beforeText;
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforeText = s.toString();
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                int length = showBytes();
                if(length > 80) editText.setText(beforeText);
            }
        });
    }
    private int showBytes(){
        try{
            int length = editText.getText().toString().getBytes("EUC-KR").length;
            textView2.setText(length + " / 80 바이트");
            return length;
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return -1;
    }
    public void onbuttonClose(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("정말로 종료하시겠습니까?");
        builder.setTitle("종료 알림창")
                .setCancelable(false)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("종료 알림창");
        alert.show();
    }
    public void onbuttonSend(View v){
       String s =  editText.getText().toString();
       //토스트 사용
       final Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG);
       toast.show();
       editText.setText("");
    }
}