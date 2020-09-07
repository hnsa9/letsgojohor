package com.example.letsgojohor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letsgojohor.model.Place;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import androidx.recyclerview.widget.LinearLayoutManager;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;


public class AttractionActivity extends AppCompatActivity {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView placeView;

    //  ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
    //ArrayAdapter<Hotel> arrayAdapter;


    private FirestoreRecyclerAdapter<Place, AttractionActivity.DataViewHolder> adapter;
    private Query query;
    private OnItemClickListener listener;
    private String city = "";
    //private OnItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction);

        placeView = findViewById(R.id.favorite);
        //arrayAdapter = new ArrayAdapter<Hotel>(this,android.R.layout.simple_list_item_1,hotelList);
        //hotelView.setAdapter(arrayAdapter);

        city = getIntent().getExtras().get("city").toString();



        placeView.setLayoutManager(new LinearLayoutManager(this));
        final CollectionReference datasRef = db.collection("place");
        query = datasRef.whereEqualTo("city",city);

        //.orderBy("placeName", Query.Direction.ASCENDING)

    }

    @Override
    protected void onStart(){
        super.onStart();
        FirestoreRecyclerOptions<Place> options = new FirestoreRecyclerOptions.Builder<Place>()
                .setQuery(query, Place.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Place, AttractionActivity.DataViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AttractionActivity.DataViewHolder dataViewHolder, final int position, @NonNull Place data) {
                dataViewHolder.setData(data.getPlaceName(), data.getPlaceDescription());

                dataViewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String docId = getSnapshots().getSnapshot(position).getId();
                        Intent placeIntent = new Intent(AttractionActivity.this, AttractionDetailsActivity.class);
                        placeIntent.putExtra("place_id", docId);
                        startActivity(placeIntent);
                    }
                });



            }

            @NonNull
            @Override
            public AttractionActivity.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent, false);
                return new AttractionActivity.DataViewHolder(view);
            }
        };
        placeView.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        if(adapter != null){
            adapter.stopListening();
        }
    }


    private class DataViewHolder extends RecyclerView.ViewHolder {
        private View view;

        //public RecyclerViewItemClick listener;

        DataViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            /*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });

             */
        }

        void setData(String dataTitle, String dataBody) {
            TextView title = view.findViewById(R.id.title);
            TextView content = view.findViewById(R.id.content);
            title.setText(dataTitle);
            content.setText(dataBody);
        }



    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }


}
