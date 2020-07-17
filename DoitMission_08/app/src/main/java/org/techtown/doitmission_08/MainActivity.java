package org.techtown.doitmission_08;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int MENU_CODE_FROM_MAIN = 101;
    public static final int MAIN_CODE_FROM_CUSTOMER = 112;
    public static final int MAIN_CODE_FROM_SALES = 113;
    public static final int MAIN_CODE_FROM_PRODUCT = 114;

    EditText editID;
    EditText editPW;
    String ID;
    String PW;

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == MAIN_CODE_FROM_CUSTOMER) {
            Toast.makeText(getApplicationContext(),"고객 관리 -> 로그인",Toast.LENGTH_LONG).show();
        }
        else if(resultCode == MAIN_CODE_FROM_SALES) {
            Toast.makeText(getApplicationContext(),"매출 분석 -> 로그인",Toast.LENGTH_LONG).show();
        }
        else if(resultCode == MAIN_CODE_FROM_PRODUCT){
            Toast.makeText(getApplicationContext(),"상품 분석 -> 로그인",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        editID = findViewById(R.id.editText1);
        editPW = findViewById(R.id.editText2);

        editID.getText().toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkID()){
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    SimpleData data = new SimpleData(ID,PW);
                    intent.putExtra("data",data);
                    startActivityForResult(intent,MENU_CODE_FROM_MAIN);
                }
                else{
                    Toast.makeText(getApplicationContext(),"ID/PW를 입력하세요", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private boolean checkID(){
       ID = editID.getText().toString();
       PW = editPW.getText().toString();

       if(ID.length() < 1 || PW.length() < 1){
           return false;
       }
       else return true;
    }
}