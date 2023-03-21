package com.example.hawkergogo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.TimePickerDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Giveaway extends AppCompatActivity{
    static String staticText;
    public static void setText(String text) {
        staticText = text;
    }

    RecyclerView foodTitleRecycler;
    RecyclerView.LayoutManager foodTitleRecyclerViewLayoutManager;
    LinearLayoutManager horizontalLayout;
    TitleRecyclerViewAdapter titleRecyclerViewAdapter;
    ArrayList<FoodTitleItem> foodTitleItemSource;
    int [] foodImages = {R.drawable.food_caifan, R.drawable.food_chickenrice, R.drawable.food_fishballnood, R.drawable.food_nasilemat, R.drawable.food_rotiplate};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giveaway);

        LinearLayout openCamera = (LinearLayout) findViewById(R.id.cameraContainer);
        TextView selectedTitle = findViewById(R.id.titlePlaceholder);
        EditText portionInput = (EditText) findViewById(R.id.portionInput);
        TextView openLocationName = (TextView) findViewById(R.id.locationNameInput);
        EditText descriptionInput = (EditText) findViewById(R.id.descriptionInput);
        EditText timeInput = (EditText) findViewById(R.id.timeInput);

        if (getIntent().hasExtra("reGiveAway")) {
            // get the Serializable data model class with the details in it
            Lisiting item = (Lisiting) getIntent().getSerializableExtra("reGiveAway");
            setImagePrefill(item.getImage());
            selectedTitle.setText(item.getTitle());
            portionInput.setText(String.valueOf(item.getPortions()));
            openLocationName.setText(item.getLocation());
            descriptionInput.setText(item.getDescription());
            timeInput.setText(item.getTime());
        }

        // =========== CAMERA INPUT () ===========
        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setImagePrefill(R.drawable.food_caifan);
            }
        });

        // =========== TITLE INPUT ===========
        RelativeLayout titleContainer = findViewById(R.id.titleContainer);
        selectedTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog foodDialog = new Dialog(view.getRootView().getContext());
                foodDialog.setContentView(R.layout.foodtitle_options);

                foodTitleRecycler = (RecyclerView) foodDialog.findViewById(R.id.foodRecycler);

                addItemsToFoodTitleArrayList();
                titleRecyclerViewAdapter = new TitleRecyclerViewAdapter(foodTitleItemSource);

                horizontalLayout = new LinearLayoutManager(foodDialog.getContext());
                horizontalLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
                foodTitleRecycler.setLayoutManager(horizontalLayout);

                foodTitleRecycler.setAdapter(titleRecyclerViewAdapter);

                foodDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                foodDialog.show();

                Button dialog_close = foodDialog.findViewById(R.id.close);
                dialog_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        foodDialog.dismiss();
                    }
                });

                Button closeButton = foodDialog.findViewById(R.id.cancelButton);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        foodDialog.dismiss();
                    }
                });

                Button addButton = foodDialog.findViewById(R.id.addButton);
                EditText newTitle = foodDialog.findViewById(R.id.descriptionInput);
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView titleInput = (TextView) findViewById(R.id.titlePlaceholder);
                        if(!newTitle.getText().toString().equals("")){
                            titleInput.setText(newTitle.getText().toString());
                        } else {
                            titleInput.setText(staticText);
                        }
                        foodDialog.dismiss();
                    }
                });
            }
        });

        // =========== LOCATION INPUT () ===========
        LinearLayout openLocation = (LinearLayout) findViewById(R.id.locationContainer);
        openLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLocationName.setText("Maxwell Food Centre");
            }
        });

        // =========== TIME INPUT ===========
        //  initiate the edit text
        timeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Giveaway.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        int hour = selectedHour;
                        int minutes = minute;
                        String timeSet = "";
                        if (hour > 12) {
                            hour -= 12;
                            timeSet = "PM";
                        } else if (hour == 0) {
                            hour += 12;
                            timeSet = "AM";
                        } else if (hour == 12) {
                            timeSet = "PM";
                        } else {
                            timeSet = "AM";
                        }

                        String min = "";
                        if (minutes < 10)
                            min = "0" + minutes;
                        else
                            min = String.valueOf(minutes);

                        // Append in a StringBuilder
                        String setTime = new StringBuilder().append(hour).append(':')
                                .append(min).append(" ").append(timeSet).toString();
                        timeInput.setText(setTime);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.show();
            }
        });

        // =========== SEND DATA ===========

        Button addButton = (Button) findViewById(R.id.addListing);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send function
                String url = "http://10.0.2.2:3000/listings";

                // Adding data
                Map<String, String> params = new HashMap();
                params.put("title", selectedTitle.getText().toString());
                params.put("portionremaining", portionInput.getText().toString());
                params.put("location", openLocationName.getText().toString());
                params.put("picture", "food_caifan");
                params.put("description", descriptionInput.getText().toString());
                params.put("endtime", timeInput.getText().toString());
                params.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

                JSONObject parameters = new JSONObject(params);

                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Giveaway.super.finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(jsonRequest);

//                Lisiting newListing = new Lisiting(R.drawable.food_caifan, "Cai Fan", Integer.getInteger(portionInput.getText().toString()),
//                        openLocationName.toString(), descriptionInput.getText().toString(), timeInput.getText().toString());
//
//                Giveaway.super.finish();
            }
        });

    }

    public void addItemsToFoodTitleArrayList() {
        String [] foodTitleArray = getResources().getStringArray(R.array.food_name);

        foodTitleItemSource = new ArrayList<>();
        for (int i = 0; i < foodTitleArray.length; i++) {
            foodTitleItemSource.add(new FoodTitleItem(foodTitleArray[i], foodImages[i]));
        }
//        foodTitleItemSource.add(new FoodTitleItem("Add Item", R.drawable.ic_add_button));
    }

    public void setImagePrefill(int imgName){
        LinearLayout openCamera = (LinearLayout) findViewById(R.id.cameraContainer);

        openCamera.removeAllViewsInLayout();
        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (10*scale + 0.5f);
        RelativeLayout rootLayout = new RelativeLayout(Giveaway.this);
        rootLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        //to retrieve image in res/drawable and set image in ImageView
        ImageView ivOne = new ImageView(Giveaway.this);
        ivOne.setImageResource(imgName);
        ivOne.setPadding(dpAsPixels, dpAsPixels, dpAsPixels, dpAsPixels);
        ivOne.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ivOne.setLayoutParams(params);
        openCamera.addView(ivOne);
    }

    public void goBack(View view) {
        super.finish();
    }


}