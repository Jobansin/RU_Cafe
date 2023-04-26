package com.example.rucafe;

import java.util.ArrayList;

/**
 * The Order class consists of the order number and the shopping basket.
 */
public class Order {

    private int orderNumber;
    private ArrayList<MenuItem> basket;
    private static final double NJ_TAX = 0.06625;

    /**
     * A constructor that takes in 2 parameters.
     * @param number
     * @param items
     */
    public Order(int number, ArrayList<MenuItem> items) {
        orderNumber = number;
        basket = items;
    }

    /**
     * This method returns the shopping basket array list of MenuItem objects.
     * @return ArrayList<MenuItem>
     */
    public ArrayList<MenuItem> getItems() {
        return basket;
    }

    /**
     * This method returns the total price of the shopping basket.
     * @return double
     */
    public double getTotal() {
        double total = 0;
        for(MenuItem item: basket) {
            total += item.itemPrice();
        }
        return total * (1 + NJ_TAX);
    }

    /**
     * This method returns the order number.
     * @return int
     */
    public int getNumber() {
        return orderNumber;
    }

    /**
     * This method returns the order contents.
     * @return String
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Order #" + orderNumber + "\n");
        for(MenuItem item: basket) {
            str.append(item + "\n");
        }
        return str.toString();
    }
}
