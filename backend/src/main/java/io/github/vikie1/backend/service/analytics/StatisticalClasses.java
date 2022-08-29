package io.github.vikie1.backend.service.analytics;

public class StatisticalClasses {
    public enum TimeIntervals{
        EARLY_MORNINGS("04:00 - 06:59"),
        MORNINGS("07:00 - 11:59"),
        AFTERNOON("12:00 - 15:59"),
        EVENING("16:00 - 18:59"),
        EARLY_NIGHT("19:00 - 21:59"),
        LATE_NIGHT("22:00 - 00:59"),
        WEE_HOURS("01:00am - 03:59am");

        private final String value;
        private TimeIntervals(String s) {
            this.value = s;
        }

        public String getValue() {
            return value;
        }
    }
}
