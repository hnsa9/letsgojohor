package com.example.letsgojohor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.Locale;

public class GetLocationActivity extends AppCompatActivity {

    private String address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);

        address = getIntent().getExtras().get("address").toString();

        String map = "http://maps.google.co.in/maps?q=" + address;
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
        startActivity(i);

    }




}