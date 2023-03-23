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
import java.util.regex.Pattern;

public class PastAdapter extends RecyclerView.Adapter<PastAdapter.MyView> {
    // Replace CartItem with your item
    private List<Listing> list;
    private Context context;
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
    public PastAdapter(List<Listing> horizontalList) {
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
        Listing item = list.get(position);
        holder.textViewTitle.setText(item.getTitle());
        holder.textViewPickup.setText(item.getTime());
        holder.imageView.setImageResource(item.getImage());

        holder.repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(), Giveaway.class);
                intent.putExtra("repeatOrder", item);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().getApplicationContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
