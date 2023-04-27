package com.example.rucafe;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Represents the activity for managing orders.
 * @author David Harianto
 * @author Joban Singh
 */
public class OrdersActivity extends AppCompatActivity {

    private ListView orderList;
    
    public static ArrayList<Order> orders = new ArrayList<>();

    /**
     * Called when the activity is created. Initializes the layout and sets up the order list.
     *
     * @param savedInstanceState The saved instance state bundle.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        orderList = findViewById(R.id.orderList);
        ArrayAdapter<Order> allOrders = new ArrayAdapter<Order>(this, android.R.layout.simple_list_item_1, orders);
        orderList.setAdapter(allOrders);
        deleteItem(allOrders);
    }

    /**
     * Deletes an order from the list.
     *
     * @param adapter The adapter for the order list.
     */
    public void deleteItem(ArrayAdapter<Order> adapter) {
        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * Handles the item click event in the order list.
             *
             * @param parent The AdapterView where the click happened.
             * @param view The view within the AdapterView that was clicked.
             * @param position The position of the view in the adapter.
             * @param id The row id of the item that was clicked.
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(OrdersActivity.this);
                alert.setTitle("Delete selected order.");
                alert.setMessage("Delete order?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    /**
                     * Handles the click event when a dialog button is clicked.
                     *
                     * @param dialog The dialog interface.
                     * @param id The ID of the clicked button.
                     */
                    public void onClick(DialogInterface dialog, int id) {
                        orders.remove(position);
                        Toast.makeText(getApplicationContext(), "Order has been deleted.", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {

                    /**
                     * Handles the click event when the negative button of the dialog is clicked.
                     *
                     * @param dialog The dialog interface.
                     * @param which The button that was clicked.
                     */
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

}
