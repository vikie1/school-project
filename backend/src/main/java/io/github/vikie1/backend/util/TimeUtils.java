package io.github.vikie1.backend.util;

import io.github.vikie1.backend.service.analytics.StatisticalClasses;

import java.time.LocalTime;

public class TimeUtils {

    /**
     * TimeUtils.getTimeSlot() method returns the given timeSlot ( Refer to StatisticalClasses.TimeIntervals enum )
     * after you specify the exact time
     * @param time (description) - the parameter should be an object of type LocalTime
     * @return - the return type is a timeslot of the String object
     */
    public static String getTimeSlot(LocalTime time){
        for (StatisticalClasses.TimeIntervals timeSlot: StatisticalClasses.TimeIntervals.values()){
            String[] specificTimeSlot = timeSlot.name().split(" - ");
            LocalTime startOfTimeSlot = LocalTime.parse(specificTimeSlot[0]);
            LocalTime endOfTimeSlot = LocalTime.parse(specificTimeSlot[1]);

            if (time.equals(startOfTimeSlot)
                    || time.equals(endOfTimeSlot)
                    || (time.isAfter(startOfTimeSlot) && time.isBefore(endOfTimeSlot))){
                return timeSlot.name();
            }
        }
        throw new IllegalArgumentException("The timeslot provided doesn't fit any given slot or there was an error while processing the time");
    }
}
