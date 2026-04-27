package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class DateUtils {

    public static LocalDate getNextSlotDate(DayOfWeek slotDay) {
        LocalDate today = LocalDate.now();
        return today.with(TemporalAdjusters.nextOrSame(slotDay));
    }
}