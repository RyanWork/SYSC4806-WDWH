package SYSC4806.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

/**
 * Course is a model class that represents a course a student can take.
 * A course belongs to many programs and a student should acquire certain learning outcomes by taking this course.
 */
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private int year;
    private String name;
    private String code;
    private ArrayList<LearningOutcome> learningOutcomes;
    private ArrayList<Program> programs;

    public Course() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<LearningOutcome> getLearningOutcomes() {
        return learningOutcomes;
    }

    public void setLearningOutcomes(ArrayList<LearningOutcome> learningOutcomes) {
        this.learningOutcomes = learningOutcomes;
    }

    public ArrayList<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(ArrayList<Program> programs) {
        this.programs = programs;
    }
}
