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
        float avg = 0;
        float result;

        for (Map<String, Integer> element : this.data) {

            sum += element.get("value");            // calculates aggregate sum of all values

            if (maxTime == 0 || element.get("timestamp").compareTo(maxTime) > 0){
                maxTime = element.get("timestamp");         // calculates highest timestamp
            }

            if (minTime == 0 || element.get("timestamp").compareTo(minTime) < 0) {
                minTime = element.get("timestamp");     // calculates lowest timestamp
            }
        }

        
        try {
            int delta = maxTime - minTime; // calculates timestamps interval
            avg = (float)sum/delta;      // calculates average over the time interval
            DecimalFormat temp = new DecimalFormat("#.##"); // formatting result to two decimal places
            result = Float.valueOf(temp.format(avg));
        }
        catch (NumberFormatException e) {     // handling divide by zero exception
           return (float)sum;                // min and max timestamps are same
        
        }
        
        return result;
        
    }

    
}