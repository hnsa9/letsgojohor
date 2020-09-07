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

import com.example.letsgojohor.model.Transport;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class TransportActivity extends AppCompatActivity {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView transportView;

    private OnItemClickListener listener;

    //  ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
    //ArrayAdapter<Hotel> arrayAdapter;

    private FirestoreRecyclerAdapter<Transport, TransportActivity.DataViewHolder> adapter;
    private Query query;

    private String city = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        transportView = findViewById(R.id.transportView);
        //arrayAdapter = new ArrayAdapter<Hotel>(this,android.R.layout.simple_list_item_1,hotelList);
        //hotelView.setAdapter(arrayAdapter);
        city = getIntent().getExtras().get("city").toString();


        transportView.setLayoutManager(new LinearLayoutManager(this));
        final CollectionReference datasRef = db.collection("transport");
        query = datasRef.whereEqualTo("city",city);
        //.orderBy("name", Query.Direction.ASCENDING)

    }

    @Override
    protected void onStart(){
        super.onStart();
        FirestoreRecyclerOptions<Transport> options = new FirestoreRecyclerOptions.Builder<Transport>()
                .setQuery(query, Transport.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Transport, TransportActivity.DataViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull TransportActivity.DataViewHolder dataViewHolder, final int i, @NonNull Transport data) {
                dataViewHolder.setData(data.getName(), data.getDescription());

                dataViewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String docId = getSnapshots().getSnapshot(i).getId();
                        Intent transportIntent = new Intent(TransportActivity.this, TransportDetailsActivity.class);
                        transportIntent.putExtra("transport_id", docId);
                        startActivity(transportIntent);
                    }
                });
            }

            @NonNull
            @Override
            public TransportActivity.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent, false);
                return new TransportActivity.DataViewHolder(view);
            }
        };
        transportView.setAdapter(adapter);
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
