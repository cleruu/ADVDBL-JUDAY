package com.example.juday;

public class Stock {
    private String type;
    private String time;
    private String value;

    public Stock(String type, String time, String value) {
        this.type = type;
        this.time = time;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public String getValue() {
        return value;
    }
}
