package com.example.hawkergogo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SellerHome extends AppCompatActivity{

    RecyclerView itemsRecycler;
    RecyclerView.LayoutManager itemsRecyclerViewLayoutManager;
    LinearLayoutManager verticalLayout;
    ArrayList<CartItem> itemsSource;
    PastAdapter itemsAdapter;

    RecyclerView ongoingRecycler;
    RecyclerView.LayoutManager ongoingRecyclerViewLayoutManager;
    LinearLayoutManager horizontalLayout;
    ArrayList<CartItem> ongoingSource;
    OngoingAdapter ongoingAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_homepage);

        itemsRecycler = (RecyclerView) findViewById(R.id.itemsRecycler);
        itemsRecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        ongoingRecycler = (RecyclerView) findViewById(R.id.ongoingRecycler);
        ongoingRecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        // Set LayoutManager on Recycler View
        itemsRecycler.setLayoutManager(itemsRecyclerViewLayoutManager);
        ongoingRecycler.setLayoutManager(ongoingRecyclerViewLayoutManager);

        // Adding items to RecyclerView.
        addItemsToItemsRecyclerViewArrayList();
        addItemsToOngoingRecyclerViewArrayList();


        itemsAdapter = new PastAdapter(itemsSource);
        ongoingAdapter = new OngoingAdapter(ongoingSource);


        verticalLayout = new LinearLayoutManager(SellerHome.this, LinearLayoutManager.VERTICAL, false);
        itemsRecycler.setLayoutManager(verticalLayout);

        horizontalLayout = new LinearLayoutManager(SellerHome.this, LinearLayoutManager.HORIZONTAL, false);
        ongoingRecycler.setLayoutManager(horizontalLayout);

        // Set adapter on recycler view
        itemsRecycler.setAdapter(itemsAdapter);
        ongoingRecycler.setAdapter(ongoingAdapter);

    }

    public void addItemsToItemsRecyclerViewArrayList() {
        // Adding items to ArrayList
        CartItem item = new CartItem(R.drawable.chickenrice, "Khicken Rice - Last 20 Plates!", "9:30 pm");
        CartItem item2 = new CartItem(R.drawable.chickenrice, "Khicken Rice -  Last 10 Plates!", "10:00 pm");
        itemsSource = new ArrayList<>();
        itemsSource.add(item);
        itemsSource.add(item2);
        itemsSource.add(item2);
        itemsSource.add(item);
        itemsSource.add(item2);
    }

    public void addItemsToOngoingRecyclerViewArrayList() {
        // Adding items to ArrayList
        CartItem item = new CartItem(R.drawable.chickenrice, "Khicken Rice - Last 20 Plates!", "10:00 pm", "3/20");
        CartItem item2 = new CartItem(R.drawable.rice, "Khicken Rice -  Hainanese Rice", "10:00 pm", "5/10");
        ongoingSource = new ArrayList<>();
        ongoingSource.add(item);
        ongoingSource.add(item2);
    }

    public void goBack(View view) {
        super.finish();
    }

    public void goToAddItem(View view) {
        Intent intent = new Intent(this, Giveaway.class);
        startActivity(intent);
    }
}