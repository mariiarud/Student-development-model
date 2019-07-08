package developmentmodel.knowledgesource;

import developmentmodel.Student;

public class Meetup implements KnowledgeSource {
    final int theoryPoints;
    final int practicePoints;

    public Meetup(int theoryPoints, int practicePoints) {
        this.theoryPoints = theoryPoints;
        this.practicePoints = practicePoints;
    }

    @Override
    public void educate(Student student) {
        student.learn(Math.round(theoryPoints*student.learningRate));
        if(student.hesLaptop){
            student.practice(Math.round(practicePoints));
        }
    }
}
