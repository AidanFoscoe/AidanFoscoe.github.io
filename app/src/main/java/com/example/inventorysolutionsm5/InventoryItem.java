package com.example.inventorysolutionsm5;

/**
 * Model class representing a single inventory item.
 * Each item maps directly to a row in the inventory database table.
 */
public class InventoryItem {

    private int id;          // Primary key from SQLite
    private String name;     // Item name
    private int quantity;    // Item quantity
    private String category; // Item category

    // Constructor used when reading from the database
    public InventoryItem(int id, String name, int quantity, String category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    // Setters (used for future update functionality)
    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}