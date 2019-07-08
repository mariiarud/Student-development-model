package developmentmodel.schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class LastThursdayOnMonth implements Schedule{

    @Override
    public boolean isActive(LocalDate date) {
        LocalDate lastDay = date.withDayOfMonth(date.lengthOfMonth());
        while (!date.isAfter(lastDay)) {
            if(isThursday(lastDay))
                return date.isEqual(lastDay);
            lastDay = lastDay.minusDays(1);
        }
        return false;
    }


    boolean isThursday(LocalDate date){
        return date.getDayOfWeek() == DayOfWeek.TUESDAY;
    }
}
