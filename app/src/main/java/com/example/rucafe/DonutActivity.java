package com.example.rucafe;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonutActivity extends AppCompatActivity {

    private ArrayList<DonutCharacteristics> donuts = new ArrayList<>();
    private RecyclerView donut_recycler_view;
    private Spinner donutQuantity;
    private int [] itemImages = {R.drawable.glazed, R.drawable.jelly, R.drawable.strawberry_frosted, R.drawable.sugar, R.drawable.chocolate_frosted,
            R.drawable.lemon_filled, R.drawable.original, R.drawable.blueberry_cake, R.drawable.cinnamon_sugar, R.drawable.chocolate, R.drawable.powdered, R.drawable.butternut};
    private String [] donutNames = {"Glazed", "Jelly", "Strawberry Frosted", "Sugar", "Chocolate Frosted", "Lemon-filled",
            "Original Cake", "Blueberry Cake", "Cinnamon Sugar Cake", "Chocolate", "Powdered", "Butternut"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        donut_recycler_view = findViewById(R.id.donut_recycler_view);
        setupMenuItems();
        DonutAdapter adapter = new DonutAdapter(this, donuts);
        donut_recycler_view.setAdapter(adapter);
        donut_recycler_view.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupMenuItems() {
        for(int i = 0; i < donutNames.length; i++) {
            if(i < 6) {
                donuts.add(new DonutCharacteristics(donutNames[i], itemImages[i], "$1.59"));
            }
            else if(i < 9) {
                donuts.add(new DonutCharacteristics(donutNames[i], itemImages[i], "$1.79"));
            }
            else {
                donuts.add(new DonutCharacteristics(donutNames[i], itemImages[i], "$0.39"));
            }
        }
    }
}
