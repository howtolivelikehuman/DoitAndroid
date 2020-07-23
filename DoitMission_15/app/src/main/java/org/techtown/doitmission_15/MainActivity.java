package org.techtown.doitmission_15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView2);

        Button button = findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerInfo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.move_right, R.anim.not_move);
                finish();
                overridePendingTransition(R.anim.move_left, R.anim.not_move);
            }
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if(name != null ){
            textView.setText( name + "\n" + intent.getStringExtra("age") + "\n" + intent.getStringExtra("date"));
        }

    }
}