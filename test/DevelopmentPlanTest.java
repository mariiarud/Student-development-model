import developmentmodel.Activity;
import developmentmodel.DevelopmentPlan;
import developmentmodel.Student;
import developmentmodel.TimePeriod;
import developmentmodel.knowledgesource.Meetup;
import developmentmodel.knowledgesource.SelfConditioning;
import developmentmodel.knowledgesource.University;
import developmentmodel.knowledgesource.Internship;
import developmentmodel.schedule.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class DevelopmentPlanTest {
    Student student_1;
    Student student_2;
    DevelopmentPlan developmentPlan;
    List<Activity> activities;
    TimePeriod developmentPlanPeriod;

    SelfConditioning selfConditioning;
    Meetup meetup;
    Internship internship;
    University university;

    TimePeriod universityIsActivePeriods;
    TimePeriod shelfConditioningPeriods;
    TimePeriod internshipPeriod;

    List<Month>universityHolidays = new ArrayList<>();

    @BeforeEach
    void setUp() {
        student_1 = new Student("Vasa", 0.5f, true, true, false);
        student_2 = new Student("Anton", 0.6f, true, true, false);

        selfConditioning = new SelfConditioning(2, 2);
        meetup = new Meetup(10, 5);
        internship = new Internship(10, 12);
        university = new University(8, 2);
        developmentPlanPeriod = new TimePeriod(LocalDate.of(2018, 12, 1), LocalDate.of(2019, 1, 1));

        universityIsActivePeriods = new TimePeriod(LocalDate.of(2018, 9, 1), LocalDate.of(2022, 12, 30));
        shelfConditioningPeriods = developmentPlanPeriod;

        internshipPeriod = new TimePeriod(LocalDate.of(2018, 7, 1), LocalDate.of(2018, 9, 30));

        universityHolidays.add(Month.JUNE);
        universityHolidays.add(Month.JULY);
        universityHolidays.add(Month.AUGUST);
        universityHolidays.add(Month.JANUARY);
    }

    @Test
    void performDevPlan_excludeWeekends(){
        List<Activity>activities = new ArrayList<>();

        List<Schedule> schedules = new ArrayList<Schedule>();
        schedules.add(new Periods(internshipPeriod));
        schedules.add(new Weekend().exclude());
        activities.add(new Activity(internship, schedules));

        developmentPlanPeriod = new TimePeriod(LocalDate.of(2018, 9, 1), LocalDate.of(2018, 9, 30));
        developmentPlan = new DevelopmentPlan(activities);
        developmentPlan.perform(student_1, developmentPlanPeriod);

        Assert.assertEquals(student_1.knowledgeLevel.theoryPoints, 100);
    }

    @Test
    void performDevPlan_excludeHolidays(){
        List<Activity>activities = new ArrayList<>();

        List<Schedule> schedules = new ArrayList<Schedule>();
        schedules.add(new Periods(universityIsActivePeriods));
        schedules.add(new OnMonths(universityHolidays).exclude());
        schedules.add(new Weekend().exclude());
        activities.add(new Activity(university, schedules));

        developmentPlanPeriod = new TimePeriod(LocalDate.of(2018, 5, 1), LocalDate.of(2018, 9, 30));
        developmentPlan = new DevelopmentPlan(activities);
        developmentPlan.perform(student_1, developmentPlanPeriod);

        Assert.assertEquals(student_1.knowledgeLevel.theoryPoints, 80);
    }

    @Test
    void performDevPlan_lastThursday(){
        List<Activity>activities = new ArrayList<>();

        developmentPlanPeriod = new TimePeriod(LocalDate.of(2018, 9, 1), LocalDate.of(2018, 10, 30));

        List<Schedule> schedules = new ArrayList<Schedule>();
        schedules.add(new Periods(developmentPlanPeriod));
        schedules.add(new LastThursdayOnMonth());
        activities.add(new Activity(meetup, schedules));

        developmentPlan = new DevelopmentPlan(activities);
        developmentPlan.perform(student_1, developmentPlanPeriod);

        Assert.assertEquals(student_1.knowledgeLevel.theoryPoints, 10);
    }

    @Test
    void performDevPlan_meetupWithoutLaptop(){
        List<Activity>activities = new ArrayList<>();

        developmentPlanPeriod = new TimePeriod(LocalDate.of(2018, 9, 1), LocalDate.of(2018, 10, 30));

        List<Schedule> schedules = new ArrayList<Schedule>();
        schedules.add(new Periods(developmentPlanPeriod));
        schedules.add(new LastThursdayOnMonth());
        activities.add(new Activity(meetup, schedules));

        developmentPlan = new DevelopmentPlan(activities);
        developmentPlan.perform(student_1, developmentPlanPeriod);

        Assert.assertEquals(student_1.knowledgeLevel.practicePoints, 0);
    }

    @Test
    void performDevPlan_meetupWithLaptop(){
        List<Activity>activities = new ArrayList<>();
        student_1.hesLaptop = true;

        developmentPlanPeriod = new TimePeriod(LocalDate.of(2018, 9, 1), LocalDate.of(2018, 10, 30));

        List<Schedule> schedules = new ArrayList<Schedule>();
        schedules.add(new Periods(developmentPlanPeriod));
        schedules.add(new LastThursdayOnMonth());
        activities.add(new Activity(meetup, schedules));

        developmentPlan = new DevelopmentPlan(activities);
        developmentPlan.perform(student_1, developmentPlanPeriod);

        Assert.assertEquals(student_1.knowledgeLevel.practicePoints, 10);
    }

    @Test
    void performDevPlan_studentEducateOtherStudent(){
        List<Activity>activities = new ArrayList<>();

        developmentPlanPeriod = new TimePeriod(LocalDate.of(2018, 9, 1), LocalDate.of(2018, 9, 30));

        List<Schedule> schedules = new ArrayList<Schedule>();
        schedules.add(new Periods(developmentPlanPeriod));
        activities.add(new Activity(student_2, schedules));

        developmentPlan = new DevelopmentPlan(activities);
        developmentPlan.perform(student_1, developmentPlanPeriod);

        Assert.assertEquals(student_1.knowledgeLevel.theoryPoints, 30);
    }
}
