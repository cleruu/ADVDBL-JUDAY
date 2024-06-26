package com.example.juday;

public class Cards {
    private String orderNumber;
    private String time;

    public Cards(String orderNumber, String time) {
        this.orderNumber = orderNumber;
        this.time = time;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getTime() {
        return time;
    }


}
