package com.example.rucafe;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private ListView orders;
    private EditText orderSubtotal;
    private EditText orderTax;
    private EditText orderTotal;

    public static ArrayList<MenuItem> cart = new ArrayList<>();
    private static final double NJ_TAX = 0.06625;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        orders = findViewById(R.id.orders);
        orderSubtotal = findViewById(R.id.orderSubtotal);
        orderTax = findViewById(R.id.orderTax);
        orderTotal = findViewById(R.id.orderTotal);
        ArrayAdapter<MenuItem> allOrders = new ArrayAdapter<MenuItem>(this, android.R.layout.simple_list_item_1, cart);
        orders.setAdapter(allOrders);
        calculatePrices();
    }

    public void calculatePrices() {
        double price = 0;
        if(cart.size() > 0) {
            for(MenuItem item: cart) {
                price += item.itemPrice();
            }
            orderSubtotal.setText("$" + new DecimalFormat("0.00").format(price));
            double taxedPrice = price * NJ_TAX;
            orderTax.setText("$" + new DecimalFormat("0.00").format(taxedPrice));
            double totalPrice = price + taxedPrice;
            orderTotal.setText("$" + new DecimalFormat("0.00").format(totalPrice));
        }
    }
}
