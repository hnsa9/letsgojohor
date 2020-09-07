package com.example.letsgojohor;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CurrencyActivity extends AppCompatActivity {

    public static BreakIterator data;
    List<String> keysList;
    Spinner toCurrency;
    TextView value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        toCurrency = (Spinner)findViewById(R.id.country_spinner);
        final EditText amount = (EditText)findViewById(R.id.amount);
        final Button btnConvert = (Button)findViewById(R.id.convert);
        value =(TextView) findViewById(R.id.value);
        try {
            loadConvTypes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!amount.getText().toString().isEmpty())
                {
                    String toCurr = toCurrency.getSelectedItem().toString();
                    double rmValue = Double.valueOf(amount.getText().toString());

                    Toast.makeText(CurrencyActivity.this, "Please Wait..", Toast.LENGTH_SHORT).show();
                    try {
                        convertCurrency(toCurr, rmValue);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(CurrencyActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(CurrencyActivity.this, "Please enter a value!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    public void loadConvTypes() throws IOException {

        String url = "https://api.exchangeratesapi.io/latest?base=MYR";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .build();



        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
                Toast.makeText(CurrencyActivity.this, mMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String mMessage = response.body().string();


                CurrencyActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject obj = new JSONObject(mMessage);
                            JSONObject  b = obj.getJSONObject("rates");

                            Iterator keysToCopyIterator = b.keys();
                            keysList = new ArrayList<String>();

                            while(keysToCopyIterator.hasNext()) {

                                String key = (String) keysToCopyIterator.next();

                                keysList.add(key);

                            }


                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, keysList );
                            toCurrency.setAdapter(spinnerArrayAdapter);





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }




        });
    }


    public void convertCurrency(final String toCurr, final double rmValue) throws IOException {

        String url = "https://api.exchangeratesapi.io/latest?base=MYR";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .build();



        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
                Toast.makeText(CurrencyActivity.this, mMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String mMessage = response.body().string();
                CurrencyActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(MainActivity.this, mMessage, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject obj = new JSONObject(mMessage);
                            JSONObject  b = obj.getJSONObject("rates");

                            String val = b.getString(toCurr);

                            double output = rmValue*Double.valueOf(val);


                            value.setText(String.valueOf(output));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }





        });
    }





}






