package com.example.demo;

public class TimeDataElement {
    
    int timestamp;
    int value;


    public TimeDataElement(int timestamp, int value) {
        this.timestamp = timestamp;
        this.value = value;
    }


    public String getElement() {            // DEBUG
        return this.timestamp + " " + this.value;
    }

    public int getTimeStamp() {
        return this.timestamp;
    }


    public int getValue() {
        return this.value;
    }


}
