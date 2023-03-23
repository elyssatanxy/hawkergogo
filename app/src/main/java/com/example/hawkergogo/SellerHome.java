package com.example.hawkergogo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.*;

import java.util.ArrayList;

public class SellerHome extends AppCompatActivity{

    RecyclerView itemsRecycler;
    RecyclerView.LayoutManager itemsRecyclerViewLayoutManager;
    LinearLayoutManager verticalLayout;
    ArrayList<Lisiting> itemsSource;
    PastAdapter itemsAdapter;

    RecyclerView ongoingRecycler;
    RecyclerView.LayoutManager ongoingRecyclerViewLayoutManager;
    LinearLayoutManager horizontalLayout;
    ArrayList<Lisiting> ongoingSource;
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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dashboard:
                        //start the Home activity
                        Intent dashboardIntent = new Intent(SellerHome.this, SellerHome.class);
                        startActivity(dashboardIntent);
                        break;
                    case R.id.add_item:
                        //start the Destination activity
                        Intent addIntent = new Intent(SellerHome.this, Giveaway.class);
                        startActivity(addIntent);
                        break;
                    case R.id.profile:
                        //start the Profile activity
                        Intent profileIntent = new Intent(SellerHome.this, ConsumerMain.class);
                        startActivity(profileIntent);
                        break;
                }
                return true;
            }
        });

    }

    public void addItemsToItemsRecyclerViewArrayList() {
        // Adding items to ArrayList
        Lisiting item = new Lisiting(R.drawable.sell_chickenrice, "Khicken Rice - Last 20 Plates!", 20,
                "Waterlooloo", "Yummy Chicken Rice", "10:00pm");
        Lisiting item2 = new Lisiting(R.drawable.sell_chickenrice, "Khicken Rice - Last 20 Plates!", 20,
                "Waterlooloo", "Yummy Chicken Rice", "10:00pm");
        itemsSource = new ArrayList<>();
        itemsSource.add(item);
        itemsSource.add(item2);
        itemsSource.add(item2);
        itemsSource.add(item);
        itemsSource.add(item2);
    }

    public void addItemsToOngoingRecyclerViewArrayList() {
        // Adding items to ArrayList
        Lisiting item = new Lisiting(R.drawable.sell_chickenrice, "Khicken Rice - Last 20 Plates!", 20,
                "Waterlooloo", "Yummy Chicken Rice", "10:00pm");
        Lisiting item2 = new Lisiting(R.drawable.sell_rice, "Khicken Rice - Hainanese Rice", 10,
                "Waterlooloo", "Yummy Chicken Rice", "10:00pm");
        ongoingSource = new ArrayList<>();
        ongoingSource.add(item);
        ongoingSource.add(item2);
    }

    public void goBack(View view) {
        super.finish();
    }

    public void goToAddItem(View view) {
        Intent intent = new Intent(this, Giveaway.class);
        Lisiting oldListing = new Lisiting(R.drawable.sell_chickenrice,"Khicken Rice - Last 10 Plates!", 10, "Maxwell Food Centre",
                getResources().getString(R.string.chickenricedesc), "10:00 PM");
        intent.putExtra("reGiveAway", oldListing);
        startActivity(intent);
    }

}