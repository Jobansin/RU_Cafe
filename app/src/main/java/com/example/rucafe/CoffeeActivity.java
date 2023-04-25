package com.example.rucafe;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CoffeeActivity extends AppCompatActivity {
    private Spinner coffeeSizes;
    private Spinner coffeeAmt;
    ArrayList<String> sizes = new ArrayList<>();
    ArrayList<String> cups = new ArrayList<>();
    private CheckBox coffee1, coffee2, coffee3, coffee4, coffee5;
    private EditText coffeeSubtotal;
    private static final double SHORT_COFFEE = 1.89;
    private static final double TALL_COFFEE = 2.29;
    private static final double GRANDE_COFFEE = 2.69;
    private static final double VENTI_COFFEE = 3.09;
    private static final double ADDINS_COST = 0.30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        coffeeSizes = findViewById(R.id.coffeeSizes);
        coffeeAmt = findViewById(R.id.coffeeAmt);
        coffeeListener();
        coffee1 = findViewById(R.id.coffee1);
        coffee2 = findViewById(R.id.coffee2);
        coffee3 = findViewById(R.id.coffee3);
        coffee4 = findViewById(R.id.coffee4);
        coffee5 = findViewById(R.id.coffee5);
        coffeeSubtotal = findViewById(R.id.coffeeSubtotal);
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
        coffeeSubtotal.setText("$" + SHORT_COFFEE);
    }

    public void coffeeListener() {
        coffeeSizes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changePrice(view);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing selected
            }
        });
        coffeeAmt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changePrice(view);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing selected
            }
        });
    }

    public void addCoffee(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirm Coffee Order");
        alert.setMessage("Add to Cart?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ArrayList<AddIns> flavors = new ArrayList<>();
                if(coffee1.isChecked()) {
                    flavors.add(AddIns.SWEETCREAM);
                    coffee1.setChecked(false);
                }
                if(coffee2.isChecked()) {
                    flavors.add(AddIns.FRENCHVANILLA);
                    coffee2.setChecked(false);
                }
                if(coffee3.isChecked()) {
                    flavors.add(AddIns.IRISHCREAM);
                    coffee3.setChecked(false);
                }
                if(coffee4.isChecked()) {
                    flavors.add(AddIns.CARAMEL);
                    coffee4.setChecked(false);
                }
                if(coffee5.isChecked()) {
                    flavors.add(AddIns.MOCHA);
                    coffee5.setChecked(false);
                }
                Coffee coffeeOrder = new Coffee(coffeeSizes.getSelectedItem().toString(), flavors, Integer.parseInt(coffeeAmt.getSelectedItem().toString()));
                CartActivity.cart.add(coffeeOrder);
                coffeeSizes.setSelection(0);
                coffeeAmt.setSelection(0);
                Toast.makeText(getApplicationContext(), "Coffee added to order.", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Coffee order was canceled.", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void changePrice(View view) {
        double total = 0, price = 0;
        if(coffeeSizes.getSelectedItem().toString().equals("Short")) {
            price = SHORT_COFFEE;
        }
        if(coffeeSizes.getSelectedItem().toString().equals("Tall")) {
            price = TALL_COFFEE;
        }
        if(coffeeSizes.getSelectedItem().toString().equals("Grande")) {
            price = GRANDE_COFFEE;
        }
        if(coffeeSizes.getSelectedItem().toString().equals("Venti")) {
            price = VENTI_COFFEE;
        }
        total = Integer.parseInt(coffeeAmt.getSelectedItem().toString()) * price;
        if(coffee1.isChecked())
            total += ADDINS_COST;
        if(coffee2.isChecked())
            total += ADDINS_COST;
        if(coffee3.isChecked())
            total += ADDINS_COST;
        if(coffee4.isChecked())
            total += ADDINS_COST;
        if(coffee5.isChecked())
            total += ADDINS_COST;
        coffeeSubtotal.setText("$" + new DecimalFormat("0.00").format(total));
    }
}
