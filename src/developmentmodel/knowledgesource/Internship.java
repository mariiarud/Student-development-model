package developmentmodel.knowledgesource;

import developmentmodel.Student;

public class Internship implements KnowledgeSource {
    final int theoryPoints;
    final int practicePoints;

    public Internship(int theoryPoints, int practicePoints) {
        this.theoryPoints = theoryPoints;
        this.practicePoints = practicePoints;
    }

    @Override
    public void educate(Student student) {
        if(student.isEnrolledInInternship){
            student.learn(Math.round(theoryPoints*student.learningRate));
            student.practice(Math.round(practicePoints));
        }
    }
}
