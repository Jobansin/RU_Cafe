package com.example.rucafe;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * The main activity of the application that serves as the entry point and provides navigation to different views.
 * @author David Harianto
 * @author Joban Singh
 * Pixel 2 API 28
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Called when the activity is created. Initializes the layout.
     *
     * @param savedInstanceState The saved instance state bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Opens the CoffeeActivity to view coffee options.
     *
     * @param view The view that was clicked.
     */
    public void getCoffeeView(View view) {
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }

    /**
     * Opens the CartActivity to view the current cart.
     *
     * @param view The view that was clicked.
     */
    public void getCartView(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    /**
     * Opens the DonutActivity to view donut options.
     *
     * @param view The view that was clicked.
     */
    public void getDonutView(View view) {
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);
    }

    /**
     * Opens the OrdersActivity to view previous orders.
     *
     * @param view The view that was clicked.
     */
    public void getOrderView(View view) {
        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);
    }
}