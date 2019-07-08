package developmentmodel;

import developmentmodel.knowledgesource.KnowledgeSource;
import developmentmodel.schedule.CompositorSchedule;
import developmentmodel.schedule.Schedule;

import java.time.LocalDate;
import java.util.List;

public class Activity {
    final KnowledgeSource knowledgeSource;
    final List<Schedule> schedules;

    public Activity(KnowledgeSource knowledgeSource, List<Schedule> schedule) {
        this.knowledgeSource = knowledgeSource;
        this.schedules = schedule;
    }

    void tryToApply(Student student, LocalDate currentDay){
        if(isInPeriods(currentDay))
            knowledgeSource.educate(student);
    }

    private boolean isInPeriods(LocalDate currentDay) {
        Schedule schedule = schedules.get(0);
        for(int i=1; i<schedules.size(); i++){
            schedule = new CompositorSchedule(schedule, schedules.get(i));
        }
        return schedule.isActive(currentDay);
    }
}
