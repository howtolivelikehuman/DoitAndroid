package org.techtown.doitmission_13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editName;
    EditText editDate;
    EditText editNumber;
    TextView textNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        final PersonAdapter adapter = new PersonAdapter();
        recyclerView.setAdapter(adapter);

        editName = findViewById(R.id.editName);
        editDate = findViewById(R.id.editDate);
        editNumber = findViewById(R.id.editNumber);
        textNum = findViewById(R.id.textNum);



        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString();
                String date = editDate.getText().toString();
                String number = editNumber.getText().toString();

                adapter.addItem(new Person(name,date,number));
                adapter.notifyDataSetChanged();
                textNum.setText(adapter.getItemCount() + "명");
            }
        });
    }
}