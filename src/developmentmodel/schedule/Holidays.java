package developmentmodel.schedule;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Holidays implements Schedule {
    private List<Month> holidaysMonths;

    public Holidays(List<Month> holidaysMonths) {
        this.holidaysMonths = holidaysMonths;
    }

    @Override
    public boolean isActive(LocalDate date) {
        for(Month holidaysMonths: holidaysMonths)
            if(isInPeriod(holidaysMonths, date))
                return true;
        return false;
    }

    boolean isInPeriod(Month month, LocalDate date){
        return date.getMonth().name() == month.name();
    }
}
