package com.example.letsgojohor;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



public class HotelDetailsActivity extends AppCompatActivity {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ImageView hotelImg;
    private TextView hotelName, hotelDesc, hotelContact, hotelAddress, hotelAmenities ;
    private String hotel_id = "";
    private FloatingActionButton map, bookmark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);

       //get all data from collection hotels


        hotel_id = getIntent().getExtras().get("hotel_id").toString();



        hotelImg = (ImageView) findViewById(R.id.image_details);
        hotelName = (TextView) findViewById(R.id.hotelName);
        hotelDesc = (TextView) findViewById(R.id.hotelDescription);
        hotelContact = (TextView) findViewById(R.id.contact);
        hotelAddress = (TextView) findViewById(R.id.hotelAddress);
        hotelAmenities = (TextView) findViewById(R.id.amenities);

        map =  findViewById(R.id.map);
        bookmark =  findViewById(R.id.bookmark);



        getHotelDetail(hotel_id);

    }



    private void addFavorite(String photo) {



        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Map<String, Object> newFavorite = new HashMap<>();
        newFavorite.put("favorite_id", hotel_id);
        newFavorite.put("user_id", user_id);
        newFavorite.put("favorite_name", hotelName.getText().toString());
        newFavorite.put("favorite_photo", photo);

        //newTrip.put("startDate", getDateFromString(startDate.getText().toString()));

        db.collection("favorite").add(newFavorite)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(HotelDetailsActivity.this, "Added to favorite",
                                Toast.LENGTH_SHORT).show();
                        // Intent intent = new Intent(AttractionDetailsActivity.this, FavoriteActivity.class);
                        // startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(HotelDetailsActivity.this, "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });

    }

//db.collection('books').doc('fK3ddutEpD2qQqRMXNW5').get()
   // DocumentReference docRef = db.collection("cities").doc('SF').get();
   // DocumentReference docRef =
   // CollectionReference dbRef =  db.collection("hotels");

    private void getHotelDetail(String hotel_id) {

        DocumentReference docRef = db.collection("hotels").document(hotel_id);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        hotelName.setText(document.getString("hotelName"));
                        hotelDesc.setText(document.getString("hotelDescription"));
                        hotelContact.setText(document.getString("hotelContact"));
                        hotelAddress.setText(document.getString("hotelAddress"));
                        hotelAmenities.setText(document.getString("hotelAmenities"));
                        Picasso.get().load(document.getString("hotelPhoto")).into(hotelImg);

                        final String favorite_photo = document.getString("hotelPhoto");
                        final String address = document.getString("hotelAddress");

                        bookmark.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addFavorite(favorite_photo);
                            }
                        });

                        map.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(HotelDetailsActivity.this, GetLocationActivity.class);
                                intent.putExtra("address", address);
                                startActivity(intent);
                            }
                        });


                        /*
                         insert image from db to imageview


                         */


                    } else {
                        Toast.makeText(HotelDetailsActivity.this, "Hotel does not exist!", Toast.LENGTH_SHORT).show();
                        //Log.d(TAG, "No such document");
                    }
                } else {
                    Toast.makeText(HotelDetailsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                    //Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }










}