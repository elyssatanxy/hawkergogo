package com.example.hawkergogo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class FoodTitle extends AppCompatActivity {

    RecyclerView foodTitleRecycler;
    RecyclerView.LayoutManager foodTitleRecyclerViewLayoutManager;
    LinearLayoutManager horizontalLayout;
    ArrayList<FoodTitleItem> foodTitleItemSource;
    FoodTitlesAdapter foodTitlesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_title);

        // initialisation with id's
        foodTitleRecycler = (RecyclerView) findViewById(R.id.foodTitleRecycler);
        foodTitleRecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());


        // Set LayoutManager on Recycler View
        foodTitleRecycler.setLayoutManager(foodTitleRecyclerViewLayoutManager);

        // Adding items to RecyclerView.
        addItemsToFoodTitleArrayList();

        foodTitlesAdapter = new FoodTitlesAdapter(foodTitleItemSource);

        // Set Horizontal Layout Manager
        // for Recycler view
        horizontalLayout = new LinearLayoutManager(FoodTitle.this, LinearLayoutManager.HORIZONTAL, false);
        foodTitleRecycler.setLayoutManager(horizontalLayout);

        // Set adapter on recycler view
        foodTitleRecycler.setAdapter(foodTitlesAdapter);

    }

    public void addItemsToFoodTitleArrayList() {
        // Adding items to ArrayList
        foodTitleItemSource = new ArrayList<>();
        for (String foodOption :getResources().getStringArray(R.array.food_name)) {
            foodTitleItemSource.add(new FoodTitleItem(foodOption, R.drawable.caifan));
        }

    }
    public void goBack(View view) {
        super.finish();
    }
}