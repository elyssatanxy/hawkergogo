package com.example.hawkergogo;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ConsumerMainAdapter extends RecyclerView.Adapter<ConsumerMainAdapter.MyView> {
    private List<CartItem> list;

    Dialog myDialog;

    RecyclerView recRecycler;
    LinearLayoutManager recHorizontalLayout;
    ArrayList<CartItem> recSource;
    ConsumerMainAdapter recAdapter;

    public class MyView extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        ImageView imageView;

        TextView qty;

        CardView cardView;

        public MyView(View view) {
            super(view);

            // initialise TextView with id
            textView = (TextView) view.findViewById(R.id.titleText);
            imageView = (ImageView) view.findViewById(R.id.img2);
            textView2 = (TextView) view.findViewById(R.id.pickupTimeText);
            qty = (TextView) view.findViewById(R.id.qty);
            cardView = (CardView) view.findViewById(R.id.itemCard);
        }
    }

    // Constructor for adapter class
    // which takes a list of String type
    public ConsumerMainAdapter(List<CartItem> horizontalList) {
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
        CartItem item = list.get(position);
        holder.textView.setText(item.getTitle());
        holder.imageView.setImageResource(item.getImageId());
        holder.textView2.setText(item.getPickup());

        if(item.getQty() != 0) {
            holder.qty.setVisibility(View.VISIBLE);
            holder.qty.setText(Integer.toString(item.getQty()));
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog = new Dialog(v.getRootView().getContext());
                myDialog.setContentView(R.layout.listing);

                TextView dialog_title = myDialog.findViewById(R.id.listing_title);
                TextView dialog_pickup = myDialog.findViewById(R.id.listing_pickup);
                // TextView dialog_portions = myDialog.findViewById(R.id.listing_portions);
                TextView dialog_quantity = myDialog.findViewById(R.id.quantity);
                ImageView dialog_img = myDialog.findViewById(R.id.listing_img);

                dialog_title.setText(item.getTitle());
                dialog_pickup.setText(item.getPickup());
                // dialog_portions.setText(item.getQty());
                dialog_quantity.setText("" + item.getQty());
                dialog_img.setImageResource(item.getImageId());

                recRecycler = (RecyclerView) myDialog.findViewById(R.id.recRecycler);

                addItemsToRecRecyclerViewArrayList();
                recAdapter = new ConsumerMainAdapter(recSource);

                recHorizontalLayout = new LinearLayoutManager(myDialog.getContext());
                recHorizontalLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
                recRecycler.setLayoutManager(recHorizontalLayout);

                recRecycler.setAdapter(recAdapter);

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();

                Button dialog_close = myDialog.findViewById(R.id.close);
                dialog_close.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                Button dialog_add = myDialog.findViewById(R.id.add);
                dialog_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView dialog_quantity = myDialog.findViewById(R.id.quantity);
                        dialog_quantity.setText("" + (item.getQty() + 1));
                        item.setQty(item.getQty() + 1);
                    }
                });

                Button dialog_minus = myDialog.findViewById(R.id.minus);
                dialog_minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView dialog_quantity = myDialog.findViewById(R.id.quantity);
                        if (item.getQty() >= 1) {
                            dialog_quantity.setText("" + (item.getQty() - 1));
                            item.setQty(item.getQty() - 1);
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItemsToRecRecyclerViewArrayList() {
        // Adding items to ArrayList
        CartItem item = new CartItem(R.drawable.chickrice, "Knicken Rice - Last 20 Plates!", "9:30pm");
        CartItem item2 = new CartItem(R.drawable.westernfood, "Bob's Western Diner's - 3 more pl...", "10:00pm");
        recSource = new ArrayList<>();
        recSource.add(item);
        recSource.add(item2);
    }
}
