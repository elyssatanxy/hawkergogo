package com.example.hawkergogo;

import static androidx.core.content.ContextCompat.getColor;
import static androidx.core.content.ContextCompat.startActivity;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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

        CardView cardView;

        public MyView(View view) {
            super(view);

            // initialise TextView with id
            textView = (TextView) view.findViewById(R.id.foodTitle);
            titleInput = (TextView) view.findViewById(R.id.titlePlaceholder);
            imageView = (ImageView) view.findViewById(R.id.img);
            cardView = (CardView) view.findViewById(R.id.foodOption);
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
                Giveaway.setText(item.getTitle());
//                if  (holder.textView.getText().toString().equals("Add Item")) {
//                    v.getRootView().setVisibility(View.GONE);
//                    Dialog newDialog = new Dialog(v.getRootView().getContext());
//                    newDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                    newDialog.setContentView(R.layout.foodtitle_options);
//
//                    Button addButton = (Button) newDialog.findViewById(R.id.addButton);
//                    addButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            EditText newTiteInput = (EditText) newDialog.findViewById(R.id.descriptionInput);
//                            addNewTitle = newTiteInput.getEditableText().toString();
//                            Giveaway.setText(addNewTitle);
//                        }
//                    });
//
//                    Button cancelButton = (Button) newDialog.findViewById(R.id.cancelButton);
//                    cancelButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            newDialog.dismiss();
//                        }
//                    });
//                    newDialog.show();
//                } else {
//                    //holder.titleInput.setText(item.getTitle());
//                    Giveaway.setText(item.getTitle());
//                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}
