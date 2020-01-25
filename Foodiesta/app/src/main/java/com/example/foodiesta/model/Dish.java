package com.example.foodiesta.model;

public class Dish {

    int dishId;
    int restaurentId;
    String dishName;
    String dishPrice;
    String dishDescription;
    String dishImage;

    public Dish(int id, String name, String image, String price, String description, int restaurentId)
    {

    }

    public Dish(int dishId, int restaurentId, String dishName, String dishPrice, String dishDescription, String dishImage) {
        this.dishId = dishId;
        this.restaurentId = restaurentId;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishDescription = dishDescription;
        this.dishImage = dishImage;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getRestaurentId() {
        return restaurentId;
    }

    public void setRestaurentId(int restaurentId) {
        this.restaurentId = restaurentId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(String dishPrice) {
        this.dishPrice = dishPrice;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }

    public String getDishImage() {
        return dishImage;
    }

    public void setDishImage(String dishImage) {
        this.dishImage = dishImage;
    }
}
