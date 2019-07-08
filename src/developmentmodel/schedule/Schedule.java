package developmentmodel.schedule;

import java.time.LocalDate;

public interface Schedule {
    default Schedule exclude(){
        return new ScheduleExclude(this);
    }

    boolean isActive(LocalDate date);
}
