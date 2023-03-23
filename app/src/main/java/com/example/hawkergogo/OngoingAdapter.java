package com.example.hawkergogo;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import java.util.List;

public class OngoingAdapter extends RecyclerView.Adapter<OngoingAdapter.MyView>{
    private List<Listing> list;

    private Context context;

    // View Holder class which
    // extends RecyclerView.ViewHolder
    public class MyView extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewReserved;
        TextView textViewPickup;
        ImageView imageView;

        ImageView floatingActionButton;

        public MyView(View view) {
            super(view);

            // initialise TextView with id
            textViewTitle = (TextView) view.findViewById(R.id.title);
            textViewReserved = (TextView) view.findViewById(R.id.reserved);
            textViewPickup = (TextView) view.findViewById(R.id.pickup);
            imageView = (ImageView) view.findViewById(R.id.img);
            floatingActionButton = (ImageView) view.findViewById(R.id.floatingActionButton);
        }
    }

    // Constructor for adapter class
    // which takes a list of String type
    public OngoingAdapter(List<Listing> horizontalList)
    {
        this.list = horizontalList;
    }

    @Override
    public OngoingAdapter.MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.ongoing, parent, false);

        return new OngoingAdapter.MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final OngoingAdapter.MyView holder, final int position)
    {
        Listing item = list.get(position);
        holder.textViewTitle.setText(item.getTitle());
        holder.textViewReserved.setText(String.valueOf(item.getPortions()));
        holder.textViewPickup.setText(item.getTime());
        holder.imageView.setImageResource(item.getImage());
        holder.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(), Giveaway.class);
                intent.putExtra("editOrder", item);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().getApplicationContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

}

