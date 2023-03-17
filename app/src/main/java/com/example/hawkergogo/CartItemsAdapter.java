package com.example.hawkergogo;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import java.util.List;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.MyView> {
    // Replace CartItem with your item
    private List<CartItem> list;

    // View Holder class which
    // extends RecyclerView.ViewHolder
    public class MyView extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewPickup;
        ImageView imageView;

        public MyView(View view) {
            super(view);

            // initialise TextView with id
            textViewTitle = (TextView) view.findViewById(R.id.textview);
            textViewPickup = (TextView) view.findViewById(R.id.pickup);
            imageView = (ImageView) view.findViewById(R.id.img);
        }
    }

    // Constructor for adapter class
    // which takes a list of String type
    public CartItemsAdapter(List<CartItem> horizontalList)
    {
        this.list = horizontalList;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.cart_item_scroll, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position)
    {
        CartItem item = list.get(position);
        holder.textViewTitle.setText(item.getTitle());
        holder.textViewPickup.setText(item.getPickup());
        holder.imageView.setImageResource(item.getImageId());
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}
