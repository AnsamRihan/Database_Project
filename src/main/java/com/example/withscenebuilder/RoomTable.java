package com.example.withscenebuilder;

public class RoomTable {
    private int Order_id;
    private String Date;
    private int Room_id;
    private String TimeRange;
    private Double price;


    public RoomTable(int order_id, String date, int room_id, String timeRange, Double price) {
        Order_id = order_id;
        Date = date;
        Room_id = room_id;
        TimeRange = timeRange;
        this.price = price;
    }

    public int getOrder_id() {
        return Order_id;
    }

    public void setOrder_id(int order_id) {
        Order_id = order_id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getRoom_id() {
        return Room_id;
    }

    public void setRoom_id(int room_id) {
        Room_id = room_id;
    }

    public String getTimeRange() {
        return TimeRange;
    }

    public void setTimeRange(String timeRange) {
        TimeRange = timeRange;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}