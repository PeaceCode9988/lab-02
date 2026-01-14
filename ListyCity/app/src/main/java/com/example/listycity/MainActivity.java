package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addcitybutton;
    Button deletecitybutton;
    Button confirmaddcitybutton;
    ListView citylistclicked;
    LinearLayout thebuttombuttonset;
    boolean buttomsetvisibility;
    boolean isitemptyornot;
    EditText inputvalue;
    String citynameinput;
    boolean listclicked;
    int listclickedwhichone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String[]cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);



        addcitybutton = findViewById(R.id.add);
        deletecitybutton = findViewById(R.id.delete);
        confirmaddcitybutton = findViewById(R.id.confirmAdd);
        inputvalue = findViewById(R.id.input);

        thebuttombuttonset = findViewById(R.id.buttonaddconfirmation);

        buttomsetvisibility = false;
        listclicked = false;
        listclickedwhichone = -1;



        addcitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                thebuttombuttonset.setVisibility(view.VISIBLE);
                buttomsetvisibility = true;
            }});

        confirmaddcitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                citynameinput = inputvalue.getText().toString();
                isitemptyornot = citynameinput.isEmpty();
                if(buttomsetvisibility && !isitemptyornot){
                    dataList.add(citynameinput);
                    cityAdapter.notifyDataSetChanged();
                    listclickedwhichone = -1;
                    thebuttombuttonset.setVisibility(View.INVISIBLE);
                    buttomsetvisibility = false;
                    inputvalue.setText("");

                    CharSequence text = "Successfully added!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(MainActivity.this, text, duration);
                    toast.show();
                }
                else{
                    CharSequence text = "Empty Input detected, input again!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(MainActivity.this, text, duration);
                    toast.show();
                    thebuttombuttonset.setVisibility(View.VISIBLE);
                    buttomsetvisibility = true;
                }
            }});


        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listclicked = true;
                listclickedwhichone = position;
                CharSequence text = "Successfully selected " + position;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(MainActivity.this, text, duration);
                toast.show();
            }
        });

        deletecitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(listclicked && (listclickedwhichone!=-1)){
                    dataList.remove(listclickedwhichone);
                    cityList.setAdapter(cityAdapter);
                    cityAdapter.notifyDataSetChanged();

                    listclicked = false;
                    listclickedwhichone = -1;
                    CharSequence text = "Successfully deleted";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(MainActivity.this, text, duration);
                    toast.show();
                }
                else{
                    CharSequence text = "Select a city first before deleting.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(MainActivity.this, text, duration);
                    toast.show();
                }

            }});




    }
}