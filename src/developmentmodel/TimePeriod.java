package developmentmodel;

import java.time.LocalDate;

public class TimePeriod {
    public final LocalDate firstDay;
    public final LocalDate lastDay;

    public TimePeriod(LocalDate firstDay, LocalDate lastDay) {
        this.firstDay = firstDay;
        this.lastDay = lastDay;
    }
}
