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

import java.util.HashMap;
import java.util.Map;

public class ActivityDetailsActivity extends AppCompatActivity {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ImageView activityPhoto;
    private TextView activityAddress, activityContact, activityDescription, activityName ;
    private String activity_id = "";
    private FloatingActionButton map, bookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activity);

        activity_id = getIntent().getExtras().get("activity_id").toString();



        activityPhoto = (ImageView) findViewById(R.id.image_details);
        activityAddress = (TextView) findViewById(R.id.activityAddress);
        activityContact = (TextView) findViewById(R.id.activityContact);
        activityDescription = (TextView) findViewById(R.id.activityDescription);
        activityName = (TextView) findViewById(R.id.activityName);

        map =  findViewById(R.id.map);
        bookmark =  findViewById(R.id.bookmark);




        getActivityDetail(activity_id);
    }






    private void getActivityDetail(String activity_id) {

        DocumentReference docRef = db.collection("activity").document(activity_id);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                        activityName.setText(document.getString("activityName"));
                        activityAddress.setText(document.getString("activityAddress"));
                        activityContact.setText(document.getString("activityContact"));
                        activityDescription.setText(document.getString("activityDescription"));
                        Picasso.get().load(document.getString("activityPhoto")).into(activityPhoto);


                        final String favorite_photo = document.getString("activityPhoto");
                        //ImageView imageView = findViewById(R.id.image_view);
                        //String url = "gs://letsgojohor-ccabf.appspot.com/featured_legoland.jpg";
                        //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(hotelImg);
                        /*
                         insert image from db to imageview


                         */

                        bookmark.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addFavorite(favorite_photo);
                            }
                        });


                        final String activity_address = document.getString("activityAddress");

                        map.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(ActivityDetailsActivity.this, GetLocationActivity.class);
                                intent.putExtra("address", activity_address);
                                startActivity(intent);
                            }
                        });






                    } else {
                        Toast.makeText(ActivityDetailsActivity.this, "Activity does not exist!", Toast.LENGTH_SHORT).show();
                        //Log.d(TAG, "No such document");
                    }
                } else {
                    Toast.makeText(ActivityDetailsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                    //Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }


    private void addFavorite(String photo) {


        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Map<String, Object> newFavorite = new HashMap<>();
        newFavorite.put("favorite_id", activity_id);
        newFavorite.put("user_id", user_id);
        newFavorite.put("favorite_name", activityName.getText().toString());
        newFavorite.put("favorite_photo", photo);

        //newTrip.put("startDate", getDateFromString(startDate.getText().toString()));

        db.collection("favorite").add(newFavorite)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(ActivityDetailsActivity.this, "Added to favorite",
                                Toast.LENGTH_SHORT).show();
                        // Intent intent = new Intent(AttractionDetailsActivity.this, FavoriteActivity.class);
                        // startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ActivityDetailsActivity.this, "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });

    }





}