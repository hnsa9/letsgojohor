package com.example.letsgojohor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.letsgojohor.model.Favorite;

public class MainActivity extends AppCompatActivity {

    ImageView travel, itinerary, tips, currency, favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        travel = findViewById(R.id.travelinfo);
        itinerary = findViewById(R.id.itinerary);
        tips = findViewById(R.id.traveltips);
        currency = findViewById(R.id.currency);
        favorite = findViewById(R.id.favorite);



        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CityTravelInfoActivity.class);
                startActivity(intent);
            }
        });

        itinerary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this,TripPageActivity.class);
                startActivity(intent2);
            }
        });

        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, TravelTipsActivity.class);
                startActivity(intent3);
            }
        });

         currency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(MainActivity.this, CurrencyActivity.class);
                startActivity(intent4);
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(intent5);
            }
        });


    }





}
