package com.example.hawkergogo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class Giveaway extends AppCompatActivity{

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

        // =========== CAMERA INPUT () ===========
        LinearLayout openCamera = (LinearLayout) findViewById(R.id.cameraContainer);
        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera.removeAllViewsInLayout();
                float scale = getResources().getDisplayMetrics().density;
                int dpAsPixels = (int) (10*scale + 0.5f);
                RelativeLayout rootLayout = new RelativeLayout(Giveaway.this);
                rootLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.CENTER_IN_PARENT);
                ImageView ivOne = new ImageView(Giveaway.this);
                ivOne.setImageResource(R.drawable.food_caifan);
                ivOne.setPadding(dpAsPixels, dpAsPixels, dpAsPixels, dpAsPixels);
                ivOne.setScaleType(ImageView.ScaleType.FIT_CENTER);
                ivOne.setLayoutParams(params);
                openCamera.addView(ivOne);
            }
        });

        // =========== TITLE INPUT ===========
        TextView selectedTitle = findViewById(R.id.titlePlaceholder);
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
            }
        });

        // =========== LOCATION INPUT () ===========
        LinearLayout openLocation = (LinearLayout) findViewById(R.id.locationContainer);
        TextView openLocationName = (TextView) findViewById(R.id.locationNameInput);
        openLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLocationName.setText("Maxwell Food Centre");
            }
        });

        // =========== TIME INPUT ===========
        //  initiate the edit text
        EditText timeInput = (EditText) findViewById(R.id.timeInput);
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
                        } else if (hour == 12){
                            timeSet = "PM";
                        }else{
                            timeSet = "AM";
                        }

                        String min = "";
                        if (minutes < 10)
                            min = "0" + minutes ;
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

        EditText portionInput = (EditText) findViewById(R.id.portionInput);
        EditText descriptionInput = (EditText) findViewById(R.id.descriptionInput);

        Button addButton = (Button) findViewById(R.id.addListing);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataInput = portionInput.getText().toString() + " " + descriptionInput.getText().toString() + " " + timeInput.getText();
                Intent intent = new Intent(getApplicationContext(), testData.class);
                intent.putExtra("data_input", dataInput);
                startActivity(intent);
            }
        });
    }

    public void addItemsToFoodTitleArrayList() {
        // data
        String [] foodTitleArray = getResources().getStringArray(R.array.food_name);

        // add items to local arraylist
        foodTitleItemSource = new ArrayList<>();
        for (int i = 0; i < foodTitleArray.length; i++) {
            foodTitleItemSource.add(new FoodTitleItem(foodTitleArray[i], foodImages[i]));
        }
        foodTitleItemSource.add(new FoodTitleItem("Add Item", R.drawable.ic_add_button));
    }

    public void goBack(View view) {
        super.finish();
    }
}