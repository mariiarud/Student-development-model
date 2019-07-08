package developmentmodel.schedule;

import java.time.LocalDate;

public class CompositorSchedule implements Schedule {
    Schedule firstSchedule;
    Schedule secondSchedule;

    public CompositorSchedule(Schedule firstSchedule, Schedule secondSchedule) {
        this.firstSchedule = firstSchedule;
        this.secondSchedule = secondSchedule;
    }

    @Override
    public boolean isActive(LocalDate date) {
        return firstSchedule.isActive(date)&&secondSchedule.isActive(date);
    }
}
