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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.letsgojohor.model.Event;
import com.example.letsgojohor.model.Favorite;
import com.example.letsgojohor.model.Place;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

public class FavoriteActivity extends AppCompatActivity {
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView favorite;


    private FirestoreRecyclerAdapter<Favorite, FavoriteActivity.DataViewHolder> adapter;
    private Query query;
    private OnItemClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        favorite = findViewById(R.id.favorite);
        //arrayAdapter = new ArrayAdapter<Hotel>(this,android.R.layout.simple_list_item_1,hotelList);
        //hotelView.setAdapter(arrayAdapter);
        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();


        favorite.setLayoutManager(new LinearLayoutManager(this));
       // final CollectionReference datasRef = db.collection("favorite")

       // query = datasRef.orderBy("favorite_id", Query.Direction.ASCENDING);

        final CollectionReference datasRef = db.collection("favorite");
        query = datasRef.whereEqualTo("user_id",user_id);



    }

    @Override
    protected void onStart(){
        super.onStart();
        FirestoreRecyclerOptions<Favorite> options = new FirestoreRecyclerOptions.Builder<Favorite>()
                .setQuery(query, Favorite.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Favorite, FavoriteActivity.DataViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FavoriteActivity.DataViewHolder dataViewHolder, final int position, @NonNull Favorite data) {
                dataViewHolder.setData(data.getFavorite_name(), data.getFavorite_photo());

            }

            @NonNull
            @Override
            public FavoriteActivity.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_photo, parent, false);
                return new FavoriteActivity.DataViewHolder(view);
            }
        };
        favorite.setAdapter(adapter);
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
            ImageView content = view.findViewById(R.id.content);
            title.setText(dataTitle);
            Picasso.get().load(dataBody).into(content);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
}