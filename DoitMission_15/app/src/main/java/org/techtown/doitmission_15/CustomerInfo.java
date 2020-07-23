package org.techtown.doitmission_15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CustomerInfo extends AppCompatActivity {
    EditText editName;
    EditText editAge;
    EditText editDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info);

        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editDate = findViewById(R.id.editDate);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                String name = editName.getText().toString();
                String age = editAge.getText().toString();
                String date = editDate.getText().toString();
                intent.putExtra("name" , name);
                intent.putExtra("age" , age);
                intent.putExtra("date" , date);

                startActivity(intent);
                overridePendingTransition(R.anim.move_right, R.anim.not_move);
                finish();
                overridePendingTransition(R.anim.move_left, R.anim.not_move);
            }
        });
    }
}