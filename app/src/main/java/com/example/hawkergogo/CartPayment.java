package com.example.hawkergogo;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    public static Set<CartItem> inCart = new HashSet<>();
    Button reserveFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        // initialisation with id's
        paymentRecycler = (RecyclerView) findViewById(R.id.paymentRecycler);
        paymentRecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        itemsRecycler = (RecyclerView) findViewById(R.id.itemsRecycler);
        itemsRecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        TextView emptyRecyclerText = (TextView) findViewById(R.id.empty_view);
        reserveFood = findViewById(R.id.bookFood);

        if (inCart.isEmpty()) {
            itemsRecycler.setVisibility(View.GONE);
            emptyRecyclerText.setVisibility(View.VISIBLE);
        }
        else {
            itemsRecycler.setVisibility(View.VISIBLE);
            emptyRecyclerText.setVisibility(View.GONE);
            itemsRecycler.setLayoutManager(itemsRecyclerViewLayoutManager);
            ArrayList<CartItem> arrListInCart = new ArrayList<CartItem>(inCart);
            itemsAdapter = new CartItemsAdapter(arrListInCart);
            verticalLayout = new LinearLayoutManager(CartPayment.this, LinearLayoutManager.VERTICAL, false);
            itemsRecycler.setLayoutManager(verticalLayout);
            itemsRecycler.setAdapter(itemsAdapter);
        }

        // Set LayoutManager on Recycler View
        paymentRecycler.setLayoutManager(paymentRecyclerViewLayoutManager);


        // Adding items to RecyclerView.
        addItemsToPaymentRecyclerViewArrayList();

        paymentAdapter = new PaymentAdapter(paymentSource);

        // Set Horizontal Layout Manager
        // for Recycler view
        horizontalLayout = new LinearLayoutManager(CartPayment.this, LinearLayoutManager.HORIZONTAL, false);
        paymentRecycler.setLayoutManager(horizontalLayout);

        // Set adapter on recycler view
        paymentRecycler.setAdapter(paymentAdapter);

    }

    public void reserveFoody(View view){
        ArrayList<CartItem> arrListInCart = new ArrayList<CartItem>(inCart);
        for (int i=0; i < arrListInCart.size(); i++){
            CartItem item = arrListInCart.get(i);
            int id = item.getId();
            int count = item.getCount();
            int remains = item.getQty() - count;

            // call api to update the total count at seller
            String url = "http://100.24.242.101:3000/listings";
            Map<String, String> params = new HashMap();
            params.put("id", String.valueOf(id));
            params.put("title", item.getTitle());
            params.put("portionremaining", String.valueOf(remains));
            params.put("location", item.getLocation());
            params.put("picture", item.getImageId());
            params.put("description", item.getDesc());
            params.put("endtime", item.getPickup());
            params.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            JSONObject parameters = new JSONObject(params);
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.PUT, url, parameters, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    inCart.clear();
                    item.setCount(0);
                    CartPayment.super.finish();
                    CartPayment.super.onRestart();
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(jsonRequest);
        }
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

    public void addItemsToItemsRecyclerViewArrayList() { //
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
