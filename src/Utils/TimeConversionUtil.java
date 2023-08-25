package Utils;

import java.time.LocalTime;

public class TimeConversionUtil {

    public LocalTime convertToLocalTime(String input) {
        String[] time = input.split(":");
        return LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]));
    }
}
