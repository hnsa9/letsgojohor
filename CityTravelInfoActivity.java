package com.example.letsgojohor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CityTravelInfoActivity extends AppCompatActivity {

    private ImageButton tangkak, segamat, kluang, mersing, kotatinggi, kulai, pontian, muar, jb, bp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_travel_info);


        tangkak = findViewById(R.id.tangkak);
        segamat = findViewById(R.id.segamat);
        kluang = findViewById(R.id.kluang);
        mersing = findViewById(R.id. mersing);
        kotatinggi = findViewById(R.id.kotatinggi);
        kulai = findViewById(R.id.kulai);
        pontian = findViewById(R.id.pontian);
        muar = findViewById(R.id.muar);
        jb = findViewById(R.id.jb);
        bp = findViewById(R.id.bp);


        tangkak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CityTravelInfoActivity.this, TravelActivity.class);
                intent.putExtra("city", "Tangkak");
                startActivity(intent);
            }
        });

        segamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(CityTravelInfoActivity.this, TravelActivity.class);
                intent2.putExtra("city", "Segamat");
                startActivity(intent2);
            }
        });

        kluang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(CityTravelInfoActivity.this, TravelActivity.class);
                intent3.putExtra("city", "Kluang");
                startActivity(intent3);
            }
        });

        mersing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(CityTravelInfoActivity.this, TravelActivity.class);
                intent4.putExtra("city", "Mersing");
                startActivity(intent4);
            }
        });

        kotatinggi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(CityTravelInfoActivity.this, TravelActivity.class);
                intent5.putExtra("city", "Kota Tinggi");
                startActivity(intent5);
            }
        });

        kulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(CityTravelInfoActivity.this, TravelActivity.class);
                intent6.putExtra("city", "Kulai");
                startActivity(intent6);
            }
        });

        pontian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent7 = new Intent(CityTravelInfoActivity.this, TravelActivity.class);
                intent7.putExtra("city", "Pontian");
                startActivity(intent7);
            }
        });

        muar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent8 = new Intent(CityTravelInfoActivity.this, TravelActivity.class);
                intent8.putExtra("city", "Muar");
                startActivity(intent8);
            }
        });

        jb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent9 = new Intent(CityTravelInfoActivity.this, TravelActivity.class);
                intent9.putExtra("city", "Johor Bahru");
                startActivity(intent9);
            }
        });

        bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent10 = new Intent(CityTravelInfoActivity.this,TravelActivity.class);
                intent10.putExtra("city", "Batu Pahat");
                startActivity(intent10);
            }
        });




    }
}