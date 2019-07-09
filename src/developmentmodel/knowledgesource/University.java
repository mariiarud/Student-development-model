package developmentmodel.knowledgesource;

import developmentmodel.Student;

public class University implements KnowledgeSource {
    private final int theoryPoints;
    private final int practicePoints;

    public University(int theoryPoints, int practicePoints) {
        this.theoryPoints = theoryPoints;
        this.practicePoints = practicePoints;
    }

    @Override
    public void educate(Student student) {
        if(student.isEnrolledInUniversity){
            student.learn(theoryPoints*student.learningRate);
            student.practice(Math.round(practicePoints));
        }
    }
}
