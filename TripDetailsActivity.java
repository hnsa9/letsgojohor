package com.example.letsgojohor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TripDetailsActivity extends AppCompatActivity {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView tripName, tripDescription, tripDestination, startDate, endDate;
   // private String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String trip_id = "";
    private String TAG = "TripDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        trip_id = getIntent().getExtras().get("trip_id").toString();

        // hotelImg = (ImageView) findViewById(R.id.image_details);
        tripName = (TextView) findViewById(R.id.tripName);
        tripDescription = (TextView) findViewById(R.id.tripDescription);

        tripDestination =  (TextView) findViewById(R.id.destination);
        startDate = (TextView) findViewById(R.id.startDate);
        endDate = (TextView) findViewById(R.id.endDate);

        getTripDetail(trip_id);

    }

    private void getTripDetail(String trip_id) {

        DocumentReference docRef = db.collection("trip").document(trip_id);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        tripName.setText(document.getString("tripName"));
                        tripDescription.setText(document.getString("tripDescription"));
                        tripDestination.setText(document.getString("tripDestination"));
                        startDate.setText(document.getString("startDate"));
                        endDate.setText(document.getString("endDate"));


                    } else {
                        Toast.makeText(TripDetailsActivity.this, "Trip does not exist!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Toast.makeText(TripDetailsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }

}