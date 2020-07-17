package org.techtown.doitmission_08;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SalesActivity extends AppCompatActivity {
    public static final int SALES_CODE_FROM_MENU = 103;
    public static final int MAIN_CODE_FROM_SALES = 113;
    public static final int MENU_CODE_FROM_SALES = 123;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        Toast.makeText(getApplicationContext(), "메뉴 -> 매출 분석", Toast.LENGTH_LONG).show();

        textView = findViewById(R.id.textView2);
        Intent intent = getIntent();
        getData(intent);

        Button main = findViewById(R.id.button2);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                setResult(MAIN_CODE_FROM_SALES,intent);
                finish();
            }
        });

        Button menu = findViewById(R.id.button1);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                setResult(MENU_CODE_FROM_SALES,intent);
                finish();
            }
        });
    }
    private void getData(Intent intent){
        if(intent != null){
            Bundle bundle = intent.getExtras();
            SimpleData data = bundle.getParcelable("data");
            if(intent != null){
                textView.setText("ID : " + data.ID +"\nPW : " + data.PW);
            }
        }
    }
}