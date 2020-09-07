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
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class EventActivity extends AppCompatActivity {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView eventView;

    private FirestoreRecyclerAdapter<Event, EventActivity.DataViewHolder> adapter;
    private Query query;

    private OnItemClickListener listener;
    private String city = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        eventView = findViewById(R.id.eventView);
        //arrayAdapter = new ArrayAdapter<Hotel>(this,android.R.layout.simple_list_item_1,hotelList);
        //hotelView.setAdapter(arrayAdapter);

        city = getIntent().getExtras().get("city").toString();

        eventView.setLayoutManager(new LinearLayoutManager(this));
        final CollectionReference datasRef = db.collection("event");
        query = datasRef.whereEqualTo("city",city);
        //.orderBy("eventName", Query.Direction.ASCENDING)
    }


    @Override
    protected void onStart(){
        super.onStart();
        FirestoreRecyclerOptions<Event> options = new FirestoreRecyclerOptions.Builder<Event>()
                .setQuery(query, Event.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Event, EventActivity.DataViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull EventActivity.DataViewHolder dataViewHolder, final int position, @NonNull Event data) {
                dataViewHolder.setData(data.getEventName(), data.getEventDescription());

                dataViewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String docId = getSnapshots().getSnapshot(position).getId();
                        Intent eventIntent = new Intent(EventActivity.this, EventDetailsActivity.class);
                        eventIntent.putExtra("event_id", docId);
                        startActivity(eventIntent);
                    }
                });

            }

            @NonNull
            @Override
            public EventActivity.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent, false);
                return new EventActivity.DataViewHolder(view);
            }
        };
        eventView.setAdapter(adapter);
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
