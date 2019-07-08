package developmentmodel.knowledgesource;

import developmentmodel.Student;

public class SelfConditioning implements KnowledgeSource {
    final int theoryPoints;
    final int practicePoints;

    public SelfConditioning(int theoryPoints, int practicePoints) {
        this.theoryPoints = theoryPoints;
        this.practicePoints = practicePoints;
    }

    @Override
    public void educate(Student student) {
        student.learn(Math.round(theoryPoints*student.learningRate));
        student.practice(Math.round(practicePoints));
    }
}
