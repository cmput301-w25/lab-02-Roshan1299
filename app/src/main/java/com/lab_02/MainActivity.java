package com.lab_02;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Button;

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
    Button button1 ;
    Button button2 ;
    LinearLayout inputLayout;
    EditText cityInput;
    Button confirmButton;
    String selectedCity = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.cityList);
        button1 = findViewById(R.id.addCity);
        button2 = findViewById(R.id.deleteCity);
        inputLayout = findViewById(R.id.inputLayout);
        cityInput = findViewById(R.id.cityInput);
        confirmButton = findViewById(R.id.confirmButton);

        String []cities = {"Edmonton","Vancouver","Moscow","Montreal"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this,R.layout.content,dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = dataList.get(position);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputLayout.setVisibility(View.VISIBLE);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = cityInput.getText().toString().trim();
                if (!cityName.isEmpty()) {

                    dataList.add(cityName);
                    cityAdapter.notifyDataSetChanged(); // Refresh the list

                    cityInput.setText("");
                    inputLayout.setVisibility(View.GONE);
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataList.remove(selectedCity);
                cityAdapter.notifyDataSetChanged();
//              selectedCity = null; // Reset the selected city
            }
        });
    }
}
