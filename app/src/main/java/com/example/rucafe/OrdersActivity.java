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

public class OrdersActivity extends AppCompatActivity {

    private ListView orderList;
    
    public static ArrayList<Order> orders = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        orderList = findViewById(R.id.orderList);
        ArrayAdapter<Order> allOrders = new ArrayAdapter<Order>(this, android.R.layout.simple_list_item_1, orders);
        orderList.setAdapter(allOrders);
        deleteItem(allOrders);
    }

    public void deleteItem(ArrayAdapter<Order> adapter) {
        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(OrdersActivity.this);
                alert.setTitle("Delete selected order.");
                alert.setMessage("Delete order?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        orders.remove(position);
                        Toast.makeText(getApplicationContext(), "Order has been deleted.", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
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
