package developmentmodel;

import developmentmodel.knowledgesource.KnowledgeSource;
import developmentmodel.schedule.Schedule;

import java.time.LocalDate;
import java.util.List;

public class Activity {
    final KnowledgeSource knowledgeSource;
    final List<Schedule> periods;

    public Activity(KnowledgeSource knowledgeSource, List<Schedule> period) {
        this.knowledgeSource = knowledgeSource;
        this.periods = period;
    }

    void tryToApply(Student student, LocalDate currentDay){
        if(isInPeriods(currentDay))
            knowledgeSource.educate(student);
    }

    private boolean isInPeriods(LocalDate currentDay) {
        for(Schedule period: periods)
            if(!period.isActive(currentDay))
                return false;
        return true;
    }

//    private boolean isStudyingInWeekendAvailable(LocalDate currentDay) {
//        if(weekend.isActive(currentDay))
//            return knowledgeSource.getIsWeekendsAvailable();
//        return true;
//    }
}
