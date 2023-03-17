package com.example.hawkergogo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartPayment extends AppCompatActivity {
    RecyclerView paymentRecycler;
    RecyclerView.LayoutManager paymentRecyclerViewLayoutManager;
    LinearLayoutManager horizontalLayout;
    ArrayList<CartItem> paymentSource;
    Adapter paymentAdapter;

    RecyclerView itemsRecycler;
    RecyclerView.LayoutManager itemsRecyclerViewLayoutManager;
    LinearLayoutManager verticalLayout;
    ArrayList<CartItem> itemsSource;
    Adapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        // initialisation with id's
        paymentRecycler = (RecyclerView) findViewById(R.id.paymentRecycler);
        paymentRecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        itemsRecycler = (RecyclerView) findViewById(R.id.itemsRecycler);
        itemsRecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        // Set LayoutManager on Recycler View
        paymentRecycler.setLayoutManager(paymentRecyclerViewLayoutManager);
        itemsRecycler.setLayoutManager(itemsRecyclerViewLayoutManager);

        // Adding items to RecyclerView.
        addItemsToPaymentRecyclerViewArrayList();
        addItemsToItemsRecyclerViewArrayList();

        paymentAdapter = new Adapter(paymentSource);
        itemsAdapter = new Adapter(itemsSource);

        // Set Horizontal Layout Manager
        // for Recycler view
        horizontalLayout = new LinearLayoutManager(CartPayment.this, LinearLayoutManager.HORIZONTAL, false);
        paymentRecycler.setLayoutManager(horizontalLayout);

        verticalLayout = new LinearLayoutManager(CartPayment.this, LinearLayoutManager.VERTICAL, false);
        itemsRecycler.setLayoutManager(verticalLayout);

        // Set adapter on recycler view
        paymentRecycler.setAdapter(paymentAdapter);
        itemsRecycler.setAdapter(itemsAdapter);
    }

    // Function to add items in RecyclerView.
    public void addItemsToPaymentRecyclerViewArrayList() {
        // Adding items to ArrayList
        CartItem item = new CartItem(R.drawable.chickenrice, "Khicken Rice");
        paymentSource = new ArrayList<>();
        paymentSource.add(item);
    }

    public void addItemsToItemsRecyclerViewArrayList() {
        // Adding items to ArrayList
        CartItem item = new CartItem(R.drawable.caifan, "Khicken Rice");
        itemsSource = new ArrayList<>();
        itemsSource.add(item);
    }
}
