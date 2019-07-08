package developmentmodel.schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Weekend implements Schedule {
    @Override
    public boolean isActive(LocalDate date) {
        return (date.getDayOfWeek()== DayOfWeek.SATURDAY || date.getDayOfWeek()== DayOfWeek.SUNDAY);
    }
}
