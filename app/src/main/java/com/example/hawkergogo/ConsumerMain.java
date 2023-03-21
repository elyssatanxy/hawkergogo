package com.example.hawkergogo;

import static com.example.hawkergogo.R.id.searchBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import java.time.LocalDate;
import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ConsumerMain extends AppCompatActivity {
    RecyclerView featuredRecycler;
    RecyclerView.LayoutManager featuredRecyclerViewLayoutManager;
    LinearLayoutManager horizontalLayout;
    ArrayList<CartItem> consumerMainItemSource;
    ConsumerMainAdapter consumerMainAdapter;

    RecyclerView pastOrdersRecycler;
    RecyclerView.LayoutManager pastOrdersRecyclerViewLayoutManager;
    LinearLayoutManager horizontalLayout2;
    ArrayList<CartItem> consumerPastOrdersSource;

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


        // Call api to fetch the data
        String url = "http://10.0.2.2:3000/listings";
        JsonArrayRequest  jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Adding items to RecyclerView.
                    addItemsToFeaturedRecyclerViewArrayList(response);
                    addItemsToMoreFoodRecyclerViewArrayList(response);

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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                System.out.println("Error! API couldn't be reached");
            }

        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

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
    }

    public void addItemsToFeaturedRecyclerViewArrayList(JSONArray dataList) {
        // Adding items to ArrayList
        LocalDate dateNow = LocalDate.now();
        System.out.println(dateNow);
        consumerMainItemSource = new ArrayList<>();
        consumerPastOrdersSource = new ArrayList<>();
        for (int i = 0; i<dataList.length(); i++) {
            JSONObject listItem = null;
            try {
                listItem = dataList.getJSONObject(i);
                int id = listItem.getInt("id");
                String title = listItem.getString("title");
                String picture = listItem.getString("picture");
                String description = listItem.getString("description");
                String endtime = listItem.getString("endtime");
                String date = listItem.getString("date");
                String location = listItem.getString("location");
                String portionremaining = listItem.getString("portionremaining");
                LocalDate pastDate = LocalDate.parse(date);
                if (dateNow.isAfter(pastDate)){
                    CartItem item = new CartItem(getResources().getIdentifier(picture, "drawable", getPackageName()), title, endtime);
                    consumerPastOrdersSource.add(item);
                } else {
                    CartItem item = new CartItem(getResources().getIdentifier(picture, "drawable", getPackageName()), title, endtime, portionremaining);
                    consumerMainItemSource.add(item);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addItemsToMoreFoodRecyclerViewArrayList(JSONArray dataList) {
        // Adding items to ArrayList
        CartItem item = new CartItem(R.drawable.sell_chickenrice, "Khicken Rice - Last 20 Plates!", "9:30 pm");
        CartItem item2 = new CartItem(R.drawable.food_caifan, "Lee's Cai Fan -  Sweet and ...", "10:00 pm");
        moreFoodSource = new ArrayList<>();
        moreFoodSource.add(item);
        moreFoodSource.add(item2);
    }
}