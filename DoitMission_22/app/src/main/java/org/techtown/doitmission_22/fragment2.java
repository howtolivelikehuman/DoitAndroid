package org.techtown.doitmission_22;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class fragment2 extends Fragment {
    RecyclerView recyclerView;
    BookAdapter adapter;
    ArrayList<Book> booklist;

    //프래그먼트 -> 액티비티로 데이터 전송
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onDetach(Context context) {
        super.onDetach();
    }

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.fragment2, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new BookAdapter();
        Bundle bundle = getArguments();
        booklist = bundle.<Book>getParcelableArrayList("booklist");
        recyclerView.setAdapter(adapter);
        adapter.setItems(booklist);

        adapter.setOnItemClickListener(new OnBookClickListener() {
            @Override
            public void onItemClick(BookAdapter.ViewHolder holder, View view, int position) {
                Book book = adapter.getItem(position);
                Toast.makeText(container.getContext(), book.getContents(), Toast.LENGTH_LONG).show();
            }
        });

        adapter.notifyDataSetChanged();

        return view;
    }
}
