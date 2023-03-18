package com.example.hawkergogo;

import static com.example.hawkergogo.R.id.searchBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import java.util.ArrayList;

public class ConsumerMain extends AppCompatActivity {
    RecyclerView featuredRecycler;
    RecyclerView.LayoutManager featuredRecyclerViewLayoutManager;
    LinearLayoutManager horizontalLayout;
    ArrayList<ConsumerMainItem> consumerMainItemSource;
    ConsumerMainAdapter consumerMainAdapter;

    RecyclerView pastOrdersRecycler;
    RecyclerView.LayoutManager pastOrdersRecyclerViewLayoutManager;
    LinearLayoutManager horizontalLayout2;
    ArrayList<ConsumerMainItem> consumerPastOrdersSource;

    ConsumerMainAdapter pastOrdersAdapter;

    RecyclerView moreFoodRecycler;
    RecyclerView.LayoutManager moreFoodRecyclerViewLayoutManager;
    LinearLayoutManager verticalLayout;
    ArrayList<CartItem> moreFoodSource;
    CartItemsAdapter moreFoodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_main);

        SearchView searchView = findViewById(searchBar);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });

        // initialisation with id's
        featuredRecycler = (RecyclerView) findViewById(R.id.featuredRecycler);
        featuredRecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        pastOrdersRecycler = (RecyclerView) findViewById(R.id.pastOrdersRecycler);
        pastOrdersRecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        moreFoodRecycler = (RecyclerView) findViewById(R.id.moreFoodRecycler);
        moreFoodRecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        // Set LayoutManager on Recycler View
        featuredRecycler.setLayoutManager(featuredRecyclerViewLayoutManager);
        pastOrdersRecycler.setLayoutManager(pastOrdersRecyclerViewLayoutManager);
        moreFoodRecycler.setLayoutManager(moreFoodRecyclerViewLayoutManager);

        // Adding items to RecyclerView.
        addItemsToFeaturedRecyclerViewArrayList();
        addItemsToPastOrdersRecycler();
        addItemsToMoreFoodRecyclerViewArrayList();

        consumerMainAdapter = new ConsumerMainAdapter(consumerMainItemSource);
        pastOrdersAdapter = new ConsumerMainAdapter(consumerPastOrdersSource);
        moreFoodAdapter = new CartItemsAdapter(moreFoodSource);

        // Set Horizontal Layout Manager
        // for Recycler view
        horizontalLayout = new LinearLayoutManager(ConsumerMain.this, LinearLayoutManager.HORIZONTAL, false);
        featuredRecycler.setLayoutManager(horizontalLayout);

        horizontalLayout2 = new LinearLayoutManager(ConsumerMain.this, LinearLayoutManager.HORIZONTAL, false);
        pastOrdersRecycler.setLayoutManager(horizontalLayout2);

        verticalLayout = new LinearLayoutManager(ConsumerMain.this, LinearLayoutManager.VERTICAL, false);
        moreFoodRecycler.setLayoutManager(verticalLayout);

        // Set adapter on recycler view
        featuredRecycler.setAdapter(consumerMainAdapter);
        pastOrdersRecycler.setAdapter(pastOrdersAdapter);
        moreFoodRecycler.setAdapter(moreFoodAdapter);

    }

    public void addItemsToFeaturedRecyclerViewArrayList() {
        // Adding items to ArrayList
        ConsumerMainItem item = new ConsumerMainItem(R.drawable.chickrice, "Knicken Rice - Last 20 Plates!", "9:30pm", 1);
        ConsumerMainItem item2 = new ConsumerMainItem(R.drawable.westernfood, "Bob's Western Diner's - 3 more pl...", "10:00pm");
        ConsumerMainItem item3 = new ConsumerMainItem(R.drawable.gpay, "Some placeholder for testing", "1:00pm");
        consumerMainItemSource = new ArrayList<>();
        consumerMainItemSource.add(item);
        consumerMainItemSource.add(item2);
        consumerMainItemSource.add(item3);
    }

    public void addItemsToPastOrdersRecycler() {
        // Adding items to ArrayList
        ConsumerMainItem item = new ConsumerMainItem(R.drawable.chickrice, "Knicken Rice - Last 20 Plates!", "9:30pm");
        ConsumerMainItem item2 = new ConsumerMainItem(R.drawable.westernfood, "Bob's Western Diner's - 3 more pl...", "10:00pm");
        ConsumerMainItem item3 = new ConsumerMainItem(R.drawable.gpay, "Some placeholder for testing", "1:00pm");
        consumerPastOrdersSource = new ArrayList<>();
        consumerPastOrdersSource.add(item);
        consumerPastOrdersSource.add(item2);
        consumerPastOrdersSource.add(item3);
    }

    public void addItemsToMoreFoodRecyclerViewArrayList() {
        // Adding items to ArrayList
        CartItem item = new CartItem(R.drawable.chickenrice, "Khicken Rice - Last 20 Plates!", "9:30 pm");
        CartItem item2 = new CartItem(R.drawable.caifan, "Lee's Cai Fan -  Sweet and ...", "10:00 pm");
        moreFoodSource = new ArrayList<>();
        moreFoodSource.add(item);
        moreFoodSource.add(item2);
    }
}