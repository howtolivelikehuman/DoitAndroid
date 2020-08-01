package org.techtown.doitmission_22;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;



public class fragment1 extends Fragment {
    EditText editName;
    EditText editWriter;
    EditText editContents;
    private MainActivity activity;

    Book book;

    //프래그먼트 -> 액티비티로 데이터 전송
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity){
            this.activity = (MainActivity) context;
        }
    }

    public void onDetach(Context context) {
        super.onDetach();
        activity = null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);
        Button button = rootview.findViewById(R.id.button);

        editName = rootview.findViewById(R.id.editName);
        editWriter = rootview.findViewById(R.id.editWriter);
        editContents = rootview.findViewById(R.id.editContent);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = (editName.getText().toString());
                String writer = (editWriter.getText().toString());
                String contents = (editContents.getText().toString());
                book = new Book(name, writer,contents);
                ((sendDataListener)activity).sendata(book);
            }
        });

    return rootview;
    }
    public interface sendDataListener{
        void sendata(Book book);
    }

}
