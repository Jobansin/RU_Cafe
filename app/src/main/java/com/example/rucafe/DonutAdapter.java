package com.example.rucafe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.DonutHolder> {

    private Context context;
    private ArrayList<DonutCharacteristics> donuts;

    public DonutAdapter(Context context, ArrayList<DonutCharacteristics> donuts) {
        this.context = context;
        this.donuts = donuts;
    }

    @NonNull
    @Override
    public DonutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.donut_row, parent, false);
        return new DonutHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonutHolder holder, int position) {
        holder.donutName.setText(donuts.get(position).getItemName());
        holder.donutPrice.setText(donuts.get(position).getUnitPrice());
        holder.donutImage.setImageResource(donuts.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return donuts.size();
    }

    public static class DonutHolder extends RecyclerView.ViewHolder {

        private TextView donutName, donutPrice;
        private ImageView donutImage;
        private Button addButton;
        private Spinner donutQuantity;
        private ArrayList<String> quantity = new ArrayList<>();
        private ConstraintLayout parentLayout;

        public DonutHolder(@NonNull View itemView) {
            super(itemView);
            donutName = itemView.findViewById(R.id.donutName);
            donutPrice = itemView.findViewById(R.id.donutPrice);
            donutImage = itemView.findViewById(R.id.donutImage);
            addButton = itemView.findViewById(R.id.addDonut);
            donutQuantity = itemView.findViewById(R.id.donutQuantity);
            quantity.add("1");
            quantity.add("2");
            quantity.add("3");
            quantity.add("4");
            quantity.add("5");
            quantity.add("6");
            ArrayAdapter<String> numDonuts = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_list_item_1, quantity);
            donutQuantity.setAdapter(numDonuts);
            parentLayout = itemView.findViewById(R.id.donut_row_layout);
            setAddButtonOnClick(itemView);
        }

        private void setAddButtonOnClick(@NonNull View itemView) {
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    int position = getAdapterPosition();
                    double price = 0;
                    if(position < 6) {
                        price = Integer.parseInt(donutQuantity.getSelectedItem().toString()) * 1.59;
                    }
                    else if(position < 9) {
                        price = Integer.parseInt(donutQuantity.getSelectedItem().toString()) * 1.79;
                    }
                    else {
                        price = Integer.parseInt(donutQuantity.getSelectedItem().toString()) * 0.39;
                    }
                    alert.setTitle("Subtotal: $" + new DecimalFormat("0.00").format(price));
                    alert.setMessage("Add " + donutName.getText().toString() + " to cart?");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Donut donut = new Donut(donutName.getText().toString(), Integer.parseInt(donutQuantity.getSelectedItem().toString()));
                            CartActivity.cart.add(donut);
                            donutQuantity.setSelection(0);
                            Toast.makeText(itemView.getContext(), donutName.getText().toString() + " added to cart.", Toast.LENGTH_LONG).show();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(), donutName.getText().toString() + " not added to cart.", Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
        }
    }
}



