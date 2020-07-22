package org.techtown.doitmission_14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        final ProductAdapter adapter = new ProductAdapter();

        adapter.addItem(new Product("구찌", "90만" , "세상에 12개 밖에 없음", R.drawable.birdmissile1));
        adapter.addItem(new Product("루이", "190만" , "세상에 1개 밖에 없음", R.drawable.birdmissile2));
        adapter.addItem(new Product("휠라", "9만" , "세상에 145개 밖에 없음", R.drawable.birdmissile3));
        adapter.addItem(new Product("슈프림", "90만" , "세상에 11개 밖에 없음", R.drawable.birdmissile4));
        adapter.addItem(new Product("아보키", "0.9만" , "세상에 11234개 밖에 없음", R.drawable.birdmissile5));
        adapter.addItem(new Product("바보", "100만" , "세상에 1111개 밖에 없음", R.drawable.birdmissile6));
        adapter.addItem(new Product("아메리카노", "90만" , "세상에 12개 밖에 없음", R.drawable.birdmissile1));
        adapter.addItem(new Product("횟초리", "190만" , "세상에 1개 밖에 없음", R.drawable.birdmissile2));
        adapter.addItem(new Product("찰싹", "9만" , "세상에 145개 밖에 없음", R.drawable.birdmissile3));

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnProductItemClickListener() {
            @Override
            public void onItemClick(ProductAdapter.ViewHolder holder, View view, int position) {
                Product item = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "이름 : " + item.getName() + "\n 가격 : " + item.getCost() +
                                "\n 설명 : " + item.getNotification(),Toast.LENGTH_LONG).show();
            }
        });

    }
}