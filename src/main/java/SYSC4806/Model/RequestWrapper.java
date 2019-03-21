package SYSC4806.Model;

public class RequestWrapper {
    private Category category;

    private Course course;

    private LearningOutcome learningOutcome;

    private Program program;

    public Category getCategory() {
        return this.category;
    }

    public Course getCourse(){
        return this.course;
    }

    public LearningOutcome getLearningOutcome(){
        return this.learningOutcome;
    }

    public Program getProgram(){
        return this.program;
    }
}
