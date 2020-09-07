package com.example.letsgojohor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.letsgojohor.model.Hotel;
import com.example.letsgojohor.model.Trip;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class TripPageActivity extends AppCompatActivity {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirestoreRecyclerAdapter<Trip, TripViewHolder> adapter;
    private RecyclerView tripView;
    private FloatingActionButton addTrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_page);

        tripView = findViewById(R.id.tripview);
        tripView.setLayoutManager(new LinearLayoutManager(this));

        addTrip = findViewById(R.id.addtrip);

        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();


        addTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TripPageActivity.this, ItineraryActivity.class);
                startActivity(intent);
            }
        });



        Query query =  db.collection("trip")
                    .whereEqualTo("user_id",user_id)
                    .orderBy("tripName", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<Trip> options = new FirestoreRecyclerOptions.Builder<Trip>()
                .setQuery(query, Trip.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Trip, TripPageActivity.TripViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull TripPageActivity.TripViewHolder dataViewHolder, final int position, @NonNull Trip data) {
                dataViewHolder.setTripName(data.getTripName());


                dataViewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String docId = getSnapshots().getSnapshot(position).getId();
                        Intent tripIntent = new Intent(TripPageActivity.this, TripDetailsActivity.class);
                        tripIntent.putExtra("trip_id", docId);
                        startActivity(tripIntent);
                    }
                });



            }



            @NonNull
            @Override
            public TripPageActivity.TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item1, parent, false);
                return new TripPageActivity.TripViewHolder(view);
            }
        };

        tripView.setAdapter(adapter);
        adapter.startListening();


    }


    private class TripViewHolder extends RecyclerView.ViewHolder {
        private View view;

        TripViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        void setTripName(String tripName) {
            TextView textView = view.findViewById(R.id.title);
            textView.setText(tripName);
        }
    }

}