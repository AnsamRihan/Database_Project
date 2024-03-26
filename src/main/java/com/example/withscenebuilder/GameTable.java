package com.example.withscenebuilder;

public class GameTable {
    int order_id;
    int Game_id;
    double  Total;
    String Gamr_type;
    String Order_time;
    Double total;

    public GameTable(int order_id, String order_time ,int game_id, String gamr_type , double total) {
        this.order_id = order_id;
        Game_id = game_id;
        Total = total;
        Gamr_type = gamr_type;
        Order_time = order_time;

    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getGame_id() {
        return Game_id;
    }

    public void setGame_id(int game_id) {
        Game_id = game_id;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public String getGamr_type() {
        return Gamr_type;
    }

    public void setGamr_type(String gamr_type) {
        Gamr_type = gamr_type;
    }

    public String getOrder_time() {
        return Order_time;
    }

    public void setOrder_time(String order_time) {
        Order_time = order_time;
    }
}
