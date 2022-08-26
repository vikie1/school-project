package io.github.vikie1.backend.service.analytics;

public class StatisticalClasses {
    public static enum TimeIntervals{
        EARLY_MORNINGS("4:00am - 6:59am"),
        MORNINGS("7:00am - 11:59am"),
        AFTERNOON("12:00pm - 03:59pm"),
        EVENING("4:00pm - 6:59pm"),
        EARLY_NIGHT("7:00pm - 9:59pm"),
        LATE_NIGHT("10:00pm - 12:59pm"),
        WEE_HOURS("1:00am - 3:59am");

        private String value;
        private TimeIntervals(String s) {
            this.value = s;
        }
    }
}
