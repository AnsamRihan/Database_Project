package com.example.withscenebuilder;

public class FoodTable {
    int orderid;
    String date;
    String FOOD_NAME;
    int FOOD_QUANTITY;
    double totalprice;

    public FoodTable(int orderid, String FOOD_NAME, String date, int FOOD_QUANTITY, double totalprice) {

        this.orderid = orderid;
        this.date = date;
        this.FOOD_NAME = FOOD_NAME;
        this.FOOD_QUANTITY = FOOD_QUANTITY;
        this.totalprice = totalprice;
    }


    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFOOD_NAME() {
        return FOOD_NAME;
    }

    public void setFOOD_NAME(String FOOD_NAME) {
        this.FOOD_NAME = FOOD_NAME;
    }

    public int getFOOD_QUANTITY() {
        return FOOD_QUANTITY;
    }

    public void setFOOD_QUANTITY(int FOOD_QUANTITY) {
        this.FOOD_QUANTITY = FOOD_QUANTITY;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }
}