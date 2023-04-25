package com.example.rucafe;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Donut class consists of the flavor donut and the amount of donuts.
 */
public class Donut extends MenuItem{

    private String flavor;
    private int numDonuts;
    private static final double YEAST_DONUT = 1.59;
    private static final double CAKE_DONUT = 1.79;
    private static final double DONUT_HOLE = 0.39;

    /**
     * A constructor that takes in 2 parameters.
     * @param donutFlavor
     * @param donutQuantity
     */
    public Donut(String donutFlavor, int donutQuantity) {
        flavor = donutFlavor;
        numDonuts = donutQuantity;
    }

    /**
     * This method returns the total price of the donuts ordered.
     * @return double
     */
    @Override
    public double itemPrice() {
        double price = 0;
        ArrayList<String> yeastDonutFlavors = new ArrayList<> (Arrays.asList("Jelly", "Glazed", "Strawberry Frosted", "Sugar", "Chocolate Frosted", "Lemon Filled"));
        ArrayList<String> cakeDonutFlavors = new ArrayList<> (Arrays.asList("Original", "Blueberry", "Cinnamon Sugar"));
        ArrayList<String> donutHoleFlavors = new ArrayList<> (Arrays.asList("Chocolate", "Powdered", "Butternut"));
        if (yeastDonutFlavors.contains(flavor)) {
            price += YEAST_DONUT * numDonuts;
        }
        if (cakeDonutFlavors.contains(flavor)) {
            price += CAKE_DONUT * numDonuts;
        }
        if (donutHoleFlavors.contains(flavor)) {
            price += DONUT_HOLE * numDonuts;
        }
        return price;
    }

    /**
     * This method returns the donut order.
     * @return String
     */
    @Override
    public String toString() {
        return flavor + ": " + numDonuts;
    }
}