package com.example.rucafe;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CoffeeActivity extends AppCompatActivity {
    private Spinner coffeeSizes;
    private Spinner coffeeAmt;
    ArrayList<String> sizes = new ArrayList<>();
    ArrayList<String> cups = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        coffeeSizes = findViewById(R.id.coffeeSizes);
        coffeeAmt = findViewById(R.id.coffeeAmt);
        sizes.add("Short");
        sizes.add("Tall");
        sizes.add("Grande");
        sizes.add("Venti");
        cups.add("1");
        cups.add("2");
        cups.add("3");
        cups.add("4");
        cups.add("5");
        ArrayAdapter<String> items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sizes);
        ArrayAdapter<String> numCups = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cups);
        coffeeSizes.setAdapter(items);
        coffeeAmt.setAdapter(numCups);
    }
}
