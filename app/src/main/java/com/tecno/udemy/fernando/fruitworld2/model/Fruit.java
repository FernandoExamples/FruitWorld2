package com.tecno.udemy.fernando.fruitworld2.model;

public class Fruit {

    private int background;
    private int icon;
    private String name;
    private String description;
    private byte quantity;

    public static int LIMIT_QUANTITY = 10;

    public Fruit(int background, int icon, String name, String description, byte quantity) {
        this.background = background;
        this.icon = icon;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public int getBackground() {
        return background;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public byte getQuantity() {
        return quantity;
    }

    public void resetQuantity(){
        quantity = 0;
    }

    public void incrementQuantity(){
        if(quantity < LIMIT_QUANTITY)
            quantity++;
    }
}
