package com.example.letsgojohor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.letsgojohor.model.Event;
import com.example.letsgojohor.model.Tip;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class TravelTipsDetailsActivity extends AppCompatActivity {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView tipView;

    private FirestoreRecyclerAdapter<Tip, TravelTipsDetailsActivity.DataViewHolder> adapter;
    private Query query;
    private String category = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_tips_details);

        tipView = findViewById(R.id.tipView);
        //arrayAdapter = new ArrayAdapter<Hotel>(this,android.R.layout.simple_list_item_1,hotelList);
        //hotelView.setAdapter(arrayAdapter);
       category = getIntent().getExtras().get("category").toString();


        tipView.setLayoutManager(new LinearLayoutManager(this));
        final CollectionReference datasRef = db.collection("TravelTips");
        query = datasRef.whereEqualTo("category",category);
    }


    @Override
    protected void onStart(){
        super.onStart();
        FirestoreRecyclerOptions<Tip> options = new FirestoreRecyclerOptions.Builder<Tip>()
                .setQuery(query, Tip.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Tip, TravelTipsDetailsActivity.DataViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull TravelTipsDetailsActivity.DataViewHolder dataViewHolder, final int position, @NonNull Tip data) {
                dataViewHolder.setData(data.getTipName(), data.getTipDescription());


            }

            @NonNull
            @Override
            public TravelTipsDetailsActivity.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent, false);
                return new TravelTipsDetailsActivity.DataViewHolder(view);
            }
        };
        tipView.setAdapter(adapter);
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
    }





}