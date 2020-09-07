package com.example.letsgojohor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TravelActivity extends AppCompatActivity {


    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    //private ImageView placePhoto;
    private TextView cityName, cityInfo;
    private ImageView attraction, hotel, activity, event, transport;
            //favorite;
    private String city= "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        city = getIntent().getExtras().get("city").toString();

        attraction = findViewById(R.id.attraction);
        hotel = findViewById(R.id.hotel);
        activity = findViewById(R.id.activity);
        event = findViewById(R.id.event);
        transport = findViewById(R.id.transport);
        //favorite = findViewById(R.id.favorite);

        cityName= findViewById(R.id.cityName);
        cityInfo = findViewById(R.id.cityInfo);

        getCityDetail(city);

        attraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TravelActivity.this, AttractionActivity.class);
                intent.putExtra("city", city);
                startActivity(intent);
            }
        });

        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(TravelActivity.this, HotelActivity.class);
                intent2.putExtra("city", city);
                startActivity(intent2);
            }
        });

        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(TravelActivity.this, ActivityActivity.class);
                intent3.putExtra("city", city);
                startActivity(intent3);
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(TravelActivity.this, EventActivity.class);
                intent4.putExtra("city", city);
                startActivity(intent4);
            }
        });

        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(TravelActivity.this, TransportActivity.class);
                intent5.putExtra("city", city);
                startActivity(intent5);
            }
        });


        /*
       favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(TravelActivity.this, FavoriteActivity.class);
                startActivity(intent6);
            }
        });
       */

    }


    private void getCityDetail(String city) {

        DocumentReference docRef = db.collection("city").document(city);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        cityName.setText(document.getString("cityName"));
                        cityInfo.setText(document.getString("cityInfo"));


                    } else {
                        Toast.makeText(TravelActivity.this, "City does not exist!", Toast.LENGTH_SHORT).show();
                        //Log.d(TAG, "No such document");
                    }
                } else {
                    Toast.makeText(TravelActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                    //Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }




}
