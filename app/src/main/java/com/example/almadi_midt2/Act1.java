package com.example.almadi_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Act1 extends AppCompatActivity {


    // we"ll make HTTP request to this URL to retrieve weather conditions
    String weatherWebserviceURL = "http://api.openweathermap.org/data/2.5/weather?q=ariana,tn&appid=XXXX&units=metric";
//    ImageView weatherBackground;
    // Textview to show temperature and description
    TextView temperature, description, humidity;


    // JSON object that contains weather information
    JSONObject jsonObj;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //link graphical items to variables
        temperature = (TextView) findViewById(R.id.temperature);
        description = (TextView) findViewById(R.id.description);
        humidity = (TextView) findViewById(R.id.humidity);

        Button bttnToAct2 = (Button)findViewById(R.id.bttnToAct2);

        bttnToAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Act1.this, Act2.class));
            }
        });





        Spinner spinner = (Spinner) findViewById(R.id.spinner);




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                url = "https://api.openweathermap.org/data/2.5/weather?q="+ spinner.getSelectedItem().toString() +"&appid=a6db16468be1247fe3c436fe88426379&units=metric";
                weather(url);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }

    public void weather(String url)
    {
        JsonObjectRequest jsonObj =
                new JsonObjectRequest(Request.Method.GET,
                        url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Meteb", "Response received");
                        Log.d("Meteb",  response.toString());
                        try {
                            JSONObject jsonMain = response.getJSONObject("main");
                            JSONObject jsonSystem = response.getJSONObject("sys");

                            double temp = jsonMain.getDouble("temp");
                            Log.d("Meteb-temp", String.valueOf(temp));
                            temperature.setText(String.valueOf(temp)+" C");


                            double hum = jsonMain.getDouble("humidity");
                            Log.d("Meteb-hum", String.valueOf(hum));
                            humidity.setText("Humidity: " + String.valueOf(hum));

                            String town = response.getString("name");
                            Log.d("Meteb-town", town);
                            description.setText(town);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Receive Error", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Meteb", "Error retrieving URL");
                    }


                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);

        Button bttnDate = (Button) findViewById(R.id.bttnDate);
        Calendar c = Calendar.getInstance();
        DateFormat fmtDate = DateFormat.getDateInstance();
        TextView dateViewer = (TextView) findViewById(R.id.DateViewer);




        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, monthOfYear);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dateViewer.setText("You picked "+
                        fmtDate.format(c.getTime()));
            }
        };

        bttnDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Act1.this, d,
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }


}