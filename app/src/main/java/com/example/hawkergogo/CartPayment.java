package com.example.hawkergogo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartPayment extends AppCompatActivity {
    RecyclerView paymentRecycler;
    RecyclerView.LayoutManager paymentRecyclerViewLayoutManager;
    LinearLayoutManager horizontalLayout;
    ArrayList<PaymentItem> paymentSource;
    PaymentAdapter paymentAdapter;

    RecyclerView itemsRecycler;
    RecyclerView.LayoutManager itemsRecyclerViewLayoutManager;
    LinearLayoutManager verticalLayout;
    ArrayList<CartItem> itemsSource;
    CartItemsAdapter itemsAdapter;

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

        paymentAdapter = new PaymentAdapter(paymentSource);
        itemsAdapter = new CartItemsAdapter(itemsSource);

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
        PaymentItem item = new PaymentItem(R.drawable.dbs_paylah, "DBS PayLah!");
        PaymentItem item2 = new PaymentItem(R.drawable.gpay, "Google Pay");
        paymentSource = new ArrayList<>();
        paymentSource.add(item);
        paymentSource.add(item2);
    }

    public void addItemsToItemsRecyclerViewArrayList() {
        // Adding items to ArrayList
        CartItem item = new CartItem("https://www.innit.com/public/recipes/images/1033246--742330450-en-US-0_s1000.jpg", "Knicken Rice - Last 20 Plates!", "9:30pm");
        CartItem item2 = new CartItem("https://burpple-3.imgix.net/foods/4953a157939f12e66921893991_original.", "Bob's Western Diner's - 3 more pl...", "10:00pm");
        itemsSource = new ArrayList<>();
        itemsSource.add(item);
        itemsSource.add(item2);
    }

    public void goBack(View view) {
        super.finish();
    }
}
