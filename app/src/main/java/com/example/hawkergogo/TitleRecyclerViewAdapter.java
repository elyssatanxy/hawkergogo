package com.example.hawkergogo;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import java.util.List;

public class TitleRecyclerViewAdapter extends RecyclerView.Adapter<TitleRecyclerViewAdapter.MyView> {
    // Replace CartItem with your item
    private List<FoodTitleItem> list;
    private int selectedItem = -1;
    static String addNewTitle;

    // View Holder class which
    // extends RecyclerView.ViewHolder
    public class MyView extends RecyclerView.ViewHolder {
        TextView textView;
        TextView titleInput;
        ImageView imageView;

        EditText descriptionInput;
        CardView cardView;

        public MyView(View view) {
            super(view);

            // initialise TextView with id
            textView = (TextView) view.findViewById(R.id.foodTitle);
            titleInput = (TextView) view.findViewById(R.id.titlePlaceholder);
            imageView = (ImageView) view.findViewById(R.id.img);
            cardView = (CardView) view.findViewById(R.id.foodOption);
            descriptionInput = (EditText) view.findViewById(R.id.descriptionInput);
        }
    }

    // Constructor for adapter class
    // which takes a list of String type
    public TitleRecyclerViewAdapter(List<FoodTitleItem> horizontalList)
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
        int inputPosition = holder.getAdapterPosition();
        FoodTitleItem item = list.get(inputPosition);
        holder.textView.setText(item.getTitle());
        holder.imageView.setImageResource(item.getImageId());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText descriptionInput = (EditText) v.getRootView().findViewById(R.id.descriptionInput);
                descriptionInput.setText(item.getTitle());
                Giveaway.setText(item.getTitle());
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}
