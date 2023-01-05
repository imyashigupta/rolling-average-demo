package com.example.demo;

import java.text.DecimalFormat;
import java.util.Map;


class TimeData {


    Map<String, Integer>[] data;

    TimeData() {
    }

    TimeData(Map<String, Integer>[] requestData) {

        this.data = requestData;
        
    }

    public Float GetRollingAverage() {
        
        int maxTime = 0;
        int minTime = 0;
        int sum = 0;

        for (Map<String, Integer> element : this.data) {

            sum += element.get("value");            // calculates aggregate sum of all values

            if (maxTime == 0 || element.get("timestamp").compareTo(maxTime) > 0){
                maxTime = element.get("timestamp");         // calculates highest timestamp
            }

            if (minTime == 0 || element.get("timestamp").compareTo(minTime) < 0) {
                minTime = element.get("timestamp");     // calculates lowest timestamp
            }
        }

        int delta = maxTime - minTime;      // calculates timestamps interval
        float avg = (float)sum/delta;      // calculates average over the time interval

    
        DecimalFormat result = new DecimalFormat("#.##");       // formatting result to two decimal places
        return  Float.valueOf(result.format(avg)); 
             

    }

    
}