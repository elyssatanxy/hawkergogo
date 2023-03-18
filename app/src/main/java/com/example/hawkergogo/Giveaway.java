package com.example.hawkergogo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Giveaway extends AppCompatActivity {

    RecyclerView foodTitleRecycler;
    RecyclerView.LayoutManager foodTitleRecyclerViewLayoutManager;
    LinearLayoutManager horizontalLayout;
    ArrayList<FoodTitleItem> foodTitleItemSource;
    FoodTitlesAdapter foodTitlesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giveaway);
    }

    public void addItemsToFoodTitleArrayList() {
        // Adding items to ArrayList
        foodTitleItemSource = new ArrayList<>();
        for (String foodOption :getResources().getStringArray(R.array.food_name)) {
            foodTitleItemSource.add(new FoodTitleItem(foodOption, R.drawable.caifan));
        }

    }
}