package developmentmodel.schedule;

import java.time.LocalDate;

public class ScheduleExclude implements Schedule {
    private Schedule schedule;

    public ScheduleExclude(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean isActive(LocalDate date) {
        return !schedule.isActive(date);
    }
}
