package com.example.hawkergogo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToCart(View view) {
        Intent intent = new Intent(MainActivity.this, CartPayment.class);
        startActivity(intent);
    }

    public void goToConsumerMain(View view) {
        Intent intent = new Intent(MainActivity.this, consumerMain.class);
        startActivity(intent);
    }

    public void goTofoodOptions(View view) {
        Intent intent = new Intent(MainActivity.this, Giveaway.class);
        startActivity(intent);
    }
}