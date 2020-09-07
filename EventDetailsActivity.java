package com.example.letsgojohor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EventDetailsActivity extends AppCompatActivity {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ImageView photo;
    private TextView eventName, eventDescription, eventLocation, eventContact,  eventDate;
    private String event_id = "";
    private FloatingActionButton map, bookmark;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        event_id = getIntent().getExtras().get("event_id").toString();

        photo = (ImageView) findViewById(R.id.img_details);
        eventName = (TextView) findViewById(R.id.destination);
        eventDescription = (TextView) findViewById(R.id.startDate);
        eventLocation = (TextView) findViewById(R.id.endDate);
        eventContact = (TextView) findViewById(R.id.tripDescription);
        eventDate = (TextView) findViewById(R.id.tripName);
        map =  findViewById(R.id.map);
        bookmark =  findViewById(R.id.bookmark);




        getEventDetail(event_id);
    }




    private void getEventDetail(String event_id) {

        DocumentReference docRef = db.collection("event").document(event_id);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        eventName.setText(document.getString("eventName"));
                        eventDescription.setText(document.getString("eventDescription"));
                        eventLocation.setText(document.getString("eventLocation"));
                        eventContact.setText(document.getString("eventContact"));
                        Picasso.get().load(document.getString("eventPhoto")).into(photo);
                        final String favorite_photo = document.getString("eventPhoto");

                        bookmark.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addFavorite(favorite_photo);
                            }
                        });
                        ///////PRICE///////////
                        /*
                        Float price = document.getLong("eventPrice").floatValue();
                        String ePrice = Float.toString(price);
                        eventPrice.setText("RM");
                        eventPrice.append(ePrice);*/

                        ////////DATE//////////
                        Date d = document.getDate("eventDate");
                        String pattern = "d MMMM yyyy h:mm a";
                        DateFormat df = new SimpleDateFormat(pattern);
                        String dateString = df.format(d);
                        eventDate.setText(dateString);

                        final String address = document.getString("eventLocation");

                        map.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(EventDetailsActivity.this, GetLocationActivity.class);
                                intent.putExtra("address", address);
                                startActivity(intent);
                            }
                        });




                        //ImageView imageView = findViewById(R.id.image_view);
                        //String url = "gs://letsgojohor-ccabf.appspot.com/featured_legoland.jpg";
                        //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(hotelImg);
                        /*
                         insert image from db to imageview


                         */



                    } else {
                        Toast.makeText(EventDetailsActivity.this, "Event does not exist!", Toast.LENGTH_SHORT).show();
                        //Log.d(TAG, "No such document");
                    }
                } else {
                    Toast.makeText(EventDetailsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                    //Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }

    private void addFavorite(String photo) {



        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Map<String, Object> newFavorite = new HashMap<>();
        newFavorite.put("favorite_id", event_id);
        newFavorite.put("user_id", user_id);
        newFavorite.put("favorite_name", eventName.getText().toString());
        newFavorite.put("favorite_photo", photo);

        //newTrip.put("startDate", getDateFromString(startDate.getText().toString()));

        db.collection("favorite").add(newFavorite)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(EventDetailsActivity.this, "Added to favorite",
                                Toast.LENGTH_SHORT).show();
                        // Intent intent = new Intent(AttractionDetailsActivity.this, FavoriteActivity.class);
                        // startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EventDetailsActivity.this, "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });

    }









}


/*
public TextView tvInfo;
private java.lang.CharSequence ID = "8945213624";

TextView tvInfo = (TextView)findViewById(R.id.tvInfo);
tvInfo.setText("My ID number is: ");
tvInfo.append(ID);
 */