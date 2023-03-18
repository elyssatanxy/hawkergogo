package com.example.hawkergogo;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import java.util.List;

public class FoodTitlesAdapter extends RecyclerView.Adapter<FoodTitlesAdapter.MyView> {
    // Replace CartItem with your item
    private List<FoodTitleItem> list;

    // View Holder class which
    // extends RecyclerView.ViewHolder
    public class MyView extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyView(View view) {
            super(view);

            // initialise TextView with id
            textView = (TextView) view.findViewById(R.id.textview);
            imageView = (ImageView) view.findViewById(R.id.img);
        }
    }

    // Constructor for adapter class
    // which takes a list of String type
    public FoodTitlesAdapter(List<FoodTitleItem> horizontalList)
    {
        this.list = horizontalList;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.food_title_scroll, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position)
    {
        FoodTitleItem item = list.get(position);
        holder.textView.setText(item.getTitle());
        holder.imageView.setImageResource(item.getImageId());
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}
