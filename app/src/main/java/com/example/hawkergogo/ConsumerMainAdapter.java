package com.example.hawkergogo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ConsumerMainAdapter extends RecyclerView.Adapter<ConsumerMainAdapter.MyView> {
    private List<ConsumerMainItem> list;

    public class MyView extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        ImageView imageView;

        TextView qty;

        public MyView(View view) {
            super(view);

            // initialise TextView with id
            textView = (TextView) view.findViewById(R.id.titleText);
            imageView = (ImageView) view.findViewById(R.id.img2);
            textView2 = (TextView) view.findViewById(R.id.pickupTimeText);
            qty = (TextView) view.findViewById(R.id.qty);
        }
    }

    // Constructor for adapter class
    // which takes a list of String type
    public ConsumerMainAdapter(List<ConsumerMainItem> horizontalList) {
        this.list = horizontalList;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.consumer_main_cards_scroll, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {
        ConsumerMainItem item = list.get(position);
        holder.textView.setText(item.getTitle());
        holder.imageView.setImageResource(item.getImageId());
        holder.textView2.setText(item.getTiming());

        if(item.getQty() != 0) {
            holder.qty.setVisibility(View.VISIBLE);
            holder.qty.setText(Integer.toString(item.getQty()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
