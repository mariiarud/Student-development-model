package developmentmodel;

import java.time.LocalDate;
import java.util.List;

public class DevelopmentPlan {
//    List<Student> students;
    List<Activity> activities;

    public DevelopmentPlan(List<Activity> activities){
        this.activities = activities;
    }

    public void perform(Student student, TimePeriod periodSchedule){
        LocalDate currentDay = periodSchedule.firstDay;
        while (!currentDay.isAfter(periodSchedule.lastDay)){
            performActivities(student, currentDay);
            currentDay = currentDay.plusDays(1);
        }
    }

    private void performActivities(Student student, LocalDate currentDay) {
        for(Activity activity: activities){
            activity.tryToApply(student, currentDay);
        }
    }

//    private void applyToStudents(LocalDate currentDay, Activity activity) {
//        for(Student student: students)
//            activity.tryToApply(student, currentDay);
//    }
}
