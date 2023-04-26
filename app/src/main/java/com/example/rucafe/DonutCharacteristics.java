package com.example.rucafe;

/**
 * This class defines the data structure of an item to be displayed in the RecyclerView
 * @author David Harianto
 * @author Joban Singh
 */
public class DonutCharacteristics {
    private String itemName;
    private int image;
    private String unitPrice;

    /**
     * Parameterized constructor.
     * @param itemName
     * @param image
     * @param unitPrice
     */
    public DonutCharacteristics(String itemName, int image, String unitPrice) {
        this.itemName = itemName;
        this.image = image;
        this.unitPrice = unitPrice;
    }

    /**
     * Getter method that returns the item name of an item.
     * @return the item name of an item.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Getter method that returns the image of an item.
     * @return the image of an item.
     */
    public int getImage() {
        return image;
    }

    /**
     * Getter method that returns the unit price.
     * @return the unit price of the item.
     */
    public String getUnitPrice() {
        return unitPrice;
    }
}
