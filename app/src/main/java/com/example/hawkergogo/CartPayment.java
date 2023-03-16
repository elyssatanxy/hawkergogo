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
    ArrayList<String> paymentSource;
    Adapter paymentAdapter;

    RecyclerView itemsRecycler;
    RecyclerView.LayoutManager itemsRecyclerViewLayoutManager;
    LinearLayoutManager verticalLayout;
    ArrayList<String> itemsSource;
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
        paymentSource = new ArrayList<>();
        paymentSource.add("DBS PayLah!");
        paymentSource.add("Apple Pay");
    }

    public void addItemsToItemsRecyclerViewArrayList() {
        // Adding items to ArrayList
        itemsSource = new ArrayList<>();
        itemsSource.add("Khicken Rice");
        itemsSource.add("Hainanese Rice");
    }
}
