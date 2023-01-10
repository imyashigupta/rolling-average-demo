package com.example.demo.dtos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class TimeDataDto {


    @Valid
    @NotNull(message = "Data is required.")
    private List<TimeDataElementDto> data = new ArrayList<TimeDataElementDto>(); 


    /**
     * Constructor to initialise data list.
     * The json object is mapped to a list of TimeDataElement objects
     * @param requestData the json object is mapped a list of TimeDataElement
     */
    public TimeDataDto(Map<String, Integer>[] requestData) {


        for (Map<String, Integer> element : requestData) {
            data.add(new TimeDataElementDto(element.get("timestamp"), element.get("value")));
        }
        
    }

    /**
     * Calculates the rolling average of the values in data list over all timestamps
     * @param none
     * @return result: rolling average of valuess
     */
    public Float getRollingAverage() {
        
        int maxTime = getMaxTimestamp();
        int minTime = getMinTimestamp();
        int sum = 0;
        float avg = 0;
        float result;

        for (TimeDataElementDto element : this.data) {

            sum += element.getValue();          // calculates aggregate sum of all values
            
        }
             
        int delta = maxTime - minTime; // calculates timestamps interval

        if (delta==0) { // single time stamp
            return (float) sum; // min and max timestamps are same
        } 

        avg = (float)sum/delta;      // calculates average over the time interval
        DecimalFormat temp = new DecimalFormat("#.##"); // formatting result to two decimal places
        result = Float.valueOf(temp.format(avg));
        
        
        return result;
        
    }


    /**
     * Calculates highest timestamp from the data list
     * @param none
     * @return maxTime: the maximum timestamp
     */
    private int getMaxTimestamp() { 

        int maxTime = 0;

        for (TimeDataElementDto element : this.data) {
            if ((maxTime == 0) || (element.getTimeStamp() > maxTime)) {
                maxTime = element.getTimeStamp(); 
            }
        }

        return maxTime;

    }

    /**
     * Calculates minimum timestamp from the data list
     * @param none
     * @return minTime: the minimum timestamp
     */
    private int getMinTimestamp() {

        int minTime = 0;

        for (TimeDataElementDto element : this.data) {
            if ((minTime == 0) || (element.getTimeStamp() < minTime)) {
                minTime = element.getTimeStamp();
            }
        }

        return minTime;

    }

    public boolean checkValidity() {

        boolean valid = true;

        if(data.size()==1) {            // only one timestamp request
            return valid;
        }

        int[] timestamps = new int[this.data.size()];         // stores a sorted list of timestamps in data list

        for (int i = 0; i < data.size(); i++) {
            timestamps[i] = data.get(i).getTimeStamp();
        }
        Arrays.sort(timestamps);        // sortes the list before checking interval
        
        int interval = timestamps[1] - timestamps[0];           // array has atleast 2 elements

        if(interval==0) {               // same timestamps
            valid = false;
            return valid;
        }
        

        for (int j = 2; j < timestamps.length; j++) {
            if((timestamps[j] - timestamps[j-1])!=interval){
                valid=false;
            }
        }

        return valid;
    }

    
}