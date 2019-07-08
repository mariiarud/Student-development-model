package developmentmodel;

import developmentmodel.knowledgesource.KnowledgeSource;

public class Student implements KnowledgeSource {
    final String name;
    public final float learningRate;
    public Knowledge knowledgeLevel;
    public boolean isEnrolledInUniversity;
    public boolean isEnrolledInInternship;
    public boolean hesLaptop;

    public Student(String name, float learningRate, boolean isEnrolledInUniversity,  boolean isEnrolledInInternship,  boolean hesLaptop ) {
        this.name = name;
        this.learningRate = learningRate;
        this.knowledgeLevel = new Knowledge(0, 0);
        this.isEnrolledInUniversity = isEnrolledInUniversity;
        this.isEnrolledInInternship = isEnrolledInInternship;
        this.hesLaptop = hesLaptop;
    }

    public void learn(float theoryPoints){
        knowledgeLevel.theoryPoints +=  theoryPoints;
    }

    public void practice(float practicePoints){
        knowledgeLevel.practicePoints += practicePoints;
    }

    @Override
    public void educate(Student student) {
        student.learn(1*student.learningRate);
        student.practice(1);
    }
}
