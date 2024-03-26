package com.example.withscenebuilder;

import javafx.collections.ObservableArray;

public class PaymentTable {
    private int Pay_id;
    private String Name;
    private int Order_id;
    private String date;
    private double foodTotal;
    private double vipTotal;
    private double gameTotal;
    private double total;

    public PaymentTable(int pay_id, String name, int order_id, String date, double foodTotal, double vipTotal, double gameTotal, double total) {
        Pay_id = pay_id;
        Name = name;
        Order_id = order_id;
        this.date = date;
        this.foodTotal = foodTotal;
        this.vipTotal = vipTotal;
        this.gameTotal = gameTotal;
        this.total = total;
    }

    public int getPay_id() {
        return Pay_id;
    }

    public void setPay_id(int pay_id) {
        Pay_id = pay_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getOrder_id() {
        return Order_id;
    }

    public void setOrder_id(int order_id) {
        Order_id = order_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getFoodTotal() {
        return foodTotal;
    }

    public void setFoodTotal(double foodTotal) {
        this.foodTotal = foodTotal;
    }

    public double getVipTotal() {
        return vipTotal;
    }

    public void setVipTotal(double vipTotal) {
        this.vipTotal = vipTotal;
    }

    public double getGameTotal() {
        return gameTotal;
    }

    public void setGameTotal(double gameTotal) {
        this.gameTotal = gameTotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
