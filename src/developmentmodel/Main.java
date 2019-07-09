package developmentmodel;

import developmentmodel.knowledgesource.KnowledgeSource;
import developmentmodel.knowledgesource.University;
import developmentmodel.schedule.OnMonths;
import developmentmodel.schedule.Periods;
import developmentmodel.schedule.Schedule;
import developmentmodel.schedule.Weekend;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] arg){
        System.out.println(applyDevelopmentPlanPacifist());
    }

    private static String applyDevelopmentPlanPacifist(){
        KnowledgeSource university = new University(8, 2);
        Student student_1 = new Student("Vasa", 0.5f, true, true, true);

        TimePeriod universityIsActivePeriods;
        universityIsActivePeriods = new TimePeriod(LocalDate.of(2018, 9, 1), LocalDate.of(2023, 5, 30));
        List<Month> universityHolidays = new ArrayList<>();
        universityHolidays.add(Month.JUNE);
        universityHolidays.add(Month.JULY);
        universityHolidays.add(Month.AUGUST);
        universityHolidays.add(Month.JANUARY);

        List<Activity>activities = new ArrayList<>();

        List<Schedule> schedules = new ArrayList<Schedule>();
        schedules.add(new Periods(universityIsActivePeriods));
        schedules.add(new OnMonths(universityHolidays).exclude());
        schedules.add(new Weekend().exclude());
        activities.add(new Activity(university, schedules));

        TimePeriod developmentPlanPeriod = new TimePeriod(LocalDate.of(2018, 5, 1), LocalDate.of(2023, 5, 30));
        DevelopmentPlan developmentPlan = new DevelopmentPlan(activities);
        developmentPlan.perform(student_1, developmentPlanPeriod);

        return student_1.knowledgeLevel.theoryPoints+" "+student_1.knowledgeLevel.practicePoints;
    }

    private static String applyDevelopmentPlanSelfTaught(){
        KnowledgeSource university = new University(8, 2);
        Student student_1 = new Student("Vasa", 0.5f, true, true, true);



        List<Activity>activities = new ArrayList<>();

        List<Schedule> schedules = new ArrayList<Schedule>();

        activities.add(new Activity(university, schedules));

        TimePeriod developmentPlanPeriod = new TimePeriod(LocalDate.of(2018, 5, 1), LocalDate.of(2023, 5, 30));
        DevelopmentPlan developmentPlan = new DevelopmentPlan(activities);
        developmentPlan.perform(student_1, developmentPlanPeriod);

        return student_1.knowledgeLevel.theoryPoints+" "+student_1.knowledgeLevel.practicePoints;
    }
}
