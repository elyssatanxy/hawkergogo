package com.example.hawkergogo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.app.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

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

        LinearLayout openCamera = (LinearLayout) findViewById(R.id.openCamera);

        //  initiate the edit text
        EditText timeInput = (EditText) findViewById(R.id.time);
        // perform click event listener on edit text
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
    }

    public void addItemsToFoodTitleArrayList() {
        // Adding items to ArrayList
        foodTitleItemSource = new ArrayList<>();
        for (String foodOption :getResources().getStringArray(R.array.food_name)) {
            foodTitleItemSource.add(new FoodTitleItem(foodOption, R.drawable.caifan));
        }

    }
}