package com.example.rucafe;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
    ArrayAdapter<MenuItem> allItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        orders = findViewById(R.id.orders);
        orderSubtotal = findViewById(R.id.orderSubtotal);
        orderTax = findViewById(R.id.orderTax);
        orderTotal = findViewById(R.id.orderTotal);
        allItems = new ArrayAdapter<MenuItem>(this, android.R.layout.simple_list_item_1, cart);
        orders.setAdapter(allItems);
        calculatePrices();
        deleteItem(allItems);
    }

    public void deleteItem(ArrayAdapter<MenuItem> adapter) {
        orders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(CartActivity.this);
                alert.setTitle("Delete selected item.");
                alert.setMessage("Delete item?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        cart.remove(position);
                        Toast.makeText(getApplicationContext(), "Item has been deleted.", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        calculatePrices();
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
        else {
            orderSubtotal.setText("$0.00");
            orderTax.setText("$0.00");
            orderTotal.setText("$0.00");
        }
    }

    public void placeOrder(View view) {
        if(cart.size() > 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Confirm Order.");
            alert.setMessage("Place Order?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    ArrayList<MenuItem> menuItems = new ArrayList<>(cart);
                    int orderNumber = OrdersActivity.orders.size() > 0 ? OrdersActivity.orders.get(OrdersActivity.orders.size() - 1).getNumber() : 0;
                    Order order = new Order( orderNumber + 1, menuItems);
                    OrdersActivity.orders.add(order);
                    cart.clear();
                    allItems.notifyDataSetChanged();
                    calculatePrices();
                    Toast.makeText(getApplicationContext(), "Order has been placed.", Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Order was canceled.", Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Cart is empty.", Toast.LENGTH_LONG).show();
        }
    }
}
