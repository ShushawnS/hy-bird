package com.example.hy_bird;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

import android.graphics.Color;
import org.json.*;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class WeatherApi extends AppCompatActivity{
    EditText etCity, etCountry;
    TextView tvResult;
    private final String url = "https://api.openweathermap.org/data/2.5/forecast";
    private final String appid = "9f2392a8f6cc3d364982588723654b2a";
    DecimalFormat df = new DecimalFormat("#.##");

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_api);
        etCity = findViewById(R.id.etCity);
        etCountry = findViewById(R.id.etCountry);
        tvResult = findViewById(R.id.tvResult);
    }

    public void getWeatherDetails(View view){

        String tempUrl = "";
        String city = etCity.getText().toString().trim();
        String country = etCountry.getText().toString().trim();
        if(city.equals("")){
            tvResult.setText("City field can not be empty!");
        }else{
            if(!country.equals("")){
                tempUrl = url + "?q=" + city + "," + country + "&appid=" + appid +"&units=metric";
            }else{
                tempUrl = url + "?q=" + city + "&appid=" + appid+"&units=metric";
            }
            StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    String output = "";
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray jsonList = jsonResponse.getJSONArray("list");

                        //FIRST DAY
                        JSONObject day1 = jsonList.getJSONObject(4);
                        String day1date = day1.getString("dt_txt");
                        JSONObject day1main = day1.getJSONObject("main");
                        String day1temp = day1main.getString("temp");
                        String day1feelslike = day1main.getString("feels_like");
                        JSONArray day1weather = day1.getJSONArray("weather");
                        JSONObject day1condition = day1weather.getJSONObject(0);
                        String day1description = day1condition.getString("description");
                        String Day1 = "On "+day1date+" is: \nTemperature: "+day1temp+" °C \nFeels Like: "+day1feelslike+" °C \nCondition: "+day1description;

                        //SECOND DAY
                        JSONObject day2 = jsonList.getJSONObject(12);
                        String day2date = day2.getString("dt_txt");
                        JSONObject day2main = day2.getJSONObject("main");
                        String day2temp = day2main.getString("temp");
                        String day2feelslike = day2main.getString("feels_like");
                        JSONArray day2weather = day2.getJSONArray("weather");
                        JSONObject day2condition = day2weather.getJSONObject(0);
                        String day2description = day2condition.getString("description");
                        String Day2 = "On "+day2date+" is: \nTemperature: "+day2temp+" °C \nFeels Like: "+day2feelslike+" °C \nCondition: "+day2description;

                        //THIRD DAY
                        JSONObject day3 = jsonList.getJSONObject(20);
                        String day3date = day3.getString("dt_txt");
                        JSONObject day3main = day3.getJSONObject("main");
                        String day3temp = day3main.getString("temp");
                        String day3feelslike = day3main.getString("feels_like");
                        JSONArray day3weather = day3.getJSONArray("weather");
                        JSONObject day3condition = day3weather.getJSONObject(0);
                        String day3description = day3condition.getString("description");
                        String Day3 = "The weather in " + city +" on "+day3date+" is: \nTemperature: "+day3temp+" °C \nFeels Like: "+day3feelslike+" °C \nCondition: "+day3description;

                        //FOURTH DAY
                        JSONObject day4 = jsonList.getJSONObject(28);
                        String day4date = day4.getString("dt_txt");
                        JSONObject day4main = day4.getJSONObject("main");
                        String day4temp = day4main.getString("temp");
                        String day4feelslike = day4main.getString("feels_like");
                        JSONArray day4weather = day4.getJSONArray("weather");
                        JSONObject day4condition = day4weather.getJSONObject(0);
                        String day4description = day4condition.getString("description");
                        String Day4 = "On "+day4date+" is: \nTemperature: "+day4temp+" °C \nFeels Like: "+day4feelslike+" °C \nCondition: "+day4description;

                        //FIFTH DAY
                        JSONObject day5 = jsonList.getJSONObject(36);
                        String day5date = day5.getString("dt_txt");
                        JSONObject day5main = day5.getJSONObject("main");
                        String day5temp = day5main.getString("temp");
                        String day5feelslike = day5main.getString("feels_like");
                        JSONArray day5weather = day5.getJSONArray("weather");
                        JSONObject day5condition = day5weather.getJSONObject(0);
                        String day5description = day5condition.getString("description");
                        String Day5 = "On "+day5date+" is: \nTemperature: "+day5temp+" °C \nFeels Like: "+day5feelslike+" °C \nCondition: "+day5description;
                        output = "The 5 day forecast in "+city+":\n"+Day1 +"\n"+Day2+"\n"+Day3+"\n"+Day4+"\n"+Day5;
                        tvResult.setText(output);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }

    }

