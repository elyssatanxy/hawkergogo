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


        // Set LayoutManager on Recycler View
        featuredRecycler.setLayoutManager(featuredRecyclerViewLayoutManager);

        // Adding items to RecyclerView.
        addItemsToFeaturedRecyclerViewArrayList();

        consumerMainAdapter = new ConsumerMainAdapter(consumerMainItemSource);

        // Set Horizontal Layout Manager
        // for Recycler view
        horizontalLayout = new LinearLayoutManager(ConsumerMain.this, LinearLayoutManager.HORIZONTAL, false);
        featuredRecycler.setLayoutManager(horizontalLayout);

        // Set adapter on recycler view
        featuredRecycler.setAdapter(consumerMainAdapter);

    }

    public void addItemsToFeaturedRecyclerViewArrayList() {
        // Adding items to ArrayList
        ConsumerMainItem item = new ConsumerMainItem(R.drawable.dbs_paylah, "DBS PayLah!");
        ConsumerMainItem item2 = new ConsumerMainItem(R.drawable.gpay, "Google Pay");
        consumerMainItemSource = new ArrayList<>();
        consumerMainItemSource.add(item);
        consumerMainItemSource.add(item2);
    }
}