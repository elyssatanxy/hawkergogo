package com.example.hawkergogo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;

public class SellerHome extends AppCompatActivity{

    RecyclerView itemsRecycler;
    RecyclerView.LayoutManager itemsRecyclerViewLayoutManager;
    LinearLayoutManager verticalLayout;
    ArrayList<Listing> itemsSource;
    PastAdapter itemsAdapter;

    RecyclerView ongoingRecycler;
    RecyclerView.LayoutManager ongoingRecyclerViewLayoutManager;
    LinearLayoutManager horizontalLayout;
    ArrayList<Listing> ongoingSource;
    OngoingAdapter ongoingAdapter;

    boolean firstVisit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_homepage);

        firstVisit =  true;

        // Call api to fetch the data
        String url = "http://100.24.242.101:3000/listings";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Adding items to RecyclerView.
                    addItemsToItemsRecyclerViewArrayList(response);
//                    addItemsToOngoingRecyclerViewArrayList(response);

                    itemsAdapter = new PastAdapter(itemsSource);
                    ongoingAdapter = new OngoingAdapter(ongoingSource);


                    verticalLayout = new LinearLayoutManager(SellerHome.this, LinearLayoutManager.VERTICAL, false);
                    itemsRecycler.setLayoutManager(verticalLayout);

                    horizontalLayout = new LinearLayoutManager(SellerHome.this, LinearLayoutManager.HORIZONTAL, false);
                    ongoingRecycler.setLayoutManager(horizontalLayout);

                    // Set adapter on recycler view
                    itemsRecycler.setAdapter(itemsAdapter);
                    ongoingRecycler.setAdapter(ongoingAdapter);
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

        itemsRecycler = (RecyclerView) findViewById(R.id.itemsRecycler);
        itemsRecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        ongoingRecycler = (RecyclerView) findViewById(R.id.ongoingRecycler);
        ongoingRecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        // Set LayoutManager on Recycler View
        itemsRecycler.setLayoutManager(itemsRecyclerViewLayoutManager);
        ongoingRecycler.setLayoutManager(ongoingRecyclerViewLayoutManager);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dashboard:
                        //start the Home activity
                        Intent dashboardIntent = new Intent(SellerHome.this, SellerHome.class);
                        finish();
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
                        finish();
                        startActivity(profileIntent);
                        break;
                }
                return true;
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        if (firstVisit == true) {
            firstVisit = false;
        } else {
            finish();
            startActivity(getIntent());
        }
    }

    public void addItemsToItemsRecyclerViewArrayList(JSONArray dataList) {
        // Adding items to ArrayList
        // Adding items to ArrayList
        LocalDate dateNow = LocalDate.now();
        itemsSource = new ArrayList<>();
        ongoingSource = new ArrayList<>();
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
                int portion;
                try {
                    portion =Integer.parseInt(portionremaining);
                }catch (Exception e)  {
                    portion = 0;
                }
                LocalDate pastDate = LocalDate.parse(date);
                Listing item = new Listing(picture, title, portion, location, description, endtime);
                item.setId(id);
                if (dateNow.isAfter(pastDate)){
                    itemsSource.add(item);
                } else {
                    ongoingSource.add(item);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void goBack(View view) {
        super.finish();
    }

}