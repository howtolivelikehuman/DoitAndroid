package org.techtown.doitmission_25;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class imageAdapter extends RecyclerView.Adapter<imageAdapter.ViewHolder> {
    ArrayList<sampleimage> items = new ArrayList<sampleimage>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.imagelayout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull imageAdapter.ViewHolder holder, int position) {
        sampleimage item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void addItem(sampleimage item){
        items.add(item);
    }
    public void setItems(ArrayList<sampleimage> items){
        this.items = items;
    }
    public sampleimage getItem(int position){
        return items.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView textView2;

        public ViewHolder(View items){
            super(items);
            textView = items.findViewById(R.id.textView);
            textView2 = items.findViewById(R.id.textView2);
            imageView = items.findViewById(R.id.imageView);
        }
        public void setItem(sampleimage image){
            imageView.setImageBitmap(image.thumbnail);
            textView.setText(image.date);
            textView2.setText(image.name);
        }
    }
}
