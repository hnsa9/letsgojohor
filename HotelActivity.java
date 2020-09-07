package com.example.letsgojohor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.letsgojohor.model.Hotel;
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


public class HotelActivity extends AppCompatActivity {


    //private static final String TAG = "HotelActivity";


    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView hotelView;

  //  ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
    //ArrayAdapter<Hotel> arrayAdapter;

    private FirestoreRecyclerAdapter<Hotel, DataViewHolder> adapter;
    private Query query;

    private OnItemClickListener listener;

    private String city = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        hotelView = findViewById(R.id.hotelView);
        //arrayAdapter = new ArrayAdapter<Hotel>(this,android.R.layout.simple_list_item_1,hotelList);
        //hotelView.setAdapter(arrayAdapter);

        city = getIntent().getExtras().get("city").toString();
        hotelView.setLayoutManager(new LinearLayoutManager(this));
        final CollectionReference datasRef = db.collection("hotels"); //get all data from collection hotels
        query = datasRef.whereEqualTo("city",city); //order by hotelname in ascending
       // .orderBy("hotelName", Query.Direction.ASCENDING);

        //Query query = db.collection("name") - get all documents in the collection

    }


    @Override
    protected void onStart(){
        super.onStart();
        // FirestoreRecyclerOptions<Class> variable = new FirestoreRecyclerOptions.Builder<Class>()
        // .setQuery(query, Classname.class)
        FirestoreRecyclerOptions<Hotel> options = new FirestoreRecyclerOptions.Builder<Hotel>()
                .setQuery(query, Hotel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Hotel, DataViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DataViewHolder dataViewHolder, final int position, @NonNull Hotel data) {
                dataViewHolder.setData(data.getHotelName(), data.getHotelAddress());


                dataViewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String docId = getSnapshots().getSnapshot(position).getId();
                        Intent hotelIntent = new Intent(HotelActivity.this, HotelDetailsActivity.class);
                        hotelIntent.putExtra("hotel_id", docId);
                        startActivity(hotelIntent);
                    }
                });



            }

            @NonNull
            @Override
            public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent, false);
                return new DataViewHolder(view);
            }
        };
        hotelView.setAdapter(adapter);
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

        DataViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        void setData(String dataTitle, String dataBody) {
            TextView title = view.findViewById(R.id.title);
            TextView content = view.findViewById(R.id.content);
            title.setText(dataTitle);
            content.setText(dataBody);
        }


        //////////////////////////
    }


    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

}












