package com.example.rucafe;

import java.util.ArrayList;

/**
 * The Coffee class consists of the cup size and the add-ins.
 */
public class Coffee extends MenuItem{

    private String cupSize;
    private ArrayList<AddIns> addIns;
    private int cups;
    private static final double SHORT_COFFEE = 1.89;
    private static final double TALL_COFFEE = 2.29;
    private static final double GRANDE_COFFEE = 2.69;
    private static final double VENTI_COFFEE = 3.09;
    private static final double ADDINS_COST = 0.30;

    /**
     * A constructor that takes in 2 parameters.
     * @param size
     * @param flavors
     */
    public Coffee(String size, ArrayList<AddIns> flavors, int cupQuantity) {
        cupSize = size;
        addIns = flavors;
        cups = cupQuantity;
    }

    /**
     * This method returns the price of the coffee with the added extra cost for any additional add-ins.
     * @return double
     */
    @Override
    public double itemPrice() {
        double price = 0;
        if(cupSize.equals("Short")) {
            price = SHORT_COFFEE;
        }
        if(cupSize.equals("Tall")) {
            price = TALL_COFFEE;
        }
        if(cupSize.equals("Grande")) {
            price = GRANDE_COFFEE;
        }
        if(cupSize.equals("Venti")) {
            price = VENTI_COFFEE;
        }
        price *= cups;
        price += addIns.size() * ADDINS_COST;
        return price;
    }
    /**
     * This method returns the coffee order.
     * @return String
     */
    @Override
    public String toString() {
        if(addIns.size() > 0)
            return cupSize + " Coffee: " + cups + " " + addIns;
        else
            return cupSize + " Coffee: " + cups;
    }
}
