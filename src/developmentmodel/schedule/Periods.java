package developmentmodel.schedule;

import developmentmodel.TimePeriod;

import java.time.LocalDate;

public class Periods implements Schedule {
    public final TimePeriod timePeriod;

    public Periods(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    @Override
    public boolean isActive(LocalDate date) {
        return (date.isAfter(timePeriod.firstDay)||(date.isEqual(timePeriod.firstDay)))
                && (date.isEqual(timePeriod.lastDay)||date.isBefore(timePeriod.lastDay));
    }
}
