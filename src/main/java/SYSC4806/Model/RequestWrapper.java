package SYSC4806.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class RequestWrapper {
    private Category category;

    private Course course;

    private LearningOutcome learningOutcome;

    private Program program;

    private ArrayList<String> programList = new ArrayList<String>();

    private ArrayList<String>learningOutcomeList = new ArrayList<String>();

    private ArrayList<String>courseList = new ArrayList<String>();

    public ArrayList<String> getProgramList() { return this.programList;}

    public ArrayList<String> getLearningOutcomeList() { return this.learningOutcomeList;}

    public ArrayList<String> getCourseList() { return this.courseList;}

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
