package com.example.demo.dtos;


import javax.validation.constraints.NotNull;


public class TimeDataElementDto {
    
    @NotNull(message = "Timestamp is required.")
    int timestamp;

    @NotNull(message = "Value is required.")
    int value;


    public TimeDataElementDto(int timestamp, int value) {
        this.timestamp = timestamp;
        this.value = value;
    }


    public String getElement() {            
        return this.timestamp + " " + this.value;
    }

    public int getTimeStamp() {
        return this.timestamp;
    }


    public int getValue() {
        return this.value;
    }


}
