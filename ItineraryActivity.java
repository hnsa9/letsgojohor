package com.example.letsgojohor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letsgojohor.model.Trip;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ItineraryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth.AuthStateListener mAuthListener;
    //private ImageView placePhoto;
    private EditText tripName, tripDescription, startDate, endDate;
    private Button create;
    private Spinner city_spinner;
    private DatePickerDialog picker;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        tripName =  findViewById(R.id. tripName);
        tripDescription = findViewById(R.id.tripDescription);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);
        create =  findViewById(R.id.create);
        city_spinner = findViewById(R.id.destination);


        startDate.setInputType(InputType.TYPE_NULL);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ItineraryActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                startDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        endDate.setInputType(InputType.TYPE_NULL);
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ItineraryActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                endDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });





        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city_spinner.setAdapter(adapter);

        city_spinner.setOnItemSelectedListener(this);



        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tripName.getText().toString().isEmpty() ||tripDescription.getText().toString().isEmpty() || startDate.getText().toString().isEmpty()
                    || endDate.getText().toString().isEmpty()
                ) {
                    Toast.makeText(ItineraryActivity.this, "Please fill all the required field!", Toast.LENGTH_SHORT).show();

                }
                else {

                    try {
                        addNewTrip();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(ItineraryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }



                }
            }
        });



    }





    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        // trip.setTripDestination(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    static final SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
    public Date getDateFromString(String datetoSaved){

        try {
            Date date = format.parse(datetoSaved);
            return date ;
        } catch (ParseException e){
            return null ;
        }

    }


    private void addNewTrip() throws IOException {


        final String destination = city_spinner.getSelectedItem().toString();
        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Map<String, Object> newTrip = new HashMap<>();
        newTrip.put("tripName", tripName.getText().toString());
        newTrip.put("tripDescription", tripDescription.getText().toString());
        newTrip.put("tripDestination", destination);
        newTrip.put("startDate", startDate.getText().toString());
        newTrip.put("endDate", endDate.getText().toString());
        newTrip.put("user_id", user_id);

        //newTrip.put("startDate", getDateFromString(startDate.getText().toString()));

        db.collection("trip").add(newTrip)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(ItineraryActivity.this, "Trip successfully created!",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ItineraryActivity.this, TripPageActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ItineraryActivity.this, "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });

    }

}
