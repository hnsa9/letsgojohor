package com.example.letsgojohor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TravelTipsActivity extends AppCompatActivity {

    private Button general, language, planning, culture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_tips);

        general = findViewById(R.id.general);
        language = findViewById(R.id.language);
        planning = findViewById(R.id.planning);
        culture = findViewById(R.id.culture);



        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent( TravelTipsActivity.this,  TravelTipsDetailsActivity.class);
                intent1.putExtra("category", "General");
                startActivity(intent1);
            }
        });

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(TravelTipsActivity.this,TravelTipsDetailsActivity.class);
                intent2.putExtra("category", "Language");
                startActivity(intent2);
            }
        });

        planning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(TravelTipsActivity.this, TravelTipsDetailsActivity.class);
                intent3.putExtra("category", "Planning");
                startActivity(intent3);
            }
        });

        culture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(TravelTipsActivity.this, TravelTipsDetailsActivity.class);
                intent4.putExtra("category", "Culture");
                startActivity(intent4);
            }
        });


    }
}