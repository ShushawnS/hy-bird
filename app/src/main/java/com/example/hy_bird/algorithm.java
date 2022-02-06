package com.example.hy_bird;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class algorithm extends AppCompatActivity {

    TextView algoTrial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm2);
        algoTrial = findViewById(R.id.algoTrial);

        readData();
        performAlgo();
    }

    private List<DataSample> dataSampleList = new ArrayList<>();

    private void readData(){
        InputStream is = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader( new BufferedReader( new InputStreamReader(is, Charset.forName("UTF-8"))) );

        boolean current = true;

         String line = "";

        while (current) {
            try {
                if ( ((line = reader.readLine()) != null)) {
                    //split by ","
                    String[] tokens = line.split(",");

                    //read the data
                    DataSample sample = new DataSample();

                    sample.setName(tokens[0]);
                    sample.setMonday(tokens[1]);
                    sample.setTuesday(tokens[2]);
                    sample.setWednesday(tokens[3]);
                    sample.setThursday(tokens[4]);
                    sample.setFriday(tokens[5]);

                    sample.setCapacity(Integer.parseInt(tokens[6]));

                    sample.setbMeeting(tokens[7]);
                    sample.settMeeting(tokens[8]);
                    sample.setTransportation(tokens[9]);
                    sample.setPara(tokens[10]);

                    dataSampleList.add(sample);

                    current = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void performAlgo() {
        int monday = 0, tuesday = 0, wednesday = 0, thursday = 0, friday = 0;

        DataSample data = dataSampleList.get(0);

        String trial = "trial";

        //q1
        if ( data.getMonday().equals("Y") ) {
            monday +=  50;
        }

        if ( data.getTuesday().equals("Y") ) {
            tuesday +=  50;
        }

        if ( data.getWednesday().equals("Y") ) {
            wednesday +=  50;
        }

        if ( data.getThursday().equals("Y") ) {
            thursday +=  50;
        }

        if ( data.getFriday().equals("Y") ) {
            friday +=  50;
        }

        //q2

        if ( data.getbMeeting().contains("M") ) {
            monday +=  30;
        }
        if ( data.getbMeeting().contains("T") ) {
            tuesday +=  30;
        }
        if ( data.getbMeeting().contains("W") ) {
            wednesday +=  30;
        }
        if ( data.getbMeeting().contains("Th") ) {
            thursday +=  30;
        }
        if ( data.getbMeeting().contains("F") ) {
            friday +=  30;
        }

        //q3
        if ( data.gettMeeting().equals("ip") ) {
            if ( data.getMonday().equals("Y") ) {
                monday +=  20;
            }

            if ( data.getTuesday().equals("Y") ) {
                tuesday +=  20;
            }

            if ( data.getWednesday().equals("Y") ) {
                wednesday +=  20;
            }

            if ( data.getThursday().equals("Y") ) {
                thursday +=  20;
            }

            if ( data.getFriday().equals("Y") ) {
                friday +=  20;
            }
        }

        if ( data.gettMeeting().equals("on") ) {
            if ( data.getMonday().equals("N") ) {
                monday +=  20;
            }

            if ( data.getTuesday().equals("N") ) {
                tuesday +=  20;
            }

            if ( data.getWednesday().equals("N") ) {
                wednesday +=  20;
            }

            if ( data.getThursday().equals("N") ) {
                thursday +=  20;
            }

            if ( data.getFriday().equals("N") ) {
                friday +=  20;
            }
        }


        //returns date with score
        algoTrial.setText(
                "Monday: " + Integer.toString(monday) +
                "\nTuesday: " + Integer.toString(tuesday) +
                "\nWednesday: " + Integer.toString(wednesday) +
                "\nThursday: " + Integer.toString(thursday) +
                "\nFriday: " + Integer.toString(friday)
        );

    }



}