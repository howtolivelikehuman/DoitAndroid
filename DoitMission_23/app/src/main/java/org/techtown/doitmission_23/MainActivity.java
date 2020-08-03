package org.techtown.doitmission_23;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BestPaintBoard paintBoard;
    LinearLayout layout;
    RadioButton  BUTT;
    RadioButton  ROUND;
    RadioButton SQUARE;
    boolean colorbool = true;
    int border = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
        paintBoard = new BestPaintBoard(this);
        layout.addView(paintBoard);
        paintBoard.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        final RadioGroup rg = (RadioGroup)findViewById(R.id.radiogroup);
        BUTT = findViewById(R.id.radioButton);
        ROUND = findViewById(R.id.radioButton2);
        ROUND.setChecked(true);
        SQUARE = findViewById(R.id.radioButton3);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radioButton){
                    paintBoard.setStrokeCap(Paint.Cap.BUTT);
                    Toast.makeText(getApplicationContext(),"BUTT", Toast.LENGTH_LONG).show();
                }
                else if(i == R.id.radioButton2){
                    paintBoard.setStrokeCap(Paint.Cap.ROUND);
                    Toast.makeText(getApplicationContext(),"ROUND", Toast.LENGTH_LONG).show();
                }
                else if (i == R.id.radioButton3){
                    paintBoard.setStrokeCap(Paint.Cap.SQUARE);
                    Toast.makeText(getApplicationContext(),"SQUARE", Toast.LENGTH_LONG).show();
                }
            }
        });

        final Button button = findViewById(R.id.buttoncolor);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorbool = !colorbool;
                paintBoard.setColor(colorbool);
            }
        });

        Button button1 = findViewById(R.id.buttonborder);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintBoard.setBorder(border);
                if(++border >3){
                    border = border -4;
                }
            }
        });


    }
}