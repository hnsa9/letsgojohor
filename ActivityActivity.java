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

import com.example.letsgojohor.model.Activity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ActivityActivity extends AppCompatActivity {


    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView activityView;
    private OnItemClickListener listener;

    //  ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
    //ArrayAdapter<Hotel> arrayAdapter;

    private FirestoreRecyclerAdapter<Activity, ActivityActivity.DataViewHolder> adapter;
    private Query query;
    private String city = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);

        activityView = findViewById(R.id.activityView);
        city = getIntent().getExtras().get("city").toString();
        //arrayAdapter = new ArrayAdapter<Hotel>(this,android.R.layout.simple_list_item_1,hotelList);
        //hotelView.setAdapter(arrayAdapter);


        activityView.setLayoutManager(new LinearLayoutManager(this));
        final CollectionReference datasRef = db.collection("activity");
        query = datasRef.whereEqualTo("city",city);
        //.orderBy("activityName", Query.Direction.ASCENDING)

    }


    @Override
    protected void onStart(){
        super.onStart();
        FirestoreRecyclerOptions<Activity> options = new FirestoreRecyclerOptions.Builder<Activity>()
                .setQuery(query, Activity.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Activity, ActivityActivity.DataViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ActivityActivity.DataViewHolder dataViewHolder, final int i, @NonNull Activity data) {
                dataViewHolder.setData(data.getActivityName(), data.getActivityDescription());

                dataViewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String docId = getSnapshots().getSnapshot(i).getId();
                        Intent activityIntent = new Intent(ActivityActivity.this, ActivityDetailsActivity.class);
                        activityIntent.putExtra("activity_id", docId);
                        startActivity(activityIntent);
                    }
                });
            }

            @NonNull
            @Override
            public ActivityActivity.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent, false);
                return new ActivityActivity.DataViewHolder(view);
            }
        };
        activityView.setAdapter(adapter);
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

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }


}
