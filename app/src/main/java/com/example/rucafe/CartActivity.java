package com.example.rucafe;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private ListView orders;
    ArrayList<String> listOrders = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        orders = findViewById(R.id.orders);
        ArrayAdapter<String> allOrders = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOrders);
        orders.setAdapter(allOrders);
    }
}
