package com.example.promanage;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private byte[] imageByteArray;

    public Product(int id, String name, int quantity, byte[] imageByteArray) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.imageByteArray = imageByteArray;
    }

    // Add getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public byte[] getImageByteArray() {
        return imageByteArray;
    }

    // You can also add setter methods if needed
    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImageByteArray(byte[] imageByteArray) {
        this.imageByteArray = imageByteArray;
    }
}