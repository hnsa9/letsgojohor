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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class AttractionDetailsActivity extends AppCompatActivity {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage storage = FirebaseStorage.getInstance("gs://letsgojohor-ccabf.appspot.com/");
    private ImageView placePhoto;
    private TextView placeAddress, placeContact, placeDescription, placeName;
    private String place_id = "";
    private FloatingActionButton map, bookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_details);

        place_id = getIntent().getExtras().get("place_id").toString();



        placePhoto = (ImageView) findViewById(R.id.img_details);
        placeAddress = (TextView) findViewById(R.id.placeAddress);
        placeContact = (TextView) findViewById(R.id.placeContact);
        placeDescription = (TextView) findViewById(R.id.placeDescription);
        placeName = (TextView) findViewById(R.id.placeName);
        map =  findViewById(R.id.map);
        bookmark =  findViewById(R.id.bookmark);






        getAttractionDetail(place_id);


    }




    private void getAttractionDetail(String place_id) {

        DocumentReference docRef = db.collection("place").document(place_id);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                        placeName.setText(document.getString("placeName"));
                        placeAddress.setText(document.getString("placeAddress"));
                        placeContact.setText(document.getString("placeContact"));
                        placeDescription.setText(document.getString("placeDescription"));
                        Picasso.get().load(document.getString("placePhoto")).into(placePhoto);

                        final String place_address = document.getString("placeAddress");
                        final String favorite_photo = document.getString("placePhoto");
                        bookmark.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addFavorite(favorite_photo);
                            }
                        });

                        map.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(AttractionDetailsActivity.this, GetLocationActivity.class);
                                intent.putExtra("address", place_address);
                                startActivity(intent);
                            }
                        });

                        //ImageView imageView = findViewById(R.id.image_view);
                        //ImgLink (document.getString("placePhoto"));
                       // String url = document.getString("placePhoto");
                       // Picasso.get().load(url).into(placePhoto);


                        //   Picasso.get().load(product.getImage()).into(productImg);

                        //https://i.ytimg.com/vi/ZAYWHoE-D0o/hqdefault.jpg


                    } else {
                        Toast.makeText(AttractionDetailsActivity.this, "This place does not exist!", Toast.LENGTH_SHORT).show();
                        //Log.d(TAG, "No such document");
                    }
                } else {
                    Toast.makeText(AttractionDetailsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                    //Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }


    private void addFavorite(String photo) {



        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Map<String, Object> newFavorite = new HashMap<>();
        newFavorite.put("favorite_id", place_id);
        newFavorite.put("user_id", user_id);
        newFavorite.put("favorite_name", placeName.getText().toString());
        newFavorite.put("favorite_photo", photo);

        //newTrip.put("startDate", getDateFromString(startDate.getText().toString()));

        db.collection("favorite").add(newFavorite)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AttractionDetailsActivity.this, "Added to favorite",
                                Toast.LENGTH_SHORT).show();
                        // Intent intent = new Intent(AttractionDetailsActivity.this, FavoriteActivity.class);
                        // startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AttractionDetailsActivity.this, "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });

    }

    /*
    private String ImgLink (String img){
        // Points to the root reference
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference imgRef = storageRef.child("/" + img);
        return imgRef.toString();
    }
*/










}