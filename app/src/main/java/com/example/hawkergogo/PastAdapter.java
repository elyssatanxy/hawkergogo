package com.example.hawkergogo;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import java.util.List;

public class PastAdapter extends RecyclerView.Adapter<PastAdapter.MyView> {
    // Replace CartItem with your item
    private List<CartItem> list;

    // View Holder class which
    // extends RecyclerView.ViewHolder
    public class MyView extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewPickup;
        ImageView imageView;
        ImageView repeatButton;

        public MyView(View view) {
            super(view);

            // initialise TextView with id
            textViewTitle = (TextView) view.findViewById(R.id.textview);
            textViewPickup = (TextView) view.findViewById(R.id.pickup);
            imageView = (ImageView) view.findViewById(R.id.img);
            repeatButton = (ImageView) view.findViewById(R.id.repeatbutton);
        }
    }

    // Constructor for adapter class
    // which takes a list of String type
    public PastAdapter(List<CartItem> horizontalList) {
        this.list = horizontalList;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.past, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {
        CartItem item = list.get(position);
        holder.textViewTitle.setText(item.getTitle());
        holder.textViewPickup.setText(item.getPickup());
        holder.imageView.setImageResource(item.getImageId());

        holder.repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GO TO ADD ITEM + PASS ALL THE DATA
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
