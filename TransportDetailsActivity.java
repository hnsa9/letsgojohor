package com.example.letsgojohor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class TransportDetailsActivity extends AppCompatActivity {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ImageView photo;
    private TextView name, description, type;
    private String transport_id = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_details);

        transport_id = getIntent().getExtras().get("transport_id").toString();


        photo = (ImageView) findViewById(R.id.image_details);
        name = (TextView) findViewById(R.id.destination);
        description = (TextView) findViewById(R.id.startDate);
        type = (TextView) findViewById(R.id.tripName);


        getTransportDetail(transport_id);
    }

    private void getTransportDetail(String transport_id) {

        DocumentReference docRef = db.collection("transport").document(transport_id);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //Log.d(TAG, "DocumentSnapshot data: " + document.getData());


                        name.setText(document.getString("name"));
                        description.setText(document.getString("description"));
                        type.setText(document.getString("type"));
                        Picasso.get().load(document.getString("photo")).into(photo);

                        //ImageView imageView = findViewById(R.id.image_view);
                        //"gs://letsgojohor-ccabf.appspot.com/featured_legoland.jpg"
                        //String url = document.getString("photo") ;
                        //Picasso.get().load(url).into(photo);
                        /*
                         insert image from db to imageview


                         */


                    } else {
                        Toast.makeText(TransportDetailsActivity.this, "Transport does not exist!", Toast.LENGTH_SHORT).show();
                        //Log.d(TAG, "No such document");
                    }
                } else {
                    Toast.makeText(TransportDetailsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                    //Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }





}